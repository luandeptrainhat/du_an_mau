package com.example.myapplication.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.PhieuMuonAdapter;
import com.example.myapplication.dao.PhieuMuonDAO;
import com.example.myapplication.dao.SachDAO;
import com.example.myapplication.dao.ThanhVienDAO;
import com.example.myapplication.model.PhieuMuon;
import com.example.myapplication.model.Sach;
import com.example.myapplication.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class PhieuMuonFagment extends Fragment {
    PhieuMuonDAO phieuMuonDAO;
    RecyclerView recyclerView;
    ArrayList<PhieuMuon> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phieumuon_fragment, container, false);

        recyclerView = view.findViewById(R.id.recylerView);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);
        loadData();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }


        });
        return view;
    }

    private void loadData() {
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getDSPhieuMuon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        PhieuMuonAdapter phieuMuonAdapter = new PhieuMuonAdapter(list, getContext());
        recyclerView.setAdapter(phieuMuonAdapter);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themphieumuon, null);
        Spinner spnThanhVien = view.findViewById(R.id.spnThanhvien);
        Spinner spnSach = view.findViewById(R.id.spnSach);

        getDaTaThanhVien(spnThanhVien);
        getDaTaSach(spnSach);
        builder.setView(view);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //laays matv


                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int matv = (int) hsTV.get("matv");
                //lay masach
                HashMap<String, Object> hsSach = (HashMap<String, Object>) spnSach.getSelectedItem();
                int masach = (int) hsSach.get("masach");
                int tien = (int) hsSach.get("giathue");

                themphieumuon(matv, masach, tien);
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void themphieumuon(int matv, int masach, int tien) {
        //lay matt
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
       String matt = sharedPreferences.getString("matt","");

        //lay ngay hien tai
        Date surrentome = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(surrentome);

        PhieuMuon phieuMuon = new PhieuMuon(matv, matt, masach, ngay, 0, tien);
        boolean kiemtra = phieuMuonDAO.themPhieuMuon(phieuMuon);
        if (kiemtra) {
            Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
            loadData();
        } else {
            Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
        }


    }

    private void getDaTaThanhVien(Spinner spnThanhVien) {
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> list = thanhVienDAO.getDSThanhVien();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (ThanhVien tv : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("matv", tv.getMatv());
            hs.put("hoten", tv.getHoten());
            listHM.add(hs);
        }
        Toast.makeText(getContext(), "noo", Toast.LENGTH_SHORT).show();

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"hoten"},
                new int[]{android.R.id.text1});
        spnThanhVien.setAdapter(simpleAdapter);
    }

    private void getDaTaSach(Spinner spnSach) {
        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<Sach> list = sachDAO.getDSDauSach();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Sach tv : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("masach", tv.getMasach());
            hs.put("tensach", tv.getTensach());
            hs.put("giathue",tv.getGiathue());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tensach"},
                new int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);
    }


}
