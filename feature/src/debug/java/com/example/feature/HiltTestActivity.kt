package com.example.feature

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltTestActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val theme = intent.getIntExtra(
            "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY",
            R.style.Theme_RickAndMorty_app
        )
        setTheme(theme)
        super.onCreate(savedInstanceState)
    }
}
