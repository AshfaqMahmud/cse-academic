package com.ashfaq.cse_academic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder>{
    Context context;
    ArrayList<Notices> list;

    public NoticeAdapter(Context context, ArrayList<Notices> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.noticeitem,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Notices notices =list.get(position);
        holder.tsl.setText(notices.getSl());
        holder.ttitle.setText(notices.getTitle());
        holder.ttxt.setText(notices.getNotice());
        holder.tdate.setText(notices.getDate());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tsl,ttitle,ttxt,tdate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tsl= itemView.findViewById(R.id.tsl);
            ttitle= itemView.findViewById(R.id.ttitle);
            ttxt=itemView.findViewById(R.id.tnotice);
            tdate=itemView.findViewById(R.id.tdate);
        }
    }
}
