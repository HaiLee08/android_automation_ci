package com.example.loginregistrationfragmentapp.pages

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.example.loginregistrationfragmentapp.testdata.Locators

class LoginPage(device: UiDevice) : BasePage(device) {

    private val usernameField = device.findObject(By.res(Locators.userNameId))
    private val passwordField = device.findObject(By.res(Locators.passwordId))
    private val loginButton = device.findObject(By.res(Locators.loginButtonId))

    fun enterUsername(username: String) {
        usernameField.text = username
    }

    fun enterPassword(password: String) {
        passwordField.text = password
    }

    fun clickLoginButton() {
        loginButton.click()
    }
}