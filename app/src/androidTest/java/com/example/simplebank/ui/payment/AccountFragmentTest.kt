package com.example.simplebank.ui.payment


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.simplebank.R
import com.example.simplebank.ui.product.AccountFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AccountFragmentTest {

    @Test
    fun whenLaunchScreen_shouldLoadAccountList() {
        launchFragmentInContainer<AccountFragment>()
        onView(withId(R.id.account_transaction_container)).check(matches(isDisplayed()))
    }
}
