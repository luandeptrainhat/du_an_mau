package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Sach;

import java.util.ArrayList;

public class Top10Adaper extends RecyclerView.Adapter<Top10Adaper.ViewHolder> {
    private Context context;
    private ArrayList<Sach> list;

    public Top10Adaper(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recyclertop10,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaSach.setText("Mã sách: "+String.valueOf(list.get(position).getMasach()));
        holder.txtTenSach.setText("Tên sách: "+list.get(position).getTensach());
        holder.txtSoluongmuon.setText("Số lần được mượn: "+list.get(position).getSoluongdamuon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
TextView txtMaSach, txtTenSach, txtSoluongmuon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSach= itemView.findViewById(R.id.txtMasach);
            txtTenSach= itemView.findViewById(R.id.txtTenSach);
            txtSoluongmuon= itemView.findViewById(R.id.txtSoluongmuon);

        }
    }
}
