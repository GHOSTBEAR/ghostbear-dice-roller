package com.github.ghostbear.ghostbeardiceroller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DiceFragment extends Fragment {
    public static final String ARG_OBJECT = "dice_object";

    private String mParam;

    private TextView mNameTextView;
    private ImageView mDiceImageView;
    private EditText mCountEditText;
    private Button mRollButton;
    private TextView mResult;

    private Dice mDice;

    public DiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_OBJECT);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNameTextView = view.findViewById(R.id.diceName);

        mDiceImageView = view.findViewById(R.id.diceImage);
        mDiceImageView.setOnClickListener(v -> rollDice());

        mCountEditText = view.findViewById(R.id.rollCount);

        mRollButton = view.findViewById(R.id.rollButton);
        mRollButton.setOnClickListener(v -> rollDice());

        mResult = view.findViewById(R.id.result);

        update(view);
    }

    private void update(View view) {
        mNameTextView.setText(mDice.getName());
        mDiceImageView.setImageDrawable(view.getContext().getDrawable(mDice.getDrawable()));
    }

    private void rollDice() {
        int count = Integer.parseInt(mCountEditText.getText().toString());
        int[] array = mDice.rollN(count);
        int sum = sum(array);
        updateResult(sum, array);
    }

    private int sum(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }

    private void updateResult(int sum, int[] array) {
        if (array.length == 1) {
            mResult.setText(String.valueOf(sum));
        } else if (array.length > 10) {
            mResult.setText(String.valueOf(sum));
        } else {
            String text = intArrayToString(" + ", array);
            text += " = " + sum;
            mResult.setText(text);
        }
    }

    private String intArrayToString(String delimiter, int[] array) {
        StringBuilder text = new StringBuilder();
        text.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            text.append(delimiter).append(array[i]);
        }
        return text.toString();
    }

    public void setDice(Dice dice) {
        mDice = dice;
    }
}
