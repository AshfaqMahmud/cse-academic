package com.ashfaq.cse_academic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference= database.getReferenceFromUrl("https://cse-academic-c58b7-default-rtdb.firebaseio.com/");
    EditText tname,temail,troll,tpass,tcpass,tphone;
    Button button;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        tname=findViewById(R.id.tname);
        temail=findViewById(R.id.tmail);
        troll=findViewById(R.id.troll);
        tpass=findViewById(R.id.tpass);
        tcpass=findViewById(R.id.tcpass);
        tphone=findViewById(R.id.tphone);
        button=findViewById(R.id.registerb);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname,email,roll,pass,cpass,phone;
                fname=tname.getText().toString();
                email=temail.getText().toString().trim();
                roll=troll.getText().toString();
                pass=tpass.getText().toString();
                cpass=tcpass.getText().toString();
                phone=tphone.getText().toString();

                if(fname.isEmpty() || email.isEmpty() || roll.isEmpty() || pass.isEmpty() || cpass.isEmpty() || phone.isEmpty())
                    Toast.makeText(Registration.this,"One or more fields are empty",Toast.LENGTH_SHORT).show();
                else
                {
                    email=email+"@stud.kuet.ac.bd";
                    if(pass.equals(cpass))
                    {
                        reference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        reference.child("users").child(roll).child("name").setValue(fname);
                        reference.child("users").child(roll).child("mail").setValue(email);
                        reference.child("users").child(roll).child("roll").setValue(roll);
                        reference.child("users").child(roll).child("password").setValue(pass);
                        reference.child("users").child(roll).child("phone").setValue(phone);
                        reference.child("users").child(roll).child("role").setValue(role);

                        Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Registration.this,Login.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Registration.this,"Password not matched",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbutton1:
                if (checked) {
                    role="Class Representatives";
                }
                    break;
            case R.id.rbutton2:
                if (checked) {
                    role="General Student";
                }
                    break;
        }
    }
}