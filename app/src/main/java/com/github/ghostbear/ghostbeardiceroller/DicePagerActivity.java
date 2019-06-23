package com.github.ghostbear.ghostbeardiceroller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class DicePagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<Dice> mDices;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_pager);
        getSupportActionBar().setElevation(0);

        mViewPager = findViewById(R.id.dice_view_pager);

        mDices = DiceCollection.getInstance(this).getDices();

        FragmentManager manager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                Dice dice = mDices.get(position);
                return DiceFragment.newIntent(dice.getUUID());
            }

            @Override
            public int getCount() {
                return mDices.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return getString(mDices.get(position).getName());
            }
        });
    }
}
