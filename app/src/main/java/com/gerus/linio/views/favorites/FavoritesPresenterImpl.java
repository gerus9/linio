package com.gerus.linio.views.favorites;

import android.content.Context;

import com.gerus.linio.models.FavoriteModel;

import java.util.List;

/**
 * Created by gerus-mac on 04/02/18.
 */
public class FavoritesPresenterImpl implements FavoritesPresenter {
    private FavoritesFragment mView;
    private FavoritesInteractor mInteractor;


    public FavoritesPresenterImpl(FavoritesFragment poFragment) {
        mView = poFragment;
        mInteractor = new FavoritesInteractorImpl(this);
    }

    @Override
    public Context getContext() {
        return mView.getContext();
    }

    @Override
    public void getListFavorites() {
        mView.showProgressBar();
        mInteractor.getFavorites();
    }

    @Override
    public void onListProducts(List<FavoriteModel> poListModels) {
        mView.hideProgressBar();
        mView.showResults(poListModels);
    }

    @Override
    public void onErrorListProducts(String psErrorMsg) {
        mView.hideProgressBar();
    }
}
