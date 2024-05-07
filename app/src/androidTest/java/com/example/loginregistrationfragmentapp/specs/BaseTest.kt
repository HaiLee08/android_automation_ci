package com.example.loginregistrationfragmentapp.specs

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.After
import androidx.test.uiautomator.UiDevice
import com.example.loginregistrationfragmentapp.MainActivity
import com.example.loginregistrationfragmentapp.testdata.Locators
import org.junit.Rule

abstract class BaseTest {
    protected var device: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        // Initialize UI Automator
//        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
//        InstrumentationRegistry.getInstrumentation().runOnMainSync {
//            awaitSetup()
//
////            // Launch the app
////            val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
////            context.startActivity(context.packageManager.getLaunchIntentForPackage(Locators.packageName))
//        }
        activityScenarioRule.scenario.onActivity { activity ->
            awaitSetup()
        }


////        // Waits for the app to fully launch and idle before proceeding with tests
//        awaitSetup()
    }

//    fun backgroundActivity() {
//        activityScenarioRule.scenario.close()
//    }

//    fun restartActivity() {
//        backgroundActivity()
//        activityScenario = ActivityScenario.launch(MainActivity::class.java)
//    }

    protected open fun awaitSetup() {
//        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }

    protected open fun afterTest() {

    }

    @After
    fun tearDown() {
        // Close app and release resources
        afterTest()
    }
}