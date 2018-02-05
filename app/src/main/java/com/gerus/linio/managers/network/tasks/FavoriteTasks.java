package com.gerus.linio.managers.network.tasks;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gerus.linio.managers.network.RequestManager;
import com.gerus.linio.managers.network.ResultResponse;
import com.gerus.linio.models.FavoriteModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gerus-mac on 03/02/18.
 */

public class FavoriteTasks extends RequestManager {

    public FavoriteTasks(Context poContext) {
        super(poContext);
    }

    public void getFavorites(@NonNull final ResultResponse<List<FavoriteModel>> poResponse) {
        mFavoriteService.listFavorites().enqueue(new Callback<List<FavoriteModel>>() {
            @Override
            public void onResponse(Call<List<FavoriteModel>> call, Response<List<FavoriteModel>> response) {
                switch (response.code()) {
                    case CODE_RESPONSE_OK:
                        if(poResponse!=null) poResponse.onSuccess(response.body());
                        break;
                    default:
                        if(poResponse!=null) poResponse.onError(mErrorNetwork);
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<FavoriteModel>> call, Throwable t) {
                if(poResponse!=null) poResponse.onError(mErrorNetwork);
            }
        });
    }
}
