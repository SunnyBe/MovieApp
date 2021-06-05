package com.buchi.fullentry.car

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.buchi.fullentry.R
import com.buchi.fullentry.cars.presentation.CarsActivity
import com.buchi.fullentry.cars.presentation.carlist.CarListFragment
import com.buchi.fullentry.utilities.NavTestUtility
import com.buchi.fullentry.utilities.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@LargeTest
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CarActivityTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityScenarioRule = ActivityScenarioRule(CarsActivity::class.java)

    @get:Rule(order = 2)
    val intentsTestRule = IntentsTestRule(CarsActivity::class.java)

    lateinit var scenario: ActivityScenario<CarsActivity>

    @Before
    fun init() {
        hiltRule.inject()
    }

    @After
    fun close() {
        if (::scenario.isInitialized) scenario.close()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        Assert.assertEquals("com.buchi.fullentry", appContext.packageName)
    }

    @Test
    fun carActivity_opensCarListFragment() {
        // make auth nav host controller
        runBlocking {
            val navController: TestNavHostController = NavTestUtility.testNavHostController(R.navigation.cars_nav)
            launchFragmentInHiltContainer<CarListFragment> {
                view?.let { v ->
                    Navigation.setViewNavController(v, navController)
                }
            }
            assert(navController.currentDestination?.id == R.id.carListFragment)
        }
    }

    @Test
    fun carListFragment_fetchesList_updatesRecyclerViewAdapter() {
        runBlocking {
            val navController: TestNavHostController =
                NavTestUtility.testNavHostController(R.navigation.cars_nav)
            launchFragmentInHiltContainer<CarListFragment> {
                view?.let { v ->
                    Navigation.setViewNavController(v, navController)
                }
            }

            onView(withId(R.id.car_list)).check(matches(isDisplayed()))
        }
    }

}