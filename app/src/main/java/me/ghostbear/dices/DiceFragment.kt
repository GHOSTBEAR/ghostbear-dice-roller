package me.ghostbear.dices

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.ghostbear.dices.databinding.FragmentDiceBinding

private const val ARG_DICE_FACES = "argDiceFaces"

class DiceFragment : Fragment() {
    private var diceFaces: Int = 20
    private var diceCount: Int = 1

    private var _binding: FragmentDiceBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            diceFaces = it.getInt(ARG_DICE_FACES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiceBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etDiceFaces = binding.diceFaces
        etDiceFaces.setText(diceFaces.toString())
        etDiceFaces.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.isNotEmpty()) {
                    diceFaces = s.toString().toInt()
                }
            }
        })

        binding.diceCount.setText("1")
        binding.diceCount.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.isNotEmpty()) {
                    diceCount = s.toString().toInt()
                }
            }
        })

        binding.diceRoll.setOnClickListener {

            val diceArray = Array(diceCount) { i -> (Math.random() * diceFaces).toInt() + 1}
            binding.diceArray.text = diceArray.joinToString(" + ")
            binding.diceRolled.text = diceArray.sum().toString()
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(faces: Int) =
            DiceFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_DICE_FACES, faces)
                }
            }
    }
}
