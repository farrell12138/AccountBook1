package com.example.accountbook_try;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class ChartActivity extends Activity {
    private LineChartView mChart;
    private Map<String, Integer> table = new TreeMap<>();
    private LineChartData mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_view);
        mChart = (LineChartView) findViewById(R.id.chart);
        mData = new LineChartData();
        List<CostBean> allData = (List<CostBean>) getIntent().getSerializableExtra("cost_list");

        generateValues(allData);
        generateData();
    }

    private void generateData() {
        List<Line> lines = new ArrayList<>();
        List<PointValue> values = new ArrayList<>();
        int indexX = 0;
        for (Integer value : table.values()) {
            values.add(new PointValue(indexX++, value));
        }
        Line line = new Line(values)
                .setColor(ChartUtils.COLORS[0])
                .setShape(ValueShape.CIRCLE)
                .setPointColor(ChartUtils.COLORS[3]);
        lines.add(line);
        mData.setLines(lines);
        mChart.setLineChartData(mData);
    }

    private void generateValues(List<CostBean> allData) {
        if (allData != null) {
            for (CostBean data : allData) {
                String costDate = data.getCostDate();
                int costMoney = Integer.parseInt(data.getCostMoney());
                if (!table.containsKey(costDate)) {
                    table.put(costDate, costMoney);
                } else {
                    table.put(costDate, table.get(costDate) + costMoney);
                }
            }
        }
    }
}
