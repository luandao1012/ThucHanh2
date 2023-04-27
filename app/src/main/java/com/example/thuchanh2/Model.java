package com.example.thuchanh2;

import java.io.Serializable;

public class Model implements Serializable {
    private int id;
    private String tenCongViec;
    private String noiDungCongViec;
    private Long ngayHoanThanh;
    private String tinhTrang;
    private Boolean congTac;

    public Model(int id, String tenCongViec, String noiDungCongViec, Long ngayHoanThanh, String tinhTrang, Boolean congTac) {
        this.id = id;
        this.tenCongViec = tenCongViec;
        this.noiDungCongViec = noiDungCongViec;
        this.ngayHoanThanh = ngayHoanThanh;
        this.tinhTrang = tinhTrang;
        this.congTac = congTac;
    }

    public Model(String tenCongViec, String noiDungCongViec, Long ngayHoanThanh, String tinhTrang, Boolean congTac) {
        this.tenCongViec = tenCongViec;
        this.noiDungCongViec = noiDungCongViec;
        this.ngayHoanThanh = ngayHoanThanh;
        this.tinhTrang = tinhTrang;
        this.congTac = congTac;
    }

    public Model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public String getNoiDungCongViec() {
        return noiDungCongViec;
    }

    public void setNoiDungCongViec(String noiDungCongViec) {
        this.noiDungCongViec = noiDungCongViec;
    }

    public Long getNgayHoanThanh() {
        return ngayHoanThanh;
    }

    public void setNgayHoanThanh(Long ngayHoanThanh) {
        this.ngayHoanThanh = ngayHoanThanh;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Boolean getCongTac() {
        return congTac;
    }

    public void setCongTac(Boolean congTac) {
        this.congTac = congTac;
    }
}
