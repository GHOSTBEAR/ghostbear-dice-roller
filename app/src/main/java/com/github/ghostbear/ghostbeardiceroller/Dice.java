package com.github.ghostbear.ghostbeardiceroller;

import java.util.Random;
import java.util.UUID;

public class Dice {
    private UUID mUUID;
    private int mNameResId;
    private int mDrawableResId;
    private int mFaces;

    public Dice(int nameResId, int drawableResId, int faces) {
        mUUID = UUID.randomUUID();
        mNameResId = nameResId;
        mDrawableResId = drawableResId;
        mFaces = faces;
    }

    public int[] rollN(int count) {
        int[] arr = new int[count];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(mFaces) + 1;
        }
        return arr;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public int getName() {
        return mNameResId;
    }

    public int getDrawable() {
        return mDrawableResId;
    }

    public int getFaces() {
        return mFaces;
    }
}
