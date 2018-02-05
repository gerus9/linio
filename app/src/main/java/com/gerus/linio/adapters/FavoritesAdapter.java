package com.gerus.linio.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gerus.linio.R;
import com.gerus.linio.models.FavoriteModel;
import com.gerus.linio.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerus-mac on 03/02/18.
 */
public class FavoritesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context mContext;
    private final int COLLECTION_SECTION = 1;
    private final int PRODUCTS_SECTION = 2;
    private final int SIZE_ELEMENTS = 2;

    private CollectionFavoritesAdapter mCollectionAdapter;
    private ProductsFavoritesAdapter mProductAdapter;

    private List<FavoriteModel> mListFavorites;
    private List<Product> mListProducts;

    public FavoritesAdapter(Context poContext, List<FavoriteModel> poFavorites) {
        this.mContext = poContext;
        mListFavorites = poFavorites;
        prcUpdateValues();
    }

    private void prcUpdateValues() {
        mListProducts = new ArrayList<>();
        for (FavoriteModel voFavoriteModel: mListFavorites){
            for (Product voProduct: voFavoriteModel.getProducts()) {
                mListProducts.add(voProduct);
            }
        }
    }

    public void prcReplaceFavorites(List<FavoriteModel> poFavorites){
        mListFavorites = poFavorites;
        prcUpdateValues();
        notifyDataSetChanged();
        mProductAdapter.notifyDataSetChanged();
        mCollectionAdapter.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup poParent, int piTypeView) {
        LayoutInflater voInflater = LayoutInflater.from(mContext);
        View voView;
        RecyclerView.ViewHolder voViewHolder;
        switch (piTypeView) {
            case COLLECTION_SECTION:
                voView = voInflater.inflate(R.layout.fav_collection, poParent, false);
                voViewHolder = new CollectionsViewHolder(voView);
                break;
            case PRODUCTS_SECTION:
            default:
                voView = voInflater.inflate(R.layout.fav_products, poParent, false);
                voViewHolder = new ProductsViewHolder(voView);
                break;
        }
        return voViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder poHolder, int piPosition) {
        if (poHolder.getItemViewType() == COLLECTION_SECTION)
            prcCollectionHolder((CollectionsViewHolder) poHolder);
        else if (poHolder.getItemViewType() == PRODUCTS_SECTION)
            prcProductsHolder((ProductsViewHolder) poHolder);
    }

    private void prcCollectionHolder(CollectionsViewHolder poHolder) {
        mCollectionAdapter = new CollectionFavoritesAdapter(mContext, mListFavorites);
        poHolder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        if(!mListProducts.isEmpty()) {
            poHolder.recyclerView.addItemDecoration(new SpaceItemDecoration((int) mContext.getResources().getDimension(R.dimen.space_xl)));
        }
        poHolder.recyclerView.setAdapter(mCollectionAdapter);
    }


    private void prcProductsHolder(ProductsViewHolder poHolder) {
        mProductAdapter = new ProductsFavoritesAdapter(mContext, mListProducts);
        if(mListProducts.isEmpty()) {
            poHolder.mTxtNumberProducts.setVisibility(View.GONE);
            poHolder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        } else {
            poHolder.mTxtNumberProducts.setVisibility(View.VISIBLE);
            poHolder.mTxtNumberProducts.setText(mContext.getString(R.string.label_totalProdcts, mListProducts.size()));
            poHolder.mRecyclerView.addItemDecoration(new SpaceItemDecoration((int) mContext.getResources().getDimension(R.dimen.space_s)));
            poHolder.mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, mContext.getResources().getInteger(R.integer.favColums)));
        }
        poHolder.mRecyclerView.setAdapter(mProductAdapter);
    }

    @Override
    public int getItemCount() {
        return SIZE_ELEMENTS;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? COLLECTION_SECTION : PRODUCTS_SECTION;
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;
        private TextView mTxtNumberProducts;

        ProductsViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.rvProductsFavorities);
            mTxtNumberProducts = itemView.findViewById(R.id.txtNumberProducts);
        }
    }

    public class CollectionsViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;

        CollectionsViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rvCollectionFavorities);
        }
    }
}
