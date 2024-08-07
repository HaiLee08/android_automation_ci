package com.example.loginregistrationfragmentapp.specs

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.loginregistrationfragmentapp.pages.LoginPage
import com.example.loginregistrationfragmentapp.utilities.StringUtil
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert

@RunWith(AndroidJUnit4::class)
class LoginTest: BaseTest() {
    private lateinit var loginPage: LoginPage

    override fun awaitSetup() {
        super.awaitSetup()
        loginPage = LoginPage(this.device)
    }

    @Test
    fun testLogin() {
        loginPage.enterUsername("auto+st90@qa.team")
        loginPage.enterPassword("Evizi@123")

        // Assertions: verify the login functionality
        val action = Runnable {
            loginPage.clickLoginButton()
        }
        Assert.assertTrue(loginPage.isToastDisplayed(text = "Login button clicked!", action))
    }

    @Test
    fun testLoginFailure() {
        loginPage.enterUsername("invalid")
        loginPage.enterPassword(StringUtil.randomString(10))

        // Assertions: verify the login functionality
        val action = Runnable {
            loginPage.clickLoginButton()
        }
        Assert.assertTrue(loginPage.isToastDisplayed(text = "Failed login!", action))
    }
}
