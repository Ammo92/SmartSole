package com.developpement.guide.solesmart;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

import static android.content.ContentValues.TAG;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Step.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Step#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Step extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Capteur accelerometre;
    private OnFragmentInteractionListener mListener;
    TextView stepValue;
    TextView calValue;
    Sensor sensor;
    Date now = new Date();
    Context context;
    InfoBDD information;
    Information isExist;
    Information addInfo;
    private int pas = 0;

    public Step() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Step.
     */
    // TODO: Rename and change types and number of parameters
    public static Step newInstance(String param1, String param2) {
        Step fragment = new Step();
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
        context = getContext();
        information = new InfoBDD(context);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_step, container, false);
        stepValue = rootView.findViewById(R.id.stepValue);
        calValue = rootView.findViewById(R.id.valueCal);
        SensorManager sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);

        DateFormat dateformatter = new SimpleDateFormat("MM");
        DateFormat dateformatter1 = new SimpleDateFormat("dd");
        DateFormat dateformatter2 = new SimpleDateFormat("YYYY");

        final String year = dateformatter2.format(now);
        final String day = dateformatter1.format(now);
        final String month = dateformatter.format(now);
        final String monthFinal =month.replaceAll("^0", ""); // Enlève UN zéro en début de ligne
        final String dateNow = day + "/" + monthFinal + "/" + year;



        sensorManager.registerListener(new SensorEventListener() {
                                      @Override
                                      public void onSensorChanged(SensorEvent event) {
                                          
                                          float steps = event.values[0];
                                          double cal = steps * 0.04;
                                          String valueCal = String.valueOf(cal);

                                          stepValue.setText((int) steps + "");
                                          calValue.setText(valueCal);
                                          isExist = information.getInfoWithDate(dateNow);
                                          if(isExist == null){
                                              addInfo = new Information(cal,steps,dateNow);
                                              information.insertInfo(addInfo);
                                          }else{
                                              addInfo = new Information(cal,steps,dateNow);
                                              int id = addInfo.getId() + 1;
                                              information.updateInfo(id,addInfo);
                                          }



                                      }
                                      @Override
                                      public void onAccuracyChanged(Sensor sensor, int accuracy) {
                                      }
                                  }, sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),
                SensorManager.SENSOR_DELAY_UI);
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




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
