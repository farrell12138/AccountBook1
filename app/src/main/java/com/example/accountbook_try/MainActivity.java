package com.example.accountbook_try;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<CostBean> mCostBeanList;
    private DatabaseHelper mDataHelper;
    private CostAdapter mCostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataHelper = new DatabaseHelper(this);
        mCostBeanList = new ArrayList<>();
        showCostData();

        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.del).setOnClickListener(this);
    }

    private void showCostData() {
        ListView costList = findViewById(R.id.cost_list);
        initCostData();
        mCostAdapter = new CostAdapter(this, mCostBeanList);
        costList.setAdapter(mCostAdapter);
    }

    private void initCostData() {
        Cursor cursor = mDataHelper.getAllCostData();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                CostBean costBean = new CostBean();
                costBean.setCostTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COST_TITLE)));
                costBean.setCostDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COST_DATE)));
                costBean.setCostMoney(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COST_MONEY)));
                mCostBeanList.add(costBean);
            }
            cursor.close();
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            View dialogView = LayoutInflater.from(MainActivity.this).inflate(
                    R.layout.new_cost_data, null
            );
            EditText costTitle = dialogView.findViewById(R.id.add_cost_title);
            DatePicker costDate = dialogView.findViewById(R.id.add_cost_date);
            EditText costMoney = dialogView.findViewById(R.id.add_cost_money);
            builder.setView(dialogView)
                    .setTitle("New Cost")
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @SuppressLint("WrongViewCast")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(!costTitle.getText().toString().equals("") && !costMoney.getText().toString().equals("")){
                                CostBean costBean = new CostBean();
                                costBean.setCostTitle(costTitle.getText().toString());
                                costBean.setCostDate(costDate.getYear() + "-" + costDate.getMonth() + "-" + costDate.getDayOfMonth());
                                costBean.setCostMoney(costMoney.getText().toString());
                                mDataHelper.insertCost(costBean);
                                mCostBeanList.add(costBean);
                                mCostAdapter.notifyDataSetChanged();
                            }
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create().show();
        } else if (v.getId() == R.id.del) {
            mDataHelper.deleteAllDate();
            mCostBeanList.clear();
            showCostData();
        }
    }
}