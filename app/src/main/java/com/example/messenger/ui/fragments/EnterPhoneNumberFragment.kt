package com.example.messenger.ui.fragments

import androidx.fragment.app.Fragment

import com.example.messenger.MainActivity
import com.example.messenger.R
import com.example.messenger.activities.RegisterActivity
import com.example.messenger.utilits.AUTH
import com.example.messenger.utilits.replaceActivity
import com.example.messenger.utilits.replaceFragment
import com.example.messenger.utilits.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import java.util.concurrent.TimeUnit


class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    private lateinit var mPhoneNumber: String
    private lateinit var mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onStart() {
        super.onStart()
        /* Callback который возвращает результат верификации */
        mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                /* Функция срабатывает если верификация уже была произведена
                * Пользователь авторизируется в приложении без потверждения по смс */
                AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Добро пожаловать")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else showToast(task.exception?.message.toString())
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                /* Функция срабатывает если верификация не удалась*/
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                /* Функция срабатывает если верификация впервые, и отправлена смс */
                replaceFragment(EnterCodeFragment(mPhoneNumber, id))
            }
        }
        register_btn_next.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        /* Функция проверяет поле для ввода номер телефона, если поле пустое выводит сообщение.
        * Если поле не пустое, то начинает процедуру авторизации/ регистрации */
        if (register_input_phone_number.text.toString().isEmpty()) {
            showToast(getString(R.string.register_toast_enter_phone))
        } else {
            authUser()
        }
    }

    private fun authUser() {
        /* Инициализация юзера*/
        mPhoneNumber = register_input_phone_number.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber,
            60,
            TimeUnit.SECONDS,
            activity as RegisterActivity,
            mCallBack
        )
        /* Предыдущая функция устарела
        * Хотя добавил капчу в билдгредл, есть проблема с прохождением капчи на виртуальных телефонах
        * Возможное решение*/
//        PhoneAuthProvider.verifyPhoneNumber(
//                PhoneAuthOptions
//                .newBuilder(FirebaseAuth.getInstance())
//                .setActivity(activity as RegisterActivity)
//                .setPhoneNumber(mPhoneNumber)
//                .setTimeout(60L, TimeUnit.SECONDS)
//                .setCallbacks(mCallback)
//                .build()
//        )
    }
}