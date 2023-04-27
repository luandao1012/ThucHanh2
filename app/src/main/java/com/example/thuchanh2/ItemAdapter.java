package com.example.thuchanh2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh2.databinding.ItemBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    List<Model> models;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calendar = Calendar.getInstance();
    Callback callback;

    public void setData(List<Model> list) {
        models = list;
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        ItemViewHolder itemViewHolder = new ItemViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        itemViewHolder.bindListener();
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemBinding binding;

        public ItemViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            binding.tvTen.setSelected(true);
            binding.tvNoiDung.setSelected(true);
            Model model = models.get(position);
            binding.tvTen.setText(model.getTenCongViec());
            binding.tvNoiDung.setText(model.getNoiDungCongViec());
            binding.tvTinhTrang.setText(model.getTinhTrang());
            calendar.setTimeInMillis(model.getNgayHoanThanh());
            binding.tvNgay.setText(simpleDateFormat.format(calendar.getTime()));
            if (model.getCongTac()) {
                binding.tvCongTac.setText("Làm chung");
            } else {
                binding.tvCongTac.setText("Một mình");
            }
        }

        public void bindListener() {
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.OnClickItem(models.get(getAdapterPosition()));
                }
            });
        }
    }

    interface Callback {
        void OnClickItem(Model model);
    }
}
