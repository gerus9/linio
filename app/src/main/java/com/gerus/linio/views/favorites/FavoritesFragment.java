package com.gerus.linio.views.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gerus.linio.R;
import com.gerus.linio.adapters.FavoritesAdapter;
import com.gerus.linio.models.FavoriteModel;
import com.gerus.linio.views.Dialogs;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gerus-mac on 22/01/17.
 */

public class FavoritesFragment extends Fragment {
    @BindView(R.id.rvFavorites)
    RecyclerView rvFavorties;

    private Dialogs mDialogs;
    private FavoritesAdapter mFavortiesAdapter;
    private FavoritesPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mDialogs = new Dialogs(getActivity());
        mPresenter = new FavoritesPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.favorities, menu);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFavortiesAdapter = new FavoritesAdapter(getActivity(), new ArrayList<FavoriteModel>());
        rvFavorties.setAdapter(mFavortiesAdapter);
        rvFavorties.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPresenter.getListFavorites();
    }

    public void showProgressBar(){
        mDialogs.showProgressBar();
    }

    public void hideProgressBar(){
        mDialogs.dismissProgressBar();
    }

    public void showResults(List<FavoriteModel> poListModels){
        mFavortiesAdapter.prcReplaceFavorites(poListModels);
    }
}
