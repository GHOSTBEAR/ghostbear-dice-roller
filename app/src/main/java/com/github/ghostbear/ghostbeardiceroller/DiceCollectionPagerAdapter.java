package com.github.ghostbear.ghostbeardiceroller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class DiceCollectionPagerAdapter extends FragmentStatePagerAdapter {

    public DiceCollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private final Dice[] mDices = new Dice[] {
            new Dice(R.string.d20, R.drawable.ic_d20, 20),
            new Dice(R.string.d12, R.drawable.ic_d12, 12),
            new Dice(R.string.d10, R.drawable.ic_d10, 10),
            new Dice(R.string.d8, R.drawable.ic_d8, 8),
            new Dice(R.string.d6, R.drawable.ic_d6, 6),
            new Dice(R.string.d4, R.drawable.ic_d4, 4),
            new Dice(R.string.d10000, R.drawable.ic_cubes, 10_000)
    };


    @Override
    public Fragment getItem(int i) {
        DiceFragment fragment = new DiceFragment();
        fragment.setDice(mDices[i]);
        Bundle args = new Bundle();
        args.putString(DiceFragment.ARG_OBJECT, "dice");
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return mDices.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "D" + mDices[position].getFaces();
    }
}
