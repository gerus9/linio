package com.gerus.linio;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.gerus.linio.views.shopping.ShoppingFragment;
import com.gerus.linio.views.favorites.FavoritesFragment;
import com.gerus.linio.views.home.HomeFragment;

public class GeneralActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private AppCompatTextView mToolbarTitle;
    private int IdLastFragment = 0;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        initToolbar();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.findViewById(R.id.nav_home).performClick();
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbarTitle = mToolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment voFragment = null;
            int idTitle = 0;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    voFragment = new HomeFragment();
                    clearTitle();
                    break;
                case R.id.nav_favorites:
                    voFragment = new FavoritesFragment();
                    idTitle = R.string.nav_favorite;
                    break;
                case R.id.nav_shopping:
                    voFragment = new ShoppingFragment();
                    clearTitle();
                    break;
            }
            if(IdLastFragment!= item.getItemId()){
                prcChangeFragment(voFragment);
                changeTitle(idTitle);
            }
            IdLastFragment = item.getItemId();
            return true;
        }
    };

    private void prcChangeFragment(Fragment poFragment) {
        if(poFragment!=null) getSupportFragmentManager().beginTransaction().replace(R.id.fragments, poFragment).commit();
    }

    public void changeTitle(int piTitle) {
        if(piTitle!=0) mToolbarTitle.setText(piTitle);
    }

    public void clearTitle() {
        mToolbarTitle.setText(null);
    }
}
