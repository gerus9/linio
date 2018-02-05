package com.gerus.linio.managers.images;

import android.content.Context;
import android.widget.ImageView;

import com.gerus.linio.R;
import com.squareup.picasso.Picasso;

/**
 * Created by gerus-mac on 04/02/18.
 */

public class ImageManager {

    private Context mContext;
    public static ImageManager mInstance;
    private Picasso mPicasso;

    public static ImageManager getInstance(Context poContext) {
        if (mInstance == null) {
            mInstance = new ImageManager(poContext);
        }
        return mInstance;
    }

    public ImageManager(Context poContext) {
        mContext = poContext;
        Picasso.Builder mPicassoBuilder = new Picasso.Builder(poContext)
                .loggingEnabled(false)
                .indicatorsEnabled(false);
        mPicasso = mPicassoBuilder.build();
    }

    public void setImageDefault(ImageView poImagenView, String url) {
        mPicasso.load(url).placeholder(R.mipmap.nd_product_loading_280).into(poImagenView);
    }

    public void setImage(ImageView poImagenView, String url, int placeholder) {
        mPicasso.load(url).placeholder(placeholder).into(poImagenView);
    }

    public void setImage(ImageView poImagenView, int idResource, int placeholder) {
        mPicasso.load(idResource).placeholder(placeholder).into(poImagenView);
    }

}
