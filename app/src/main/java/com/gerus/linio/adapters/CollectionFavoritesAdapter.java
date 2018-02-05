package com.gerus.linio.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gerus.linio.R;
import com.gerus.linio.customviews.CollectionFavorites;
import com.gerus.linio.models.FavoriteModel;

import java.util.List;

/**
 * Created by gerus-mac on 04/02/18.
 */
class CollectionFavoritesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<FavoriteModel> mListFavorites;

    private final int EMPTY_SECTION = 0;
    private final int COLLECTION_SECTION = 1;

    public CollectionFavoritesAdapter(Context poContext, List<FavoriteModel> poListFavorities) {
        mContext = poContext;
        mListFavorites = poListFavorities;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup poParent, int piTypeView) {
        LayoutInflater voInflater = LayoutInflater.from(mContext);
        View voView;
        RecyclerView.ViewHolder voViewHolder;
        switch (piTypeView) {
            case EMPTY_SECTION:
                voView = voInflater.inflate(R.layout.fav_empty_collection, poParent, false);
                voViewHolder = new EmptyViewHolder(voView);
                break;
            case COLLECTION_SECTION:
            default:
                voView = voInflater.inflate(R.layout.fav_item_collection, poParent, false);
                voViewHolder = new CollectionViewHolder(voView);
                break;
        }
        return voViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder poHolder, int piPosition) {
        if(poHolder instanceof CollectionViewHolder){
            CollectionViewHolder voHolder = (CollectionViewHolder) poHolder;
            voHolder.mTxtCollectionName.setText(mListFavorites.get(piPosition).getName());
            voHolder.mTxtCollectionCount.setText((String.valueOf(mListFavorites.get(piPosition).getProducts().size())));
            voHolder.mCollectionsFav.setImages(mListFavorites.get(piPosition).getProducts());
        }
    }

    @Override
    public int getItemCount() {
        return mListFavorites.isEmpty() ? 1 : mListFavorites.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mListFavorites.isEmpty() ? EMPTY_SECTION : COLLECTION_SECTION;
    }


    public class CollectionViewHolder extends RecyclerView.ViewHolder {
        CollectionFavorites mCollectionsFav;
        TextView mTxtCollectionName;
        TextView mTxtCollectionCount;

        public CollectionViewHolder(View poView) {
            super(poView);
            mCollectionsFav = poView.findViewById(R.id.cfFavorities);
            mTxtCollectionName = poView.findViewById(R.id.txtCollectionName);
            mTxtCollectionCount = poView.findViewById(R.id.txtCollectionCount);
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View poView) {
            super(poView);
        }
    }
}
