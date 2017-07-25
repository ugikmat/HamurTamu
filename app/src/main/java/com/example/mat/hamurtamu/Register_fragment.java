package com.example.mat.hamurtamu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mat.hamurtamu.helper.InputValidation;
import com.example.mat.hamurtamu.model.User;
import com.example.mat.hamurtamu.sql.DatabaseHelper;

//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link Register_fragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link Register_fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Register_fragment extends Fragment {

    private ConstraintLayout constraintLayout;
    private EditText name ;
    private EditText username ;
    private EditText password ;
    private EditText Confirmpassword ;
    private EditText telp ;
    private EditText email ;
    private Button batal;
    private Button register;

    private InputValidation inputValidation;
    private User user;
    private DatabaseHelper databaseHelper;



    public Register_fragment() {
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
        return inflater.inflate(R.layout.fragment_register_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        batal.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataToSQLite();
            }
        });

    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(name,  getString(R.string.error_message_name))) {
            Snackbar.make(constraintLayout, "Silahkan isi nama terlebih dahulu" , Snackbar.LENGTH_LONG).show();
            return;
        }
        if (!inputValidation.isInputEditTextFilled(username,  getString(R.string.error_message_name))) {
            Snackbar.make(constraintLayout, "Silahkan isi username terlebih dahulu" , Snackbar.LENGTH_LONG).show();
            return;
        }
        if (!inputValidation.isInputEditTextFilled(email,  getString(R.string.error_message_email))) {
            Snackbar.make(constraintLayout, "Silahkan isi email terlebih dahulu" , Snackbar.LENGTH_LONG).show();
            return;
        }
        if (!inputValidation.isInputEditTextEmail(email, getString(R.string.error_message_email))) {
            Snackbar.make(constraintLayout, "Email tidak valid" , Snackbar.LENGTH_LONG).show();
            return;
        }
        if (!inputValidation.isInputEditTextFilled(telp,  getString(R.string.error_message_name))) {
            Snackbar.make(constraintLayout, "Silahkan isi nomor telepon terlebih dahulu" , Snackbar.LENGTH_LONG).show();
            return;
        }
        if (!inputValidation.isInputEditTextFilled(password,getString(R.string.error_message_password))) {
            Snackbar.make(constraintLayout, "Silahkan isi password terlebih dahulu" , Snackbar.LENGTH_LONG).show();
            return;
        }
        if (!inputValidation.isInputEditTextMatches(password,
                Confirmpassword, getString(R.string.error_password_match))) {
            Snackbar.make(constraintLayout, "Confirmasi password tidak cocok" , Snackbar.LENGTH_LONG).show();
            return;
        }

        if (!databaseHelper.checkUser(email.getText().toString().trim())) {

            user.setName(name.getText().toString().trim());
            user.setUsername(username.getText().toString().trim());
            user.setEmail(email.getText().toString().trim());
            user.setTelp(telp.getText().toString().trim());
            user.setPassword(password.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(constraintLayout, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(constraintLayout, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    public void init(){
        name = getView().findViewById(R.id.EditTextNama);
        username = getView().findViewById(R.id.username);
        password = getView().findViewById(R.id.password_register);
        Confirmpassword = getView().findViewById(R.id.password_register_confirm);
        telp = getView().findViewById(R.id.EditTextTlp);
        email = getView().findViewById(R.id.EditTextEmail);
        batal = getView().findViewById(R.id.buttonBatal);
        register = getView().findViewById(R.id.buttonDaftar);

        inputValidation = new InputValidation(this.getContext());
        user = new User();
        databaseHelper = new DatabaseHelper(this.getContext());

        constraintLayout = getView().findViewById(R.id.Layout);
    }
}
