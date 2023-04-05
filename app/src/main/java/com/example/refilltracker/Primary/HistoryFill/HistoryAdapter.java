package com.example.refilltracker.Primary.HistoryFill;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.refilltracker.Primary.Data.User;
import com.example.refilltracker.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

    private final List<User> dataList;

    public HistoryAdapter(List<User> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_history, parent, false);

        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        User user = dataList.get(position);

        holder.bindUI(user);

    }

    @Override
    public int getItemCount() {
        if (dataList != null) return dataList.size();
        return 0;
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        private final ImageView imgType;
        private final TextView tvTitle;
        private final TextView tvDate;
        private final TextView tvTime;
        private final TextView tvPlace;
        private final TextView tvPrice;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);

            imgType = itemView.findViewById(R.id.img_type);
            tvTitle = itemView.findViewById(R.id.tv_history_title);
            tvDate = itemView.findViewById(R.id.tv_history_date);
            tvTime = itemView.findViewById(R.id.tv_history_time);
            tvPlace = itemView.findViewById(R.id.tv_history_place);
            tvPrice = itemView.findViewById(R.id.tv_history_price);
        }

        public void bindUI(User user) {
            int imgId = R.drawable.ic_baseline_local_gas_station_24;
            String title = "Đổ xăng";
            if (user.lit.equals("")) {
                imgId = R.drawable.ic_baseline_car_repair_24;
                title = "Sửa chữa";
            }

            imgType.setImageResource(imgId);
            tvTitle.setText(title);
            tvDate.setText(user.date);
            tvTime.setText(user.time);
            tvPlace.setText(user.place);
            tvPrice.setText(String.format("%,2dđ", Integer.parseInt(user.price)*1000));
        }
    }
}
