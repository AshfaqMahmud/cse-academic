package com.ashfaq.cse_academic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    FirebaseDatabase rootnode =FirebaseDatabase.getInstance();
    DatabaseReference reference= rootnode.getReferenceFromUrl("https://cse-academic-92d65-default-rtdb.firebaseio.com/");
    /*private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText tmail=findViewById(R.id.tmail);
        final EditText tpass=findViewById(R.id.tpass);
        final EditText troll=findViewById(R.id.troll);
        final CheckBox chk=findViewById(R.id.rembrme);
        final Button btn1= findViewById(R.id.loginb);
        final Button btn2= findViewById(R.id.rgstrb);

        //loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        //loginPrefsEditor = loginPreferences.edit();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = tmail.getText().toString();
                final String pass1 = tpass.getText().toString();
                final String roll = troll.getText().toString();

                if (email.isEmpty() || pass1.isEmpty()) {
                    Toast.makeText(Login.this, "Fill Information Please", Toast.LENGTH_SHORT).show();
                }
                else {
                    reference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(roll)) {
                                final String getpass = snapshot.child(roll).child("password").getValue(String.class);
                                if (getpass.equals(pass1)) {
                                    Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    //pass value to profile page
                                    Profile profile = new Profile();
                                    FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
                                    Bundle data = new Bundle();
                                    data.putString("groll","madarfaka");
                                    profile.setArguments(data);
                                    fragmentTransaction.replace(R.id.frameLayout,profile).commit();
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Login.this, "Invalid mail or roll", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Registration.class);
                startActivity(intent);
                finish();
            }
        });
    }

}