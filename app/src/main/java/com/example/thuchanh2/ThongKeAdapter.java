package com.example.thuchanh2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh2.databinding.ItemBinding;
import com.example.thuchanh2.databinding.ItemThongKeBinding;

import java.util.List;

public class ThongKeAdapter extends RecyclerView.Adapter<ThongKeAdapter.ThongKeViewHolder> {
    private List<ThongKe> thongKes;

    public void setData(List<ThongKe> list) {
        thongKes = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThongKeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThongKeViewHolder(ItemThongKeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return thongKes.size();
    }

    class ThongKeViewHolder extends RecyclerView.ViewHolder {
        private final ItemThongKeBinding binding;

        public ThongKeViewHolder(ItemThongKeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            binding.tvName.setText(thongKes.get(position).getName());
            binding.tvCount.setText(String.valueOf(thongKes.get(position).getCount()));
        }
    }
}
