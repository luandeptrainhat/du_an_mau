package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtUser, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        edtPass = findViewById(R.id.edtPass);
        edtUser = findViewById(R.id.edtUser);
        btnLogin = findViewById(R.id.btnLogin);
        ThuThuDAO thuThuDAO = new ThuThuDAO(this);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                if (thuThuDAO.checkDangNhap(user, pass)) {
                    //lưu sharedreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("matt", user);
                    editor.commit();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, user+pass+"Username hoặc mặt khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}