package com.gerus.linio.managers.network;

/**
 * Created by gerus-mac on 03/02/18.
 */

public interface ResultResponse<R> {

    void onSuccess(R poValue);

    void onError(String psErrorMsg);

}
