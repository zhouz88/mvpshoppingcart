package com.example.mvp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvp.ui.cart.CartFragment;
import com.example.mvp.ui.home.HomeFragment;
import com.example.mvp.ui.mine.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    private int lastFragmentIndex = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    Fragment[] fragments = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MVP实战);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        bottomNavigationView = find(R.id.nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        fragments = new Fragment[]{
                new HomeFragment(),
                new CartFragment(),
                new MineFragment()
        };
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, fragments[0]).commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.home:
                switchFragment(0);
                break;
            case R.id.cart:
                switchFragment(1);
                break;
            case R.id.person:
                switchFragment(2);
                break;
        }
        return false;
    }

    private void switchFragment(int to) {
        if (lastFragmentIndex == to) return;
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        if (!fragments[to].isAdded()) {
            fragmentTransaction.add(R.id.main_frame, fragments[to]);
        } else {
            fragmentTransaction.show(fragments[to]);
        }
        fragmentTransaction.hide(fragments[lastFragmentIndex]).commitAllowingStateLoss();
        lastFragmentIndex = to;
    }
}