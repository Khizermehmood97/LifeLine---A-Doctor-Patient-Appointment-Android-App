package com.example.lifelinev2.Patient;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lifelinev2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileEdit.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileEdit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileEdit extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

   // RadioGroup radioGroup;
  //  RadioButton radioButton;
    Spinner bloodGroup, LastCheckup;
    private FirebaseAuth mAuth;
    DatabaseReference ref;

    String bloodgroup;
    String lastcheckup;

    public ProfileEdit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment d_ProfileEdit.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileEdit newInstance(String param1, String param2) {
        ProfileEdit fragment = new ProfileEdit();
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
         final View view =inflater.inflate(R.layout.p_fragment_profile_edit, container, false);
         return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText  Name = (EditText) getView().findViewById(R.id.Name);
        final EditText  Allergy = (EditText) getView().findViewById(R.id.Allergy);
        final EditText  Phone = (EditText) getView().findViewById(R.id.Phone);

     //   radioGroup = getView().findViewById(R.id.radio_status);
         mAuth = FirebaseAuth.getInstance();


        bloodGroup = (Spinner) getView().findViewById(R.id.spinner_bloodGroup);
        ArrayAdapter <CharSequence> adapter1 = ArrayAdapter.createFromResource(getView().getContext(),R.array.bloodgroup,android.R.layout.simple_spinner_item );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroup.setAdapter(adapter1);
        bloodGroup.setOnItemSelectedListener(new BloodSpinner());

       LastCheckup = (Spinner) getView().findViewById(R.id.spinner_lastDonated);
        ArrayAdapter <CharSequence> adapter2 = ArrayAdapter.createFromResource(getView().getContext(),R.array.lastcheckup,android.R.layout.simple_spinner_item );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LastCheckup.setAdapter(adapter2);
        LastCheckup.setOnItemSelectedListener(new LastSpinner());


        Button btn = (Button) getView().findViewById(R.id.Edit_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = Name.getText().toString().trim();
                final String phone = Phone.getText().toString().trim();
                final String allergy = Allergy.getText().toString().trim();


            //    radioButton = getView().findViewById(radioGroup.getCheckedRadioButtonId());
            //    final String status = (String) radioButton.getText();

                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                String userId = firebaseUser.getUid();


                 ref = FirebaseDatabase.getInstance().getReference("Patient:").child(userId);

                HashMap <String,Object> hashMap = new HashMap<>();
                hashMap.put("blood_Group", bloodgroup);
                hashMap.put("last_Checkup", lastcheckup);
                hashMap.put("name", name);
                hashMap.put("allergy", allergy);
                hashMap.put("phone", phone);
                //hashMap.put("status", status);



                ref.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                           Toast.makeText(getView().getContext(), "PROFILE UPDATED SUCCESSFULLY.", Toast.LENGTH_LONG).show();
                        }
                       else {
                            Toast.makeText(getView().getContext(),task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    class  BloodSpinner implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            bloodgroup = adapterView.getItemAtPosition(i).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    class LastSpinner implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            lastcheckup = adapterView.getItemAtPosition(i).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
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





