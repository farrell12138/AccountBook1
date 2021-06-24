package com.example.accountbook_try;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class CostAdapter extends BaseAdapter {
    private List<CostBean> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CostAdapter(Context context, List<CostBean> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);
            viewHolder.mTvCostTitle = convertView.findViewById(R.id.cost_title);
            viewHolder.mTvCostDate = convertView.findViewById(R.id.cost_date);
            viewHolder.mTvCostMoney = convertView.findViewById(R.id.cost_money);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CostBean costBean = mList.get(position);
        viewHolder.mTvCostTitle.setText(costBean.getCostTitle());
        viewHolder.mTvCostDate.setText(costBean.getCostDate());
        viewHolder.mTvCostMoney.setText(costBean.getCostMoney());
        return convertView;
    }

    private static class ViewHolder {
        public TextView mTvCostTitle;
        public TextView mTvCostDate;
        public TextView mTvCostMoney;
    }
}
