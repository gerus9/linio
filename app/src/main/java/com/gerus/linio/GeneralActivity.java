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

import com.gerus.linio.fragments.AccountFragment;
import com.gerus.linio.fragments.FavoriteFragment;
import com.gerus.linio.fragments.HomeFragment;

public class GeneralActivity extends AppCompatActivity implements OnGeneralInterface {
    private Toolbar mToolbar;
    private AppCompatTextView mToolbarTitle;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        initToolbar();
        ((BottomNavigationView) findViewById(R.id.bottom_navigation)).setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                    idTitle = R.string.nav_home;
                    break;
                case R.id.nav_favorites:
                    voFragment = new FavoriteFragment();
                    idTitle = R.string.nav_favorite;
                    break;
                case R.id.nav_account:
                    voFragment = new AccountFragment();
                    idTitle = R.string.nav_account;
                    break;
            }
            prcChangeFragment(voFragment);
            changeTitle(idTitle);
            return true;
        }
    };

    private void prcChangeFragment(Fragment poFragment) {
        if(poFragment!=null) getSupportFragmentManager().beginTransaction().replace(R.id.fragments, poFragment).commit();
    }

    @Override
    public void changeTitle(int piTitle) {
        mToolbarTitle.setText(piTitle);
    }
}
