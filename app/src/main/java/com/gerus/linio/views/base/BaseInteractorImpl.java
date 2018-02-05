package com.gerus.linio.views.base;

import android.content.Context;

import com.gerus.linio.managers.network.RequestManager;

/**
 * Created by gerus-mac on 29/04/17.
 */

public abstract class BaseInteractorImpl {
    protected Context mContext;
    protected RequestManager mRequestManager;

    public BaseInteractorImpl(Context poContext){
        mContext = poContext;
        mRequestManager = RequestManager.getInstance(mContext);
    }

}
