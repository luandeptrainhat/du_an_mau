package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.ThuThuDAO;
import com.example.myapplication.fragment.LoaiSach_fragment;
import com.example.myapplication.fragment.PhieuMuonFagment;
import com.example.myapplication.fragment.ThongKeTop10_fragment;
import com.example.myapplication.fragment.Thongke_doanhthu_Fragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_24);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.phieumuon:
                        fragment = new PhieuMuonFagment();
                        break;
                    case R.id.loaisach:
                        fragment = new LoaiSach_fragment();
                        break;
                    case R.id.logout:
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    case R.id.doimk:
                        showDialogDoiMK();
                        break;
                    case R.id.top10:
                        fragment = new ThongKeTop10_fragment();
                        break;
                    case R.id.doanhthu:
                        fragment = new Thongke_doanhthu_Fragment();
                        break;
                    default:
                        fragment = new PhieuMuonFagment();
                        break;
                }
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.frameLayout, fragment)
                            .commit();
                    toolbar.setTitle(item.getTitle());
                }


                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });
    }

    private void showDialogDoiMK() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setNegativeButton("Cập nhật", null)
                .setPositiveButton("Hủy", null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.doi_mk_dialog, null);
        EditText edtoldPass = view.findViewById(R.id.edtpassOld);
        EditText edtnewpaas = view.findViewById(R.id.edtnewPass);
        EditText edtrenewpass = view.findViewById(R.id.edtrenewpass);


        builder.setView(view);
//        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String oldPass = edtoldPass.getText().toString();
//                String newPass = edtnewpaas.getText().toString();
//                String renewPass = edtrenewpass.getText().toString();
//                if (newPass.equals(renewPass)) {
//                    //cap nhat
//                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
//                    String matt = sharedPreferences.getString("matt", "");
//                    ThuThuDAO thuThuDAO = new ThuThuDAO(HomeActivity.this);
//                    boolean check = thuThuDAO.capnhatMK(matt, oldPass, newPass);
//                    if (check) {
//                        Toast.makeText(HomeActivity.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(HomeActivity.this, "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                } else {
//                    Toast.makeText(HomeActivity.this, "Nhập mật khẩu không trùng với nhau", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtoldPass.getText().toString();
                String newPass = edtnewpaas.getText().toString();
                String renewPass = edtrenewpass.getText().toString();
                if (newPass.equals(renewPass)) {
                    //cap nhat
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                    String matt = sharedPreferences.getString("matt", "");
                    ThuThuDAO thuThuDAO = new ThuThuDAO(HomeActivity.this);
                    boolean check = thuThuDAO.capnhatMK(matt, oldPass, newPass);
                    if (check) {
                        Toast.makeText(HomeActivity.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(HomeActivity.this, "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(HomeActivity.this, "Nhập mật khẩu không trùng với nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}