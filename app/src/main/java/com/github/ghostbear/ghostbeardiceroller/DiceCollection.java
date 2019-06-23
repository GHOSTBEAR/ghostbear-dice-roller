package com.github.ghostbear.ghostbeardiceroller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DiceCollection {
    private static DiceCollection instance;
    private List<Dice> mDices;

    private DiceCollection(Context context) {
        mDices = new ArrayList<>();
        mDices.add(new Dice(R.string.d20, R.drawable.ic_d20, 20));
        mDices.add(new Dice(R.string.d12, R.drawable.ic_d12, 12));
        mDices.add(new Dice(R.string.d10, R.drawable.ic_d10, 10));
        mDices.add(new Dice(R.string.d8, R.drawable.ic_d8, 8));
        mDices.add(new Dice(R.string.d6, R.drawable.ic_d6, 6));
        mDices.add(new Dice(R.string.d4, R.drawable.ic_d4, 4));
        mDices.add(new Dice(R.string.d10000, R.drawable.ic_cubes, 10_000));
    }

    public static DiceCollection getInstance(Context context) {
        if (instance == null) {
            instance = new DiceCollection(context);
        }
        return instance;
    }

    public List<Dice> getDices() {
        return mDices;
    }

    public Dice getDice(UUID uuid) {
        for (Dice dice : mDices) {
            if (dice.getUUID().equals(uuid)) {
                return dice;
            }
        }
        return null;
    }
}
