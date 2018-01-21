package id.co.aladine.istiqamahcontroller.adapter;

import android.support.v7.widget.CardView;

/**
 * Created by aladhine on 21/01/18.
 */

public interface CardAdapter {
    public final int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
