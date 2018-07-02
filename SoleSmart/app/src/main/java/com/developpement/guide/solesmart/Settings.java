package com.developpement.guide.solesmart;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Settings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings extends Fragment implements View.OnClickListener {

    Switch bluetoothSwitch;
    Button french;
    Button english;



    @Override
    public void onClick(View v) {
        Context appContext = getContext().getApplicationContext();
        switch (v.getId()) {
            case R.id.switchBuetooth:
                if(bluetoothSwitch.isChecked()){
                    if (!bluetoothAdapter.isEnabled()) {
                        bluetoothAdapter.enable();
                        Log.e("Bluetooth :", " Activer");
                    }
                }else{
                    if (bluetoothAdapter.isEnabled()) {
                        bluetoothAdapter.disable();
                        Log.e("Bluetooth :", " Désasctiver");
                    }
                }
                break;

            case R.id.buttonFrench:


                String language  = "fr";
                Locale locale = new Locale(language);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getResources().updateConfiguration(config,  appContext.getResources().getDisplayMetrics());
                break;

            case R.id.buttonEnglish:

                String languageEn  = "en";
                Locale localeEn = new Locale(languageEn);
                Locale.setDefault(localeEn);
                Configuration configEn = new Configuration();
                configEn.locale = localeEn;
                getResources().updateConfiguration(configEn, appContext.getResources().getDisplayMetrics());
                break;


        }

    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

  //  @BindView(R.id.switchBuetooth) Switch bluetoothSwitch;
        public Settings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Settings.
     */
    // TODO: Rename and change types and number of parameters
    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        bluetoothSwitch = rootView.findViewById(R.id.switchBuetooth);
        french = rootView.findViewById(R.id.buttonFrench);
        english = rootView.findViewById(R.id.buttonEnglish);
        bluetoothSwitch.setOnClickListener(this);
        french.setOnClickListener(this);
        english.setOnClickListener(this);


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;

        if (context instanceof Activity){
            a=(Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
