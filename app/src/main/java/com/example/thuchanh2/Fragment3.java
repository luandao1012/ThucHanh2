package com.example.thuchanh2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thuchanh2.databinding.Fragment3Binding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Fragment3 extends Fragment {
    private Fragment3Binding binding;
    private ItemAdapter itemAdapter;
    private ThongKeAdapter thongKeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = Fragment3Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initListeners();
    }

    private void initViews() {
        itemAdapter = new ItemAdapter();
        thongKeAdapter = new ThongKeAdapter();
    }

    private void initListeners() {
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ThongKe> thongKeList = new ArrayList<>();
                List<String> status = MainActivity.database.getAllStatus();
                for (String i: status){
                    thongKeList.add(new ThongKe(i, MainActivity.database.thongKe(i).size()));
                }
                thongKeList.sort(new Comparator<ThongKe>() {
                    @Override
                    public int compare(ThongKe t0, ThongKe t1) {
                        return t0.getCount() - t1.getCount();
                    }
                });
                thongKeAdapter.setData(thongKeList);
                binding.rv.setAdapter(thongKeAdapter);
            }
        });
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty())
                    itemAdapter.setData(MainActivity.database.searchModelByKey(charSequence.toString()));
                else itemAdapter.setData(new ArrayList<Model>());
                binding.rv.setAdapter(itemAdapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}