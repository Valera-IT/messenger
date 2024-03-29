package com.example.messenger.ui.fragments

import androidx.fragment.app.Fragment
import com.example.messenger.MainActivity
import com.example.messenger.R
import com.example.messenger.activities.RegisterActivity
import com.example.messenger.utilits.AUTH
import com.example.messenger.utilits.AppTextWatcher
import com.example.messenger.utilits.replaceActivity
import com.example.messenger.utilits.showToast
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*


/* Фрагмент для ввода кода подтверждения при регистрации */
class EnterCodeFragment(val phoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {
    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher {
            val string = register_input_code.text.toString()
            if (string.length == 6) {
                enterCode()
            }
        })
    }

    private fun enterCode() {
        /* Функция проверяет код
         если всё нормально, создает информацию о пользователе в базе данных.*/
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showToast("Добро пожаловать")
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else showToast(task.exception?.message.toString())
        }
    }
}