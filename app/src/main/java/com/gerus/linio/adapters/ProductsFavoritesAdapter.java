package com.gerus.linio.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gerus.linio.R;
import com.gerus.linio.customviews.ProductView;
import com.gerus.linio.models.Product;

import java.util.List;

/**
 * Created by gerus-mac on 04/02/18.
 */

class ProductsFavoritesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Product> mListProducts;

    private final int EMPTY_SECTION = 0;
    private final int PRODUCTS_SECTION = 1;

    public ProductsFavoritesAdapter(Context poContext, List<Product> poListProducts) {
        mContext = poContext;
        mListProducts = poListProducts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup poParent, int piViewType) {
        LayoutInflater voInflater = LayoutInflater.from(mContext);
        View voView;
        RecyclerView.ViewHolder voViewHolder;
        switch (piViewType) {
            case EMPTY_SECTION:
                voView = voInflater.inflate(R.layout.fav_empty_products, poParent, false);
                voViewHolder = new EmptyViewHolder(voView);
                break;
            case PRODUCTS_SECTION:
            default:
                voView = voInflater.inflate(R.layout.fav_item_product, poParent, false);
                voViewHolder = new ProductViewHolder(voView);
                break;
        }
        return voViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder poViewHolder, int piPosition) {
        if (poViewHolder instanceof ProductViewHolder) {
            ProductViewHolder mHolder = (ProductViewHolder) poViewHolder;
            mHolder.mProductView.prcCreateBadges(mListProducts.get(piPosition));
            mHolder.mProductView.setImage(mListProducts.get(piPosition).getImage());
        }
    }

    @Override
    public int getItemCount() {
        return mListProducts.isEmpty() ? 1 : mListProducts.size();
    }

    @Override
    public int getItemViewType(int piPosition) {
        return mListProducts.isEmpty() ? EMPTY_SECTION : PRODUCTS_SECTION;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        protected ProductView mProductView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            mProductView = itemView.findViewById(R.id.productView);
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
