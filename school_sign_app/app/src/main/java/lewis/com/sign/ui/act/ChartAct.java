package lewis.com.sign.ui.act;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lewis.com.sign.R;
import lewis.com.sign.base.BaseActivity;
import lewis.com.sign.bean.TjBean;

public class ChartAct extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bar_chart)
    BarChart barChart;
    private List<TjBean.DataBean> list;

    @Override
    public int intiLayout() {
        return R.layout.act_chat;
    }

    @Override
    public void initView() {

        Bundle extras = getIntent().getExtras();
        list = (List<TjBean.DataBean>) extras.getSerializable("list");
        tvTitle.setText("图表统计");
        ivBack.setVisibility(View.VISIBLE);
        barChart.getDescription().setEnabled(false); // 不显示描述
        barChart.setExtraOffsets(20, 20, 20, 20); // 设置饼图的偏移量，类似于内边距 ，设置视图窗口大小
        setAxis(); // 设置坐标轴
        setLegend(); // 设置图例
        setData();  // 设置数据

    }
    /**
     * 因为此处的 barData.setBarWidth(0.3f);，也就是说柱子的宽度是0.3f
     * 所以第二个柱子的值要比第一个柱子的值多0.3f，这样才会并列显示两根柱子
     */
    private void setData() {

        List<IBarDataSet> sets = new ArrayList<>();
        // 此处有两个DataSet，所以有两条柱子，BarEntry（）中的x和y分别表示显示的位置和高度
        // x是横坐标，表示位置，y是纵坐标，表示高度
        List<BarEntry> barEntries1 = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            TjBean.DataBean dataBean = list.get(i);
            barEntries1.add(new BarEntry(i, dataBean.zcnum));
        }


        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "");
        barDataSet1.setValueTextColor(Color.RED); // 值的颜色
        barDataSet1.setValueTextSize(15f); // 值的大小
        barDataSet1.setColor(Color.parseColor("#1AE61A")); // 柱子的颜色
        barDataSet1.setLabel("签到次数"); // 设置标签之后，图例的内容默认会以设置的标签显示
        // 设置柱子上数据显示的格式
        barDataSet1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                // 此处的value默认保存一位小数
                return value + "次";
            }
        });

        sets.add(barDataSet1);



        BarData barData = new BarData(sets);
        barData.setBarWidth(0.3f); // 设置柱子的宽度
        barChart.setData(barData);
    }

    private void setLegend() {
        Legend legend = barChart.getLegend();
        legend.setFormSize(12f); // 图例的图形大小
        legend.setTextSize(15f); // 图例的文字大小
        legend.setDrawInside(true); // 设置图例在图中
        legend.setOrientation(Legend.LegendOrientation.VERTICAL); // 图例的方向为垂直
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); //显示位置，水平右对齐
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); // 显示位置，垂直上对齐
        // 设置水平与垂直方向的偏移量
        legend.setYOffset(55f);
        legend.setXOffset(30f);
    }

    private void setAxis() {
        // 设置x轴
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // 设置x轴显示在下方，默认在上方
        xAxis.setDrawGridLines(false); // 将此设置为true，绘制该轴的网格线。
        xAxis.setLabelCount(5);  // 设置x轴上的标签个数
        xAxis.setTextSize(15f); // x轴上标签的大小
        final List<String> labelName=  new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            labelName.add(list.get(i).stucent.name);
        }
        // 设置x轴显示的值的格式
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if ((int) value < labelName.size()) {
                    return labelName.get((int) value);
                } else {
                    return "";
                }
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });
        xAxis.setYOffset(15); // 设置标签对x轴的偏移量，垂直方向

        // 设置y轴，y轴有两条，分别为左和右
        YAxis yAxis_right = barChart.getAxisRight();
        yAxis_right.setAxisMaximum(100f);  // 设置y轴的最大值
        yAxis_right.setAxisMinimum(0f);  // 设置y轴的最小值
        yAxis_right.setEnabled(false);  // 不显示右边的y轴

        YAxis yAxis_left = barChart.getAxisLeft();
        yAxis_left.setAxisMaximum(100f);
        yAxis_left.setAxisMinimum(0f);
        yAxis_left.setTextSize(15f); // 设置y轴的标签大小
    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
