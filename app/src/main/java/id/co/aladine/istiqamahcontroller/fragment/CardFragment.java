package id.co.aladine.istiqamahcontroller.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.co.aladine.istiqamahcontroller.R;
import id.co.aladine.istiqamahcontroller.adapter.CardAdapter;
import id.co.aladine.istiqamahcontroller.core.AppData;

/**
 * Created by aladhine on 21/01/18.
 */

public class CardFragment extends Fragment {
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.background)
    LinearLayout background;

    private Unbinder unbinder;

    public static Fragment getInstance(String name, String time) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("time", time);
        f.setArguments(args);

        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_viewpager, container, false);
        unbinder = ButterKnife.bind(this, view);

        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);

        tvTitle.setText(getArguments().getString("name"));
        tvTime.setText(getArguments().getString("time"));

        setColor(getArguments().getString("name"));

        return view;
    }

    public CardView getCardView() {
        return cardView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setColor(String name) {
        int backgroundColor;
        switch (name){
            case "Shubuh" : backgroundColor=R.color.colorShubuh;break;
            case "Dzuhur" : backgroundColor=R.color.colorDzuhur;break;
            case "Ashar" : backgroundColor=R.color.colorAshar;break;
            case "Maghrib" : backgroundColor=R.color.colorMaghrib;break;
            default : backgroundColor=R.color.colorIsya;break;
        }

        background.setBackground(getContext().getDrawable(backgroundColor));

    }
}
