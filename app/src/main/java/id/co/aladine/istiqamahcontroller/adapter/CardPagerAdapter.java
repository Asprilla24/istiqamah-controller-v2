package id.co.aladine.istiqamahcontroller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.co.aladine.istiqamahcontroller.fragment.CardFragment;
import id.co.aladine.istiqamahcontroller.model.SholatModel;

/**
 * Created by aladhine on 21/01/18.
 */

public class CardPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {

    private List<CardFragment> fragments;
    private ArrayList<SholatModel> data;
    private float baseElevation;

    public CardPagerAdapter(FragmentManager fm, float baseElevation, ArrayList<SholatModel> data){
        super(fm);
        fragments = new ArrayList<>();
        this.data = data;
        this.baseElevation = baseElevation;

        for(int i = 0; i < data.size(); i++){
            addCardFragment(new CardFragment());
        }
    }

    @Override
    public Fragment getItem(int position) {
        String name = data.get(position).getName();
        String time = data.get(position).getTime();

        return CardFragment.getInstance(name, time);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        fragments.set(position, (CardFragment) fragment);
        return fragment;
    }

    @Override
    public float getBaseElevation() {
        return baseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return fragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addCardFragment(CardFragment fragment) {
        fragments.add(fragment);
    }
}
