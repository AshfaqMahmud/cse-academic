package com.ashfaq.cse_academic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Notice extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Notices> list;
    DatabaseReference databaseReference;
    NoticeAdapter noticeAdapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Notice.this,MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        recyclerView=findViewById(R.id.recycler);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noticeAdapter=new NoticeAdapter(this,list);
        recyclerView.setAdapter(noticeAdapter);

        databaseReference.child("notice").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    String tsl = dataSnapshot.getKey();
                    String title=dataSnapshot.child("PostTitle").getValue(String.class);
                    String post=dataSnapshot.child("Post").getValue(String.class);
                    String date=dataSnapshot.child("PostedOn").getValue(String.class);
                    Notices notices= new Notices(tsl,title,post,date);
                    list.add(notices);
                    Toast.makeText(Notice.this,"ADEED",Toast.LENGTH_SHORT).show();
                }
                noticeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Notice.this," "+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });


        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.notice);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(1,1);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),UserProfile.class));
                        overridePendingTransition(1,1);
                        return true;
                    case R.id.notice:
                        return true;
                }
                return false;
            }
        });
    }


}