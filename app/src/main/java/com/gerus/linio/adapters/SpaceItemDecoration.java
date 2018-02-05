package com.gerus.linio.adapters;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by gerus-mac on 04/02/18.
 */

class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpaceSeparation;

    public SpaceItemDecoration(int piSpaceSperation) {
        this.mSpaceSeparation = piSpaceSperation;
    }

    @Override
    public void getItemOffsets(Rect poRect, View poView, RecyclerView poParent, RecyclerView.State poState) {
        super.getItemOffsets(poRect, poView, poParent, poState);
        poRect.bottom = mSpaceSeparation;
        poRect.right = mSpaceSeparation;
        poRect.top = mSpaceSeparation;
        poRect.left = mSpaceSeparation;
    }
}
