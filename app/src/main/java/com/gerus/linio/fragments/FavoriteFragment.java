package com.gerus.linio.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gerus.linio.OnGeneralInterface;
import com.gerus.linio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gerus-mac on 22/01/17.
 */

public class FavoriteFragment extends Fragment {
    @BindView(R.id.rvFavorites)
    RecyclerView rvFavorties;

    protected final String TAG = this.getClass().getSimpleName();
    private OnGeneralInterface mListener;
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mContext = getActivity();
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
    }

    @Override
    public void onAttach(Context poContext) {
        super.onAttach(poContext);
        if (poContext instanceof OnGeneralInterface) {
            mListener = (OnGeneralInterface) poContext;
        } else {
            String psError = "***** "+ getActivity().getClass().getSimpleName() + " must implement "+ OnGeneralInterface.class.getSimpleName() +"***** ";
            Log.e(TAG, psError);
            throw new RuntimeException(psError);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
