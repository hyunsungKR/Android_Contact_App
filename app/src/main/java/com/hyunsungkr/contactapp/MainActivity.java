package com.hyunsungkr.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hyunsungkr.contactapp.adapter.ContactAdapter;
import com.hyunsungkr.contactapp.data.DatabaseHandler;
import com.hyunsungkr.contactapp.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;

    // RecyclerView를 사용할 때
    // RecyclerView,Adapter,ArrayList를 쌍으로 적어라.
    RecyclerView recyclerView;
    ContactAdapter adapter;
    ArrayList<Contact> contactList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);

        // RecyclerView를 화면에 연결하고
        // 쌍으로 같이 다니는 코드도 작성.
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        contactList=db.getAllContacts();
        adapter = new ContactAdapter(MainActivity.this,contactList);
        recyclerView.setAdapter(adapter);
    }
}