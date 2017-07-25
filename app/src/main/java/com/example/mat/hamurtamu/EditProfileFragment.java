package com.example.mat.hamurtamu;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.example.mat.hamurtamu.model.User;
import com.example.mat.hamurtamu.sql.DatabaseHelper;


public class EditProfileFragment extends Fragment implements View.OnClickListener{


    private ConstraintLayout constraintLayout;
    private SharedPreferences sharedPreferences;
    private User user;
    private DatabaseHelper databaseHelper;
    private EditText editTextNama;
    private EditText editTextUsername;
    private EditText editTextOldPassword;
    private EditText editTextNewPassword;
    private EditText editTextEmail;
    private EditText editTextTelp;
    private Button buttonBatal;
    private Button buttonEdit;


    public EditProfileFragment() {
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
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        user = databaseHelper.getUser(sharedPreferences.getString("login",null));
        editTextUsername.setText(user.getUsername());
        editTextEmail.setText(user.getEmail());
        editTextTelp.setText(user.getTelp());

        buttonBatal.setOnClickListener(this);
        buttonEdit.setOnClickListener(this);
    }

    public void init(){

        constraintLayout = getView().findViewById(R.id.layoutProfile);

        databaseHelper = new DatabaseHelper(this.getContext());

        user = new User();

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);;

        editTextNama = (EditText) getView().findViewById(R.id.EditTextNama);
        editTextUsername = (EditText)getView().findViewById(R.id.Editusername);
        editTextOldPassword = (EditText)getView().findViewById(R.id.password_old);
        editTextNewPassword = (EditText)getView().findViewById(R.id.password_new);
        editTextEmail = (EditText)getView().findViewById(R.id.EditTextEmail);
        editTextTelp = (EditText)getView().findViewById(R.id.EditTextTlp);
        buttonBatal = (Button) getView().findViewById(R.id.buttonBatal);
        buttonEdit = (Button)getView().findViewById(R.id.buttonEdit);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonEdit:
                user.setName(editTextNama.getText().toString().trim());
                user.setEmail(editTextEmail.getText().toString().trim());
                user.setTelp(editTextTelp.getText().toString().trim());
                if(!editTextNewPassword.getText().toString().isEmpty()){
                    if(editTextNewPassword.getText().toString().equals(user.getPassword()))
                        user.setPassword(editTextNewPassword.getText().toString());
                    else
                        Snackbar.make(constraintLayout, "Silahkan masukkan password yang benar" , Snackbar.LENGTH_LONG).show();

                }
                databaseHelper.updateUser(user);
                Snackbar.make(constraintLayout, "Akun Berhasil diubah" , Snackbar.LENGTH_LONG).show();
                break;
            case R.id.buttonBatal:
                getActivity().onBackPressed();
                break;
        }
    }
}
