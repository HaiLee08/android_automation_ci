package com.example.loginregistrationfragmentapp.specs

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import org.junit.Before
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.example.loginregistrationfragmentapp.MainActivity
import com.example.loginregistrationfragmentapp.testdata.Locators
import junit.framework.TestCase
import org.junit.Rule

private const val BASIC_SAMPLE_PACKAGE = "com.example.loginregistrationfragmentapp.MainActivity"
private const val LAUNCH_TIMEOUT = 5000L


abstract class BaseTest {
    protected var device: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

//    @get:Rule
//    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        TestCase.assertNotNull(launcherPackage)
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Launch the app
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        context.startActivity(context.packageManager.getLaunchIntentForPackage(Locators.packageName))
////        val context = ApplicationProvider.getApplicationContext<Context>()
//        val intent = context.packageManager.getLaunchIntentForPackage(
//            BASIC_SAMPLE_PACKAGE)?.apply {
//            // Clear out any previous instances
//            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        }
//        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(Locators.packageName).depth(0)),
            LAUNCH_TIMEOUT
        )

        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            awaitSetup()
        }
    }


    protected open fun awaitSetup() {
    }



//@Before
//    fun setUp() {
//        // Initialize UI Automator
////        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
////        InstrumentationRegistry.getInstrumentation().runOnMainSync {
////            awaitSetup()
////
//////            // Launch the app
//////            val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
//////            context.startActivity(context.packageManager.getLaunchIntentForPackage(Locators.packageName))
////        }
//        activityScenarioRule.scenario.onActivity { activity ->
//            awaitSetup()
//        }
//
//
//////        // Waits for the app to fully launch and idle before proceeding with tests
////        awaitSetup()
//    }
//
////    fun backgroundActivity() {
////        activityScenarioRule.scenario.close()
////    }
//
////    fun restartActivity() {
////        backgroundActivity()
////        activityScenario = ActivityScenario.launch(MainActivity::class.java)
////    }
//
//    protected open fun awaitSetup() {
////        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
//    }
//
//    protected open fun afterTest() {
//
//    }
//
//    @After
//    fun tearDown() {
//        // Close app and release resources
//        afterTest()
//    }
}
