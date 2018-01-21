package id.co.aladine.istiqamahcontroller;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.aladine.istiqamahcontroller.adapter.CardPagerAdapter;
import id.co.aladine.istiqamahcontroller.animation.ShadowTransformer;
import id.co.aladine.istiqamahcontroller.core.AppData;
import id.co.aladine.istiqamahcontroller.helper.Utils;
import id.co.aladine.istiqamahcontroller.model.PercentageModel;
import id.co.aladine.istiqamahcontroller.model.SholatModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvTimer)
    TextView tvTimer;
    @BindView(R.id.tvPlace)
    TextView tvPlace;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.background)
    LinearLayout background;
    @BindView(R.id.chart)
    LineChart chart;

    private ArrayList<SholatModel> data;

    private ArrayList<PercentageModel> shubuhStatistic;
    private ArrayList<PercentageModel> dzuhurStatistic;
    private ArrayList<ArrayList<PercentageModel>> dataStatistic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("");

        getDataSholat();

        getDataPercentage();

        initView();
    }

    private void initView() {
        initChart();

        CardPagerAdapter pagerAdapter = new CardPagerAdapter(getSupportFragmentManager(), dpToPixels(1, this), data);
        //CardPagerAdapter pagerAdapter = new CardPagerAdapter(getSupportFragmentManager(), data);
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(pagerAdapter);
        //viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setColor(getColorTheme(position));
                setDataChart(dataStatistic.get(position), position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initChart() {
        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        //setting possision of Axis
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setEnabled(false);

        chart.setAutoScaleMinMaxEnabled(true);
        chart.setDrawGridBackground(false);
        chart.setScaleEnabled(false);
        chart.setHighlightPerTapEnabled(false);
        chart.setHighlightPerDragEnabled(false);
        chart.getDescription().setEnabled(false);
    }

    private void setDataChart(ArrayList<PercentageModel> data, int position){
        //set data
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            PercentageModel obj = data.get(i);
            int getDay = Integer.parseInt(obj.getDate().split("-")[2]);
            Float getPercentage = obj.getPercentage().floatValue();

            entries.add(new Entry(getDay, getPercentage));
        }
        LineDataSet dataset = new LineDataSet(entries, "");

        LineData lineData = new LineData(dataset);
        dataset.setDrawFilled(true);
        dataset.setFillAlpha(100);
        dataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataset.setFillColor(Color.BLUE);

        chart.setData(lineData);
        chart.animateY(800);
        chart.invalidate();
    }

    private void getDataSholat() {
        data = new ArrayList<>();

        data.add(new SholatModel(AppData.KEY_ISYA, "Isya", "2018-01-21 07:00PM"));
        data.add(new SholatModel(AppData.KEY_SUBUH, "Shubuh", "2018-01-21 04:20AM"));
        data.add(new SholatModel(AppData.KEY_DHUHUR, "Dzuhur", "2018-01-21 11:36AM"));
        data.add(new SholatModel(AppData.KEY_ASHAR, "Ashar", "2018-01-21 03:00PM"));
        data.add(new SholatModel(AppData.KEY_MAGHRIB, "Maghrib", "2018-01-21 06:00PM"));
    }

    private void getDataPercentage() {
        shubuhStatistic = new ArrayList<>();
        dzuhurStatistic = new ArrayList<>();
        dataStatistic = new ArrayList<>();

        shubuhStatistic.add(new PercentageModel(1, AppData.KEY_SUBUH, "2018-01-21", 80d));
        shubuhStatistic.add(new PercentageModel(2, AppData.KEY_SUBUH, "2018-01-22", 85d));
        shubuhStatistic.add(new PercentageModel(3, AppData.KEY_SUBUH, "2018-01-23", 70d));
        shubuhStatistic.add(new PercentageModel(4, AppData.KEY_SUBUH, "2018-01-24", 54.6d));
        shubuhStatistic.add(new PercentageModel(5, AppData.KEY_SUBUH, "2018-01-25", 30.1d));

        dzuhurStatistic.add(new PercentageModel(6, AppData.KEY_DHUHUR, "2018-01-21", 75d));
        dzuhurStatistic.add(new PercentageModel(7, AppData.KEY_DHUHUR, "2018-01-22", 91.3d));
        dzuhurStatistic.add(new PercentageModel(8, AppData.KEY_DHUHUR, "2018-01-23", 87.2d));
        dzuhurStatistic.add(new PercentageModel(9, AppData.KEY_DHUHUR, "2018-01-24", 88.6d));
        dzuhurStatistic.add(new PercentageModel(10, AppData.KEY_DHUHUR, "2018-01-25", 70.1d));

        dataStatistic.add(shubuhStatistic);
        dataStatistic.add(dzuhurStatistic);
        dataStatistic.add(shubuhStatistic);
        dataStatistic.add(dzuhurStatistic);
        dataStatistic.add(shubuhStatistic);
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    private int getColorTheme(int position){
        int backgroundColor;
        switch (data.get(position).getId()) {
            case AppData.KEY_SUBUH:
                backgroundColor = R.color.colorAccentShubuh;
                break;
            case AppData.KEY_DHUHUR:
                backgroundColor = R.color.colorAccentDzuhur;
                break;
            case AppData.KEY_ASHAR:
                backgroundColor = R.color.colorAccentAshar;
                break;
            case AppData.KEY_MAGHRIB:
                backgroundColor = R.color.colorAccentMaghrib;
                break;
            default:
                backgroundColor = R.color.colorAccentIsya;
                break;
        }
        return backgroundColor;
    }

    private void setColor(int backgroundColor) {
        background.setBackground(getDrawable(backgroundColor));
        getSupportActionBar().setBackgroundDrawable(getDrawable(backgroundColor));

        Utils.darkenStatusBar(this, backgroundColor);
    }
}
