package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LoaiSachAdapter;
import com.example.myapplication.dao.LoaiSachDAO;
import com.example.myapplication.model.ItemClick;
import com.example.myapplication.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSach_fragment extends Fragment {
    private RecyclerView recyclerView;
    LoaiSachDAO loaiSachDAO;
    EditText edtLoaiSach;
    int maloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loaisach_fragment, container, false);

        recyclerView = view.findViewById(R.id.recylerLoaisach);
        edtLoaiSach = view.findViewById(R.id.edtLoaiSach);
        Button btnThem = view.findViewById(R.id.btnThemls);
        Button btnSua = view.findViewById(R.id.btnEdit);
        loaiSachDAO = new LoaiSachDAO(getContext());


        loadData();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtLoaiSach.getText().toString();

                if (loaiSachDAO.themLoaiSach(tenloai)) {
                    //thông báo + load lại ds
                    loadData();
                    edtLoaiSach.setText("");
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenLoai = edtLoaiSach.getText().toString();
                LoaiSach loaiSach = new LoaiSach(maloai,tenLoai);
                if (loaiSachDAO.thayDoiLoaiSach(loaiSach)){
                    loadData();
                    edtLoaiSach.setText("");
                    Toast.makeText(getContext(), ""+maloai, Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(getContext(), "Thay đổi thông tin thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void loadData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<LoaiSach> list = loaiSachDAO.getDSLoaiSach();
        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(), list, new ItemClick() {
            @Override
            public void onClickLoaiSach(LoaiSach loaiSach) {
                edtLoaiSach.setText(loaiSach.getTenloai());
                maloai = loaiSach.getId();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
