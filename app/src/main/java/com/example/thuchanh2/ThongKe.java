package com.example.thuchanh2;

public class ThongKe {
    private String name;
    private int count;

    public ThongKe(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public ThongKe() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
