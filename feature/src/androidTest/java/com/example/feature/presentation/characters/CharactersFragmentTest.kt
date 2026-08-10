package com.example.feature.presentation.characters

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.runner.AndroidJUnit4
import com.example.feature.HiltTestActivity
import com.example.feature.R
import com.example.feature.ext.asJsonString
import com.example.feature.framework.di.BaseUrlModule
import com.example.feature.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@UninstallModules(BaseUrlModule::class)
@HiltAndroidTest
class CharactersFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var server: MockWebServer

    @Before
    fun setup() {
        hiltRule.inject()

        server = MockWebServer().apply {
            start(8080)
        }

        launchFragmentInHiltContainer<CharactersFragment>(
            themeResId = R.style.Theme_RickAndMorty_app
        )
    }

    @Test
    fun shouldShowCharacters_whenViewIsCreated() {
        val response = MockResponse.Builder()
            .code(200)
            .body("characters_01.json".asJsonString())
            .build()
        server.enqueue(response)

        onView(withId(R.id.recycler_characters))
            .check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        server.close()
    }
}
