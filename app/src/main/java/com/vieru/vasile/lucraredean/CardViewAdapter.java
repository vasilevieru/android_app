package com.vieru.vasile.lucraredean;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Vasile on 14.06.2017.
 */
public class CardViewAdapter extends PagerAdapter {

    private ViewPager mCardsViewPager;
    private float MIN_SCALE = 1f - 1f / 7f;
    private float MAX_SCALE = 1f;
    private List<Kebab> kebabList;
    private Bitmap bitmap;

    public CardViewAdapter(List<Kebab> kebabList) {
        super();
        this.kebabList = kebabList;
    }

    private boolean mIsDefaultItemSelected = false;

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView cardImageView = (ImageView) View.inflate(container.getContext(),R.layout.activity_item,null);
        kebabList=ListActivity.getKebabs();
        Kebab kebab = kebabList.get(position);
        cardImageView.setImageBitmap(kebab.getImage());
        cardImageView.setTag(position);

        if(!mIsDefaultItemSelected){
            cardImageView.setScaleX(MAX_SCALE);
            cardImageView.setScaleY(MAX_SCALE);
            mIsDefaultItemSelected=true;
        }else{
            cardImageView.setScaleX(MIN_SCALE);
            cardImageView.setScaleY(MIN_SCALE);
        }

        container.addView(cardImageView);
        return cardImageView;
    }

    @Override
    public int getCount() {
        return kebabList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


}
