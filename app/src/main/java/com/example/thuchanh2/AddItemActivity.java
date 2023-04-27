package com.example.thuchanh2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.example.thuchanh2.databinding.ActivityAddItemBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddItemActivity extends AppCompatActivity {
    private ActivityAddItemBinding binding;
    private Calendar calendar;
    private Boolean isViewOrUpdate;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        initListeners();
    }

    private void initListeners() {
        binding.edtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentDay = calendar.get(Calendar.DATE);
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentYear = calendar.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(AddItemActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        binding.edtNgay.setText(d + "/" + (m + 1) + "/" + y);
                        setCalendar(y, m, d);
                    }
                }, currentYear, currentMonth, currentDay);
                dialog.show();
            }
        });
        binding.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = binding.edtTen.getText().toString().trim();
                String noiDung = binding.edtNoiDung.getText().toString().trim();
                long ngay = calendar.getTimeInMillis();
                String tinhTrang = binding.spinner.getSelectedItem().toString();
                boolean congTac = binding.cbTinhTrang.isChecked();
                if (check(ten, noiDung, binding.edtNgay.getText().toString())) {
                    Model newModel = new Model(ten, noiDung, ngay, tinhTrang, congTac);
                    if (!isViewOrUpdate) {
                        MainActivity.database.insertModel(newModel);
                    } else {
                        MainActivity.database.updateModel(newModel, model.getId());
                    }
                    onBackPressed();
                }
            }
        });
        binding.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddItemActivity.this);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.database.deleteModel(model.getId());
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
        binding.btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initViews() {
        calendar = Calendar.getInstance();
        Intent intent = getIntent();
        isViewOrUpdate = intent.getBooleanExtra("isViewOrUpdate", false);
        if (isViewOrUpdate) {
            binding.btnThem.setText("Sửa");
            binding.btnXoa.setVisibility(View.VISIBLE);
            model = (Model) intent.getSerializableExtra("sendModel");
            binding.edtTen.setText(model.getTenCongViec());
            binding.edtNoiDung.setText(model.getNoiDungCongViec());
            calendar.setTimeInMillis(model.getNgayHoanThanh());
            binding.edtNgay.setText(simpleDateFormat.format(calendar.getTime()));
            binding.cbTinhTrang.setChecked(model.getCongTac());
            String tinhTrang = model.getTinhTrang();
            if (tinhTrang.equals("Chưa thực hiện")) {
                binding.spinner.setSelection(0);
            } else if (tinhTrang.equals("Đang thực hiện")) {
                binding.spinner.setSelection(1);
            } else {
                binding.spinner.setSelection(2);
            }
        } else {
            binding.btnThem.setText("Thêm");
            binding.btnXoa.setVisibility(View.GONE);
        }
    }

    private void setCalendar(int y, int m, int d) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.YEAR, y);
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DATE, d);
    }

    private boolean check(String ten, String noiDung, String ngay) {
        if (ten.isEmpty()) {
            binding.edtTen.setError("Không để trống");
            binding.edtTen.requestFocus();
            return false;
        }
        if (noiDung.isEmpty()) {
            binding.edtNoiDung.setError("Không để trống");
            binding.edtNoiDung.requestFocus();
            return false;
        }
        if (ngay.equals("Chọn ngày")) {
            binding.edtNgay.setError("Chọn ngày");
            binding.edtNgay.requestFocus();
            return false;
        }
        return true;
    }
}