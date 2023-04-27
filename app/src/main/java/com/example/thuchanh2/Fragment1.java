package com.example.thuchanh2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thuchanh2.databinding.Fragment1Binding;

public class Fragment1 extends Fragment implements ItemAdapter.Callback {
    private Fragment1Binding binding;
    private ItemAdapter itemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        itemAdapter.setData(MainActivity.database.getModels());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initListeners();
    }

    private void initViews() {
        itemAdapter = new ItemAdapter();
        binding.rv.setAdapter(itemAdapter);
    }

    private void initListeners() {
        itemAdapter.setCallback(this);
    }

    @Override
    public void OnClickItem(Model model) {
        Intent intent = new Intent(getActivity(), AddItemActivity.class);
        intent.putExtra("isViewOrUpdate", true);
        intent.putExtra("sendModel", model);
        startActivity(intent);
    }
}