package com.example.messenger.ui.fragments

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.messenger.R
import com.example.messenger.utilits.AppTextWatcher
import kotlinx.android.synthetic.main.fragment_enter_code.*


class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {
    override fun onStart() {
        super.onStart()
        register_input_code.addTextChangedListener(AppTextWatcher {
            val string = register_input_code.text.toString()
            if (string.length == 6) {
                verifiCode()
            }


        })
    }

    fun verifiCode() {
        Toast.makeText(activity, "OK", Toast.LENGTH_SHORT).show()
    }
}