package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.LoaiSachDAO;
import com.example.myapplication.model.ItemClick;
import com.example.myapplication.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LoaiSach> list;
    private ItemClick itemClick;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list,ItemClick itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick=itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach_recyler, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenLoai.setText("Tên loại: "+list.get(position).getTenloai());
        holder.txtLoaiSach.setText("Mã loại: "+String.valueOf(list.get(position).getId()));
        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                int check = loaiSachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getId());
                switch (check){
                    case 1:
                        //load data
                        list.clear();
                        list = loaiSachDAO.getDSLoaiSach();
                        notifyDataSetChanged();
                        break;
                    case -1:
                        Toast.makeText(context, "Còn tồn tại sách trong loại sách này ", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa Thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSach loaiSach = list.get(holder.getAdapterPosition());
                itemClick.onClickLoaiSach(loaiSach);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLoaiSach, txtTenLoai;
        ImageView btnDel,ivEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLoaiSach=itemView.findViewById(R.id.txtMaLoaiSach);
            txtTenLoai=itemView.findViewById(R.id.txtTenLoaiSach);
            btnDel= itemView.findViewById(R.id.ivDelete);
            ivEdit= itemView.findViewById(R.id.ivEdit );
        }
    }
}
