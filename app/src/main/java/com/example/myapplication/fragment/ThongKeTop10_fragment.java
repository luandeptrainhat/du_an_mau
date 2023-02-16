package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.Top10Adaper;
import com.example.myapplication.dao.ThongKeDAO;
import com.example.myapplication.model.Sach;

import java.util.ArrayList;

public class ThongKeTop10_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thongke_fragment,container,false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerTop10);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        ArrayList<Sach> list = thongKeDAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Top10Adaper top10Adaper = new Top10Adaper(getContext(),list);
        recyclerView.setAdapter(top10Adaper);

        return view;
    }
}
