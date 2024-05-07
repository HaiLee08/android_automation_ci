package com.example.loginregistrationfragmentapp.pages

import android.util.Log
import android.view.accessibility.AccessibilityEvent
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import androidx.test.platform.app.InstrumentationRegistry
import com.example.loginregistrationfragmentapp.testdata.Locators

open class BasePage(protected val device: UiDevice) {

    fun isMessageDisplayed(message: String): Boolean {
        return device.wait(Until.hasObject(By.text(message)), 1000)
    }

    fun isToastDisplayed(text: String, action: Runnable): Boolean {
        var toastDisplayed = false
        val startTimeMs = System.currentTimeMillis()
        Log.d(Locators.packageName, "isToastDisplayed: start waiting")
        InstrumentationRegistry.getInstrumentation().uiAutomation.setOnAccessibilityEventListener { event ->
            Log.d(Locators.packageName, "isToastDisplayed: event")
            if (event.eventType == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED
                && Locators.packageName == event.packageName.toString()
                && event.className.toString().contains(android.widget.Toast::class.java.name)
                && event.text.toString().contains(text)
            ) {
                Log.d(Locators.packageName, "isToastDisplayed: TOAST")
                Log.d(Locators.packageName, event.text.toString())
                toastDisplayed = true
            }
            event.recycle()
        }
        action.run()
        while (!toastDisplayed && System.currentTimeMillis() - startTimeMs < 5000) {
            Log.d(Locators.packageName, "isToastDisplayed: sleep")
            Thread.sleep(1000)
        }
        InstrumentationRegistry.getInstrumentation().uiAutomation.setOnAccessibilityEventListener(null)
        return toastDisplayed
    }

    fun waitForElementToAppear(selector: UiSelector, timeout: Long = 5000L) {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeout) {
            if (device.findObject(selector).exists()) {
                return
            }
            Thread.sleep(1000)
        }
        throw AssertionError("Element not found after $timeout ms")
    }
}