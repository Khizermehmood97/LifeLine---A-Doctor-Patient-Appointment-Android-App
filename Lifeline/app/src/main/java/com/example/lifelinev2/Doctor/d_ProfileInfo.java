package com.example.lifelinev2.Doctor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lifelinev2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link d_ProfileInfo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link d_ProfileInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class d_ProfileInfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private FirebaseAuth mAuth;
    DatabaseReference ref;

    public d_ProfileInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment d_ProfileInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static d_ProfileInfo newInstance(String param1, String param2) {
        d_ProfileInfo fragment = new d_ProfileInfo();
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
        final View view =inflater.inflate(R.layout.d_fragment_profile_info, container, false);
        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView Name = (TextView) getView().findViewById(R.id.NameInfo) ;
        final TextView  Email = (TextView) getView().findViewById(R.id.EmailInfo);
        final TextView  Phone = (TextView) getView().findViewById(R.id.PhoneInfo);
        final TextView Category = (TextView) getView().findViewById(R.id.CategoryInfo);
        //final TextView  LastDonated = (TextView) getView().findViewById(R.id.LastDonatedInfo);
        final TextView  Availabily = (TextView) getView().findViewById(R.id.AvailableInfo);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String userId = firebaseUser.getUid();


        ref = FirebaseDatabase.getInstance().getReference("Doctors:").child(userId);
        //ref.child("Name");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                String phone = dataSnapshot.child("phone").getValue().toString();
                String category = dataSnapshot.child("category").getValue().toString();
//                String Ldonated = dataSnapshot.child("last_Donated").getValue().toString();
                String availability = dataSnapshot.child("availability").getValue().toString();
                Name.setText("Name : "+ name);
                Email.setText("Email : "+email);
                Phone.setText("Phone Number : "+ phone);
                Category.setText("Category : "+category);
               // LastDonated.setText("Last Donated : "+ Ldonated);
                Availabily.setText("Doctor Status : "+availability);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
