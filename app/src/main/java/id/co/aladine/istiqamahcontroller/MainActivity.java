package id.co.aladine.istiqamahcontroller;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.aladine.istiqamahcontroller.adapter.CardPagerAdapter;
import id.co.aladine.istiqamahcontroller.animation.ShadowTransformer;
import id.co.aladine.istiqamahcontroller.core.AppData;
import id.co.aladine.istiqamahcontroller.helper.Utils;
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

    private ArrayList<SholatModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("");

        getDataSholat();

        initView();
    }

    private void initView() {
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
                setColor(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getDataSholat() {
        data = new ArrayList<>();

        data.add(new SholatModel(AppData.KEY_ISYA, "Isya", "2018-01-21 07:00PM"));
        data.add(new SholatModel(AppData.KEY_SUBUH, "Shubuh", "2018-01-21 04:20AM"));
        data.add(new SholatModel(AppData.KEY_DHUHUR, "Dzuhur", "2018-01-21 11:36AM"));
        data.add(new SholatModel(AppData.KEY_ASHAR, "Ashar", "2018-01-21 03:00PM"));
        data.add(new SholatModel(AppData.KEY_MAGHRIB, "Maghrib", "2018-01-21 06:00PM"));
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    public void setColor(int position) {
        int backgroundColor;
        switch (data.get(position).getId()){
            case AppData.KEY_SUBUH : backgroundColor=R.color.colorAccentShubuh;break;
            case AppData.KEY_DHUHUR : backgroundColor=R.color.colorAccentDzuhur;break;
            case AppData.KEY_ASHAR : backgroundColor=R.color.colorAccentAshar;break;
            case AppData.KEY_MAGHRIB : backgroundColor=R.color.colorAccentMaghrib;break;
            default : backgroundColor=R.color.colorAccentIsya;break;
        }

        background.setBackground(getDrawable(backgroundColor));
        getSupportActionBar().setBackgroundDrawable(getDrawable(backgroundColor));

        Utils.darkenStatusBar(this, backgroundColor);
    }
}
