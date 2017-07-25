package com.example.mat.hamurtamu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link Cari.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link Cari#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Cari extends Fragment implements AdapterView.OnItemSelectedListener {


    public Cari() {
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
        return inflater.inflate(R.layout.fragment_cari, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initiate Gender Spinner
        Spinner spinner_gender = getView().findViewById(R.id.spinner_gender);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_gender = ArrayAdapter.createFromResource(getContext(),
                R.array.gender_list, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_gender.setAdapter(adapter_gender);
        spinner_gender.setOnItemSelectedListener(this);

        //Initiate Gender Spinner
        Spinner spinner_daerah = getView().findViewById(R.id.spinner_daerah);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_daerah = ArrayAdapter.createFromResource(getContext(),
                R.array.daerah_list, R.layout.custom_spinner);
        // Specify the layout to use when the list of choices appears
        adapter_daerah.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        // Apply the adapter to the spinner
        spinner_daerah.setAdapter(adapter_daerah);
        spinner_daerah.setOnItemSelectedListener(this);

        Spinner spinner_min_price = getView().findViewById(R.id.spinner_min_price);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_min_price = ArrayAdapter.createFromResource(getContext(),
                R.array.price_list, R.layout.custom_spinner);
        // Specify the layout to use when the list of choices appears
        adapter_min_price.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        // Apply the adapter to the spinner
        spinner_min_price.setAdapter(adapter_min_price);
        spinner_min_price.setOnItemSelectedListener(this);


        Spinner spinner_max_price = getView().findViewById(R.id.spinner_max_price);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_max_price = ArrayAdapter.createFromResource(getContext(),
                R.array.price_list, R.layout.custom_spinner);
        // Specify the layout to use when the list of choices appears
        adapter_max_price.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        // Apply the adapter to the spinner
        spinner_max_price.setAdapter(adapter_max_price);
        spinner_max_price.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
