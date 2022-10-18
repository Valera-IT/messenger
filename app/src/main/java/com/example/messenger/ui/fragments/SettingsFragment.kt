package com.example.messenger.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.messenger.MainActivity
import com.example.messenger.R
import com.example.messenger.activities.RegisterActivity
import com.example.messenger.utilits.AUTH
import com.example.messenger.utilits.replaceActivity


class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        @Suppress("DEPRECATION")
        setHasOptionsMenu(true)
    }

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)",
            "com.example.messenger.R"
        )
    )
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
        }
        return true
    }
}