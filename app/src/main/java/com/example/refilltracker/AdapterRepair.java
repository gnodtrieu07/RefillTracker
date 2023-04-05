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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterRepair extends RecyclerView.Adapter<AdapterRepair.ViewHolder> {

    private ArrayList<InfoCost> danhsach2;
    private Context context;

    public AdapterRepair(Context context, ArrayList<InfoCost> danhsach2) {
        this.context = context;
        this.danhsach2 = danhsach2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_history_repair, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        InfoCost c = danhsach2.get(position);
        if (c == null)
            return;
        holder.tvTime.setText(c.getTime());
        holder.tvDate.setText(c.getDate());
        holder.tvType.setText(c.getType());
        holder.tvPrice.setText(c.getPrice());

        holder.bindUI(c);
    }

    public void release() {
        context = null;
    }


    @Override
    public int getItemCount() {
        if (danhsach2 != null)
            return danhsach2.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView tvTime, tvDate, tvPrice, tvTitle, tvType;
        ImageView imgType;
        View layout;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgType = itemView.findViewById(R.id.img_typeR);
            tvTitle = itemView.findViewById(R.id.tv_history_titleR);
            tvTime = itemView.findViewById(R.id.tv_history_timeR);
            tvDate = itemView.findViewById(R.id.tv_history_dateR);
            tvType = itemView.findViewById(R.id.tv_history_type);
            tvPrice = itemView.findViewById(R.id.tv_history_priceR);

            layout = itemView.findViewById(R.id.layoutNearRepair);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(), 369, 0, "Edit");
            menu.add(this.getAdapterPosition(), 951, 1, "Delete");
        }

        public void bindUI(InfoCost p) {
            int imgId = R.drawable.ic_baseline_car_repair_24;
            String title = "Sửa chữa";
            imgType.setImageResource(imgId);
            tvTitle.setText(title);
        }
    }
}