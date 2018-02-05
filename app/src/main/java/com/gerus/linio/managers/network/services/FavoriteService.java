package com.gerus.linio.managers.network.services;

import com.gerus.linio.models.FavoriteModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by gerus-mac on 03/02/18.
 */

public interface FavoriteService {

    @Headers("Cache-Control: public, max-age=30, s-maxage=30 , max-stale=30")
    @GET("/la-ursic/3c5f25d5ee955ee9a6e493ac57884b9c/raw/5fab9af8e1f4db60419ba3a8da9f138cbb3a8461/Wish%2520lists/")
    Call<List<FavoriteModel>> listFavorites();
}
