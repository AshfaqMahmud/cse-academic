package com.ashfaq.cse_academic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
    TextView tname,troll,tphone,tmail,trole;
    FirebaseDatabase rootnode =FirebaseDatabase.getInstance();
    DatabaseReference reference= rootnode.getReferenceFromUrl("https://cse-academic-92d65-default-rtdb.firebaseio.com/");
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tname = view.findViewById(R.id.tname);
        trole = view.findViewById(R.id.trole);
        tmail = view.findViewById(R.id.tmail);
        troll = view.findViewById(R.id.troll);
        tphone = view.findViewById(R.id.tphone);

        Bundle data = this.getArguments();
        //if (data != null)
        //
        //{
        //String tmp= data.getString("groll");
        String mystr="1807098";
            reference.child("users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    //String name = snapshot.child(mystr).child("name").getValue(String.class);
                    String mail = snapshot.child(mystr).child("mail").getValue(String.class);
                    String phone = snapshot.child(mystr).child("phone").getValue(String.class);
                    String roll = snapshot.child(mystr).child("roll").getValue(String.class);
                    String role = snapshot.child(mystr).child("role").getValue(String.class);

                    //tname.setText(tmp);
                    tmail.setText(mail);
                    tphone.setText(phone);
                    trole.setText(role);
                    troll.setText(roll);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        //}
        return view;
    }
}