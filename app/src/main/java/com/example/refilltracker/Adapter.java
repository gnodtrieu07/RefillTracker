package com.example.refilltracker;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.refilltracker.Fill.InfoCost;
import com.example.refilltracker.Fill.InfoFuel;
import com.example.refilltracker.Primary.Data.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<InfoFuel> danhsach;
    private Context context;

    public Adapter(Context context, ArrayList<InfoFuel> danhsach) {
        this.context = context;
        this.danhsach = danhsach;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_history, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        InfoFuel p = danhsach.get(position);
        if (p == null)
            return;
        holder.tvTime.setText(p.getTime());
        holder.tvDate.setText(p.getDate());
        holder.tvPlace.setText(p.getPlace());
        holder.tvPrice.setText(p.getPrice());

        holder.bindUI(p);
    }

    public void release() {
        context = null;
    }


    @Override
    public int getItemCount() {
        if (danhsach != null)
            return danhsach.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView tvTime, tvDate, tvPlace, tvPrice, tvTitle;
        ImageView imgType;
        View layout;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgType = itemView.findViewById(R.id.img_type);
            tvTitle = itemView.findViewById(R.id.tv_history_title);
            tvTime = itemView.findViewById(R.id.tv_history_time);
            tvDate = itemView.findViewById(R.id.tv_history_date);
            tvPlace = itemView.findViewById(R.id.tv_history_place);
            tvPrice = itemView.findViewById(R.id.tv_history_price);

            layout = itemView.findViewById(R.id.layoutNear);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(), 147, 0, "Edit");
            menu.add(this.getAdapterPosition(), 852, 1, "Delete");
        }

        public void bindUI(InfoFuel p) {
            int imgId = R.drawable.ic_baseline_local_gas_station_24;
            String title = "Đổ xăng";
            if (p.lit.equals("")) {
                imgId = R.drawable.ic_baseline_car_repair_24;
                title = "Sửa chữa";
            }
            imgType.setImageResource(imgId);
            tvTitle.setText(title);
        }
    }
}