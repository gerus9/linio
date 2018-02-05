package com.gerus.linio.views.favorites;

import com.gerus.linio.models.FavoriteModel;
import com.gerus.linio.views.base.BasePresenter;

import java.util.List;

/**
 * Created by gerus-mac on 29/04/17.
 */

public interface FavoritesPresenter extends BasePresenter {
    void getListFavorites();

    void onListProducts(List<FavoriteModel> poListModels);
    void onErrorListProducts(String psErrorMsg);

}
