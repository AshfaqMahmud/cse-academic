package com.ashfaq.cse_academic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    TextView tname,troll,tphone,tmail,trole;
    FirebaseDatabase rootnode =FirebaseDatabase.getInstance();
    DatabaseReference reference= rootnode.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        tname = findViewById(R.id.tname);
        trole = findViewById(R.id.trole);
        tmail = findViewById(R.id.tmail);
        troll = findViewById(R.id.troll);
        tphone = findViewById(R.id.tphone);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.profile);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(1, 1);
                        return true;
                    case R.id.profile:
                        return true;
                    case R.id.notice:
                        startActivity(new Intent(getApplicationContext(), Notice.class));
                        overridePendingTransition(1, 1);
                        return true;
                }
                return false;
            }
        });
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("user-db",Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPreferences.edit();

        String mystr = sharedPreferences.getString("user-roll","");

        reference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name = snapshot.child(mystr).child("name").getValue(String.class);
                String mail = snapshot.child(mystr).child("mail").getValue(String.class);
                String phone = snapshot.child(mystr).child("phone").getValue(String.class);
                String roll = snapshot.child(mystr).child("roll").getValue(String.class);
                String role = snapshot.child(mystr).child("role").getValue(String.class);

                tname.setText(name);
                tmail.setText(mail);
                tphone.setText(phone);
                trole.setText(role);
                troll.setText(roll);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}