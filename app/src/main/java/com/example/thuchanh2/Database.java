package com.example.thuchanh2;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "database.db";
    private final static int DATABASE_VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCategory = "create table  model(" +
                "id integer primary key autoincrement," +
                "name text," +
                "content text," +
                "date integer," +
                "status text," +
                "collaborate integer)";
        sqLiteDatabase.execSQL(sqlCategory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertModel(Model model) {
        String sql = "insert into model(name, content, date, status, collaborate) " +
                "values(?, ?, ?, ?, ?)";
        int collaborate = 0;
        if (model.getCongTac()) collaborate = 1;
        String[] args = {model.getTenCongViec(), model.getNoiDungCongViec(), String.valueOf(model.getNgayHoanThanh()),
                model.getTinhTrang(), String.valueOf(collaborate)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, args);
    }

    public List<Model> getModels() {
        List<Model> list = new ArrayList<>();
        String sql = "select m.id, m.name, m.content, m.date, m.status, m.collaborate " +
                "from model m";
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.rawQuery(sql, null);
        while (rs != null && rs.moveToNext()) {
            boolean collaborate = false;
            if (rs.getInt(5) == 1) collaborate = true;
            list.add(new Model(rs.getInt(0), rs.getString(1), rs.getString(2),
                    rs.getLong(3), rs.getString(4), collaborate));
        }
        assert rs != null;
        rs.close();
        return list;
    }

    public void updateModel(Model model, int id) {
        String sql = "update model set name = ?, content=? ,date=? ,status=?, collaborate=? WHERE id = ?";
        int collaborate = 0;
        if (model.getCongTac()) collaborate = 1;
        String[] args = {model.getTenCongViec(), model.getNoiDungCongViec(), String.valueOf(model.getNgayHoanThanh()),
                model.getTinhTrang(), String.valueOf(collaborate), String.valueOf(id)};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    public void deleteModel(int id) {
        String sql = "DELETE FROM model WHERE id = ?";
        String[] args = {Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    public List<Model> searchModelByKey(String key) {
        List<Model> list = new ArrayList<>();
        String sql = "select m.id, m.name, m.content, m.date, m.status, m.collaborate " +
                "from model m " +
                "where m.name like ? or m.content like ? ORDER BY date ASC";
        String[] agrs = {"%" + key + "%", "%" + key + "%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.rawQuery(sql, agrs);
        while (rs != null && rs.moveToNext()) {
            boolean collaborate = false;
            if (rs.getInt(5) == 1) collaborate = true;
            list.add(new Model(rs.getInt(0), rs.getString(1), rs.getString(2),
                    rs.getLong(3), rs.getString(4), collaborate));
        }
        rs.close();
        return list;
    }

    public List<String> getAllStatus() {
        List<String> results = new ArrayList<>();
        String sql = "SELECT DISTINCT status FROM model";
        SQLiteDatabase st = getReadableDatabase();
        Cursor cursor = st.rawQuery(sql, null);
        while (cursor != null && cursor.moveToNext()) {
            results.add(cursor.getString(0));
        }
        cursor.close();
        return results;
    }

    public List<Model> thongKe(String key) {
        List<Model> list = new ArrayList<>();
        String sql = "select m.id, m.name, m.content, m.date, m.status, m.collaborate " +
                "from model m " +
                "where  m.status like ? ORDER BY date ASC";
        String[] agrs = {"%" + key + "%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.rawQuery(sql, agrs);
        while (rs != null && rs.moveToNext()) {
            boolean collaborate = false;
            if (rs.getInt(5) == 1) collaborate = true;
            list.add(new Model(rs.getInt(0), rs.getString(1), rs.getString(2),
                    rs.getLong(3), rs.getString(4), collaborate));
        }
        rs.close();
        return list;
    }
//
//    public Item getItemById(int id) {
//        String where = "id=?";
//        String[] agrs = {Integer.toString(id)};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("items", null, where, agrs, null,
//                null, null);
//        if (rs != null && rs.moveToNext()) {
//            return new Item(rs.getInt(0), rs.getString(1), (float) rs.getDouble(3),
//                    rs.getString(4), new Category(rs.getInt(2), ""));
//
//        }
//        return null;
//    }
//
//    public int updateItem(Item t) {
//        ContentValues values = new ContentValues();
//        values.put("name", t.getName());
//        values.put("cid", t.getCategory().getId());
//        values.put("price", t.getPrice());
//        values.put("date", t.getDate());
//        String where = "id=?";
//        String[] agrs = {Integer.toString(t.getId())};
//        SQLiteDatabase st = getWritableDatabase();
//        return st.update("items", values, where, agrs);
//    }
//
//    public int deleteItem(int id) {
//        String where = "id=?";
//        String[] agrs = {Integer.toString(id)};
//        SQLiteDatabase st = getWritableDatabase();
//        return st.delete("items", where, agrs);
//    }
//
//
//    public List<Item> getItemByfromPricetoPrice(double from, double to) {
//        List<Item> list = new ArrayList<>();
//        String where = "price between ? and ?";
//        String[] agrs = {Double.toString(from), Double.toString(to)};
//        String orderby = "price desc";
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("itms", null, where, agrs, null,
//                null, orderby);
//        while (rs != null && rs.moveToNext()) {
//            list.add(new Item(rs.getInt(0), rs.getString(1), (float) rs.getDouble(3),
//                    rs.getString(4), new Category(rs.getInt(2), "")));
//
//        }
//        return list;
//    }
}
