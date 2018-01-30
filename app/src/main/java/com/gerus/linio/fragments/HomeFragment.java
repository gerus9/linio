package com.gerus.linio.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gerus.linio.OnGeneralInterface;
import com.gerus.linio.R;

/**
 * Created by gerus-mac on 22/01/17.
 */

public class HomeFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    private OnGeneralInterface mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
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
