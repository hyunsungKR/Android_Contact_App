package com.hyunsungkr.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyunsungkr.contactapp.data.DatabaseHandler;
import com.hyunsungkr.contactapp.model.Contact;

public class AddActivity extends AppCompatActivity {

    EditText editName;
    EditText editPhone;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();

                // 이름과 전화번호가 모두 있어야한다.!
                if(name.isEmpty()||phone.isEmpty()){
                    Toast.makeText(AddActivity.this,"필수항목입니다. 입력해주세요.",Toast.LENGTH_SHORT).show();
                    return;
                }

                // 묶어서 처리할 contact 객체를 하나 만든다.
                Contact contact = new Contact(name,phone);

                // DB에 저장한다.
                DatabaseHandler db = new DatabaseHandler(AddActivity.this);
                db.addContact(contact);

                // 유저한테 잘 저장되었다고, 알려주고
                Toast.makeText(AddActivity.this, "잘 저장되었습니다.", Toast.LENGTH_SHORT).show();


                // 액티비티를 종료한다.
                finish();


            }
        });

    }
}