package com.gerus.linio.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gerus.linio.R;
import com.gerus.linio.managers.images.ImageManager;
import com.gerus.linio.models.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gerus-mac on 03/02/18.
 */

public class ProductView extends FrameLayout {

    @BindView(R.id.lnlBadges)
    LinearLayout mLinearBadges;

    @BindView(R.id.imgProductFavorite)
    AppCompatImageView mImgProductFavorite;

    @BindView(R.id.imgProduct)
    ImageView mImgProduct;

    private Context mContext;

    private static final int LINIO_PLUS_1 = 1;
    private static final int LINIO_PLUS_2 = 2;

    private static final String CONDITION_NEW = "new";
    private static final String CONDITION_REFURBISHED = "refurbished";

    private static final int ID_RESOURCE_NEW = R.drawable.nd_ic_new_30;
    private static final int ID_RESOURCE_REFURBISHED = R.drawable.nd_ic_refurbished_30;
    private static final int ID_RESOURCE_LINIO_PLUS = R.drawable.nd_ic_plus_30;
    private static final int ID_RESOURCE_LINIO_PLUS_48 = R.drawable.nd_ic_plus_48_30;
    private static final int ID_RESOURCE_AIRPLANE = R.drawable.nd_ic_international_30;
    private static final int ID_RESOURCE_FREE_SHIPPING = R.drawable.nd_ic_free_shipping_30;

    public ProductView(Context poContext) {
        super(poContext);
        mContext = poContext;
        init();
    }

    public ProductView(Context poContext, @Nullable AttributeSet poAttrs) {
        super(poContext, poAttrs);
        mContext = poContext;
        init();

        TypedArray voStyle = poContext.obtainStyledAttributes(poAttrs, R.styleable.ProductView, 0, 0);
        try {
            showFavorite(voStyle.getBoolean(R.styleable.ProductView_pv_favorite, false));
        } finally {
            voStyle.recycle();
        }
    }

    private void showFavorite(boolean pbShow) {
        mImgProductFavorite.setVisibility(pbShow ? View.VISIBLE : View.GONE);
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View voView = inflater.inflate(R.layout.custom_product, this, true);
        ButterKnife.bind(voView, this);
    }


    public void setImage(String psUrl) {
        ImageManager.getInstance(mContext).setImageDefault(mImgProduct, psUrl);
        prcOrderIcons();
    }

    public void prcCreateBadges(Product poProduct) {
        prcIconCondition(poProduct.getConditionType());
        prcIconLinioPlus(poProduct.getLinioPlusLevel());
        prcIconAirplane(poProduct.isImported());
        prcIconFreeShipping(poProduct.isFreeShipping());
    }

    private void prcIconAirplane(boolean pbisImported) {
        if (pbisImported) prcAddBadge(ID_RESOURCE_AIRPLANE);
    }

    private void prcIconFreeShipping(boolean pbisFree) {
        if (pbisFree) prcAddBadge(ID_RESOURCE_FREE_SHIPPING);
    }

    private void prcIconCondition(String psCondition) {
        if (psCondition != null && !psCondition.isEmpty()) {
            if (psCondition.equals(CONDITION_REFURBISHED)) {
                prcAddBadge(ID_RESOURCE_REFURBISHED);
            } else if (psCondition.equals(CONDITION_NEW)) {
                prcAddBadge(ID_RESOURCE_NEW);
            }
        }
    }

    private void prcIconLinioPlus(int piLinioPlus) {
        switch (piLinioPlus) {
            case LINIO_PLUS_1:
                prcAddBadge(ID_RESOURCE_LINIO_PLUS);
                break;
            case LINIO_PLUS_2:
                prcAddBadge(ID_RESOURCE_LINIO_PLUS_48);
                break;
        }
    }

    private void prcAddBadge(int idResource) {
        ImageView voImageBadge = new ImageView(getContext());
        voImageBadge.setLayoutParams(new LayoutParams(getSizeBadge(), getSizeBadge()));
        voImageBadge.setImageResource(idResource);
        mLinearBadges.addView(voImageBadge);
    }

    private void prcOrderIcons() {
        mImgProductFavorite.bringToFront();
        mLinearBadges.bringToFront();
    }

    public int getSizeBadge() {
        return (int) mContext.getResources().getDimension(R.dimen.product_badge);
    }
}
