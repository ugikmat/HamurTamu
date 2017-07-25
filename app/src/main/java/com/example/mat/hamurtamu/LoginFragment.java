package com.example.mat.hamurtamu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mat.hamurtamu.helper.InputValidation;
import com.example.mat.hamurtamu.model.User;
import com.example.mat.hamurtamu.sql.DatabaseHelper;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link LoginFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link LoginFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class LoginFragment extends Fragment implements View.OnClickListener{

    private TextView register;

    private EditText username;
    private EditText password;

    private Button submit;
    private Button batal;

    private ConstraintLayout constraintLayout;

    private InputValidation inputValidation;
    private User user;
    private DatabaseHelper databaseHelper;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textRegister:
                Register_fragment fragment = new Register_fragment();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, fragment, "register_fragment").commit();
                break;
            case R.id.buttonBatal:
                getActivity().onBackPressed();
                break;
            case R.id.buttonSubmit:
                verifyFromSQLite();
                break;
        }
    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(username, getString(R.string.error_message_email))) {
            Snackbar.make(constraintLayout, "Silahkan isi username terlebih dahulu" , Snackbar.LENGTH_LONG).show();
            return;
        }
        if (!inputValidation.isInputEditTextFilled(password,  getString(R.string.error_message_email))) {
            Snackbar.make(constraintLayout, "Silahkan isi password terlebih dahulu" , Snackbar.LENGTH_LONG).show();
            return;
        }

        if (databaseHelper.checkUser(username.getText().toString().trim()
                , password.getText().toString().trim())) {
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("login", username.getText().toString().trim());
            editor.commit();

            HomeFragment fragment = new HomeFragment();

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, fragment, "home_fragment").commit();

            MainActivity mainActivity = (MainActivity)getActivity();
            mainActivity.loggedIn();

        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(constraintLayout, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    public void init(){

        register = getView().findViewById(R.id.textRegister);

        username = getView().findViewById(R.id.username);
        password = getView().findViewById(R.id.password_login);

        submit = getView().findViewById(R.id.buttonSubmit);
        batal = getView().findViewById(R.id.buttonBatal);

        register.setOnClickListener(this);
        submit.setOnClickListener(this);
        batal.setOnClickListener(this);

        inputValidation = new InputValidation(this.getContext());
        user = new User();
        databaseHelper = new DatabaseHelper(this.getContext());

        constraintLayout = getView().findViewById(R.id.LoginLayout);

    }

}
