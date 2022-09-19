package com.ashfaq.cse_academic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    LinearLayout nameField;
    Button postbtn, backbtn;
    EditText posttxt,posttitle;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference= database.getReference();
    long max_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameField = findViewById(R.id.field_nameEntry);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        FloatingActionButton fab = findViewById(R.id.fab);
        postbtn=findViewById(R.id.button_confirm);
        backbtn=findViewById(R.id.button_back);
        posttxt=findViewById(R.id.noticebox);
        posttitle=findViewById(R.id.noticetitle);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        return true;
                    case R.id.notice:
                        startActivity(new Intent(getApplicationContext(), Notice.class));
                        overridePendingTransition(1,1);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),UserProfile.class));
                        overridePendingTransition(1,1);
                        return true;
                }
                return false;
            }
        });
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("user-db", Context.MODE_PRIVATE);
        String name_shared = sharedPreferences.getString("user-name","");
        String role_shared = sharedPreferences.getString("user-role","");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(role_shared.equals("Class Representatives"))
                {
                    nameField.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.INVISIBLE);
                    bottomNavigationView.setVisibility(View.INVISIBLE);
                }
                else
                {
                    nameField.setVisibility(View.INVISIBLE);
                    Snackbar.make(view, "Not Authorized", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameField.setVisibility(View.INVISIBLE);
                fab.setVisibility(View.VISIBLE);
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        });
        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("notice").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            max_id=snapshot.getChildrenCount();
                            String post=posttxt.getText().toString();
                            String title=posttitle.getText().toString();
                            Calendar calendar=Calendar.getInstance();
                            String currentDate= DateFormat.getInstance().format(calendar.getTime());
                            reference.child("notice").child(String.valueOf(max_id+1)).child("PostTitle").setValue(title);
                            reference.child("notice").child(String.valueOf(max_id+1)).child("Post").setValue(post);
                            reference.child("notice").child(String.valueOf(max_id+1)).child("PostedBy").setValue(name_shared);
                            reference.child("notice").child(String.valueOf(max_id+1)).child("PostedOn").setValue(currentDate);
                            Snackbar.make(view, "Notice Posted", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            posttxt.setText("");
                            nameField.setVisibility(View.INVISIBLE);
                            fab.setVisibility(View.VISIBLE);
                            bottomNavigationView.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            max_id=0;
                            String post=posttxt.getText().toString();
                            String title=posttitle.getText().toString();
                            Calendar calendar=Calendar.getInstance();
                            String currentDate= DateFormat.getInstance().format(calendar.getTime());
                            reference.child("notice").child(String.valueOf(max_id+1)).child("PostTitle").setValue(title);
                            reference.child("notice").child(String.valueOf(max_id+1)).child("Post").setValue(post);
                            reference.child("notice").child(String.valueOf(max_id+1)).child("PostedBy").setValue(name_shared);
                            reference.child("notice").child(String.valueOf(max_id+1)).child("PostedOn").setValue(currentDate);
                            Snackbar.make(view, "Notice Posted", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }

    public void driveOpen(View view) {
        Snackbar.make(view, "Under development", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void fbGroup(View view) {
        Snackbar.make(view, "Under development", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public void tubeOpen(View view) {
        Snackbar.make(view, "Under development", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public void whatsAppOpen(View view) {
        Snackbar.make(view, "Under development", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public void showAll(View view) {
        Snackbar.make(view, "Under development", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public void openKuet(View view) {
        Snackbar.make(view, "Under development", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public void chatWithCR(View view) {
        Snackbar.make(view, "Under development", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public void newLink(View view) {
        Snackbar.make(view, "Under development", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public void ratefeedback(View view) {
        Snackbar.make(view, "Under development", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}