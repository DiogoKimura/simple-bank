package com.example.simplebank.ui.product

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.simplebank.R
import com.google.android.material.tabs.TabLayout
import org.hamcrest.core.AllOf.allOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductFragmentTest {

    @Test
    fun whenLaunchScreen_shouldShowTabAndPage() {
        launchFragmentInContainer<ProductFragment>(themeResId = R.style.SimpleBankTheme)
        onView(withId(R.id.product_view_pager)).check(matches(isDisplayed()))
        onView(withId(R.id.product_tab_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun whenLaunchScreen_andClickOnCreditTab_shouldShowCreditPage() {
        launchFragmentInContainer<ProductFragment>(themeResId = R.style.SimpleBankTheme)
        onView(withId(R.id.product_tab_layout)).perform(selectTabAtPosition(1))
        onView(withId(R.id.product_tab_layout)).check(checkTabAtPosition(1))
    }

    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() =
                allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }

    private fun checkTabAtPosition(tabIndex: Int): ViewAssertion {
        return ViewAssertion { view, _ ->
            val tabLayout = view as TabLayout
            assert(tabLayout.selectedTabPosition == tabIndex)
        }
    }
}