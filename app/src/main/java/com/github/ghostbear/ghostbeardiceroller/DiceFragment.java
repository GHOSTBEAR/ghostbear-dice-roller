package com.github.ghostbear.ghostbeardiceroller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

public class DiceFragment extends Fragment {
    private static final String EXTRA_DICE_ID = "com.github.ghostbear.ghostbeardiceroller.dice_id";

    private TextView mNameTextView;
    private ImageView mDiceImageView;
    private EditText mCountEditText;
    private Button mRollButton;
    private TextView mResult;

    private Dice mDice;

    public static DiceFragment newIntent(UUID diceId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_DICE_ID, diceId);

        DiceFragment fragment = new DiceFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            UUID diceId = (UUID) getArguments().getSerializable(EXTRA_DICE_ID);
            mDice = DiceCollection.getInstance(getActivity()).getDice(diceId);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dice, container, false);
        mNameTextView = view.findViewById(R.id.diceName);
        mNameTextView.setText(mDice.getName());

        mDiceImageView = view.findViewById(R.id.diceImage);
        mDiceImageView.setOnClickListener(v -> rollDice());
        mDiceImageView.setImageDrawable(view.getContext().getDrawable(mDice.getDrawable()));

        mCountEditText = view.findViewById(R.id.rollCount);

        mRollButton = view.findViewById(R.id.rollButton);
        mRollButton.setOnClickListener(v -> rollDice());

        mResult = view.findViewById(R.id.result);

        return view;
    }

    private void rollDice() {
        String text = mCountEditText.getText().toString();
        if (text.isEmpty()) {
            return;
        }
        if (!text.matches("^[0-9]*$")) {
            return;
        }
        int count = Integer.parseInt(text);
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

}
