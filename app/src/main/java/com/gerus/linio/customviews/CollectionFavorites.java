package com.gerus.linio.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gerus.linio.R;
import com.gerus.linio.managers.images.ImageManager;
import com.gerus.linio.models.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by gerus-mac on 03/02/18.
 */

public class CollectionFavorites extends LinearLayout {
    @BindViews({R.id.imgFavLarge, R.id.imgFavSmall1, R.id.imgFavSmall2})
    ImageView[] mListImageViews;

    int[] mListIdEmpty = {R.mipmap.fav_big, R.mipmap.fav_small_1, R.mipmap.fav_small_2};

    @BindView(R.id.lnrCollections)
    LinearLayout mLinearCollections;

    private Context mContext;

    public CollectionFavorites(Context poContext) {
        super(poContext);
        mContext = poContext;
        init();
    }

    public CollectionFavorites(Context poContext, @Nullable AttributeSet poAttrs) {
        super(poContext, poAttrs);
        mContext = poContext;
        init();

        TypedArray voStyle = poContext.obtainStyledAttributes(poAttrs, R.styleable.CollectionFavorites, 0, 0);
        try {
            setEmpty(voStyle.getBoolean(R.styleable.CollectionFavorites_cf_default, false));
        } finally {
            voStyle.recycle();
        }
    }

    private void init() {
        LayoutInflater voInflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View voView = voInflate.inflate(R.layout.custom_collection_favorites, this, true);
        ButterKnife.bind(voView, this);
    }

    public void setImages(List<Product> poListProducts) {
        int viSize = poListProducts.size();
        for (int i = 0, length = mListImageViews.length; i < length; i++) {
            if (i > viSize) break;
            ImageManager.getInstance(getContext()).setImage(mListImageViews[i], poListProducts.get(i).getImage(), R.mipmap.favorite_placeholder);
        }
    }

    public void setEmpty(boolean pbDefault) {
        if (pbDefault) {
            mLinearCollections.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.purple));
            for (int i = 0; i < mListImageViews.length; i++) {
                if (i > mListIdEmpty.length) break;
                ImageManager.getInstance(getContext()).setImage(mListImageViews[i], mListIdEmpty[i], R.mipmap.fav_big);
            }
        }
    }
}
