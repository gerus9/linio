package com.gerus.linio.managers.network;

import android.content.Context;

import com.gerus.linio.BuildConfig;
import com.gerus.linio.R;
import com.gerus.linio.managers.network.services.FavoriteService;
import com.gerus.linio.managers.network.tasks.FavoriteTasks;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gerus-mac on 03/02/18.
 */

public class RequestManager {

    protected static final int CODE_RESPONSE_OK = 200;
    protected String mErrorNetwork;
    protected Context mContext;
    protected FavoriteService mFavoriteService;
    public static RequestManager mRequestManager;
    private final int CACHE_SIZE = 10 * 1024 * 1024; // 10 MB

    public static RequestManager getInstance(Context poContext) {
        if (mRequestManager == null) {
            mRequestManager = new RequestManager(poContext);
        }
        return mRequestManager;
    }

    protected RequestManager(Context poContext) {
        this(poContext, BuildConfig.URL);
    }

    private RequestManager(Context poContext, String psUrl) {
        mContext = poContext;
        mErrorNetwork = mContext.getResources().getString(R.string.error_network);
        Cache voCache = new Cache(mContext.getCacheDir(), CACHE_SIZE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(voCache)
                .build();

        Retrofit voRetrofit = new Retrofit.Builder()
                .baseUrl(psUrl)
                .addConverterFactory(getGsonBuilder())
                .client(okHttpClient)
                .build();

        mFavoriteService = voRetrofit.create(FavoriteService.class);
    }

    public Converter.Factory getGsonBuilder() {
        Gson voGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        return GsonConverterFactory.create(voGson);
    }

    public FavoriteTasks getFavoriteTasks() {
        return new FavoriteTasks(mContext);
    }
}
