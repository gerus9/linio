package com.gerus.linio.views.favorites;

import com.gerus.linio.managers.network.tasks.FavoriteTasks;
import com.gerus.linio.managers.network.ResultResponse;
import com.gerus.linio.models.FavoriteModel;
import com.gerus.linio.views.base.BaseInteractorImpl;

import java.util.List;

/**
 * Created by gerus-mac on 29/04/17.
 */

public class FavoritesInteractorImpl extends BaseInteractorImpl implements FavoritesInteractor {
    private FavoritesPresenter mPresenter;
    private FavoriteTasks mFavoriteTasks;

    public FavoritesInteractorImpl(FavoritesPresenterImpl poPresenter) {
        super(poPresenter.getContext());
        mPresenter = poPresenter;
        mFavoriteTasks = mRequestManager.getFavoriteTasks();
    }

    @Override
    public void getFavorites() {
        mFavoriteTasks.getFavorites(new ResultResponse<List<FavoriteModel>>() {
            @Override
            public void onSuccess(List<FavoriteModel> poValue) {
                mPresenter.onListProducts(poValue);
            }

            @Override
            public void onError(String psErrorMsg) {
                mPresenter.onErrorListProducts(psErrorMsg);
            }
        });
    }
}
