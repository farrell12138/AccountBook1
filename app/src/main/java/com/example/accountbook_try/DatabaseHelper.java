package com.example.accountbook_try;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String COST_TITLE = "cost_title";
    public static final String COST_DATE = "cost_date";
    public static final String COST_MONEY = "cost_money";
    public static final String FARRELL_COST = "farrell_cost";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "farrell_daily", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists FARRELL_COST(" +
                "id integer primary key," +
                "cost_title varchar," +
                "cost_date varchar," +
                "cost_money varchar)");
    }

    public void insertCost(CostBean costBean) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COST_TITLE, costBean.getCostTitle());
        contentValues.put(COST_DATE, costBean.getCostDate());
        contentValues.put(COST_MONEY, costBean.getCostMoney());
        database.insert(FARRELL_COST, null, contentValues);
    }

    public Cursor getAllCostData() {
        SQLiteDatabase database = getWritableDatabase();
        return database.query("FARRELL_COST", null, null,
                null, null, null, "COST_DATE " + " ASC");
    }

    public void deleteAllDate() {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("FARRELL_COST", null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
