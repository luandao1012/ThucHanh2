package com.example.thuchanh2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.thuchanh2.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    static Database database;
    private ActivityMainBinding binding;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        initListeners();
    }

    private void initViews() {
        database = new Database(getApplicationContext());
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 3);
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        Objects.requireNonNull(binding.tabLayout.getTabAt(0)).setIcon(R.drawable.ic_list);
        Objects.requireNonNull(binding.tabLayout.getTabAt(1)).setIcon(R.drawable.ic_info);
        Objects.requireNonNull(binding.tabLayout.getTabAt(2)).setIcon(R.drawable.ic_search);
    }

    private void initListeners() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                intent.putExtra("isViewOrUpdate", false);
                startActivity(intent);
            }
        });
        binding.nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_item_1)
                    binding.viewPager.setCurrentItem(0);
                else if (id == R.id.menu_item_2)
                    binding.viewPager.setCurrentItem(1);
                else if (id == R.id.menu_item_3)
                    binding.viewPager.setCurrentItem(2);
                return true;
            }
        });

        binding.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        binding.nav.getMenu().findItem(R.id.menu_item_1).setChecked(true);
                        break;
                    case 1:
                        binding.nav.getMenu().findItem(R.id.menu_item_2).setChecked(true);
                        break;
                    case 2:
                        binding.nav.getMenu().findItem(R.id.menu_item_3).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}