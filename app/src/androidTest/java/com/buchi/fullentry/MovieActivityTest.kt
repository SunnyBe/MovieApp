package com.buchi.fullentry

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.buchi.fullentry.movie.presentation.MovieActivity
import com.buchi.fullentry.movie.presentation.movielist.MovieListFragment
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
class MovieActivityTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityScenarioRule = ActivityScenarioRule(MovieActivity::class.java)

    @get:Rule(order = 2)
    val intentsTestRule = IntentsTestRule(MovieActivity::class.java)

    lateinit var scenario: ActivityScenario<MovieActivity>

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
    fun movieActivity_opensMovieListFragment() {
        // make auth nav host controller
        runBlocking {
            val navController: TestNavHostController =
                NavTestUtility.testNavHostController(R.navigation.movies_nav)
            launchFragmentInHiltContainer<MovieListFragment> {
                view?.let { v ->
                    Navigation.setViewNavController(v, navController)
                }
            }

            // Assert activity switch to MainActivity
//            intended(hasComponent(MovieActivity::class.java.name))
            // Assert navigation was done to MovieListFragment page
            assert(navController.currentDestination?.id == R.id.movieListFragment)
        }
    }

    @Test
    fun movieListFragment_fetchesList_updatesRecyclerViewAdapter() {
        runBlocking {
            println("Before Navigation Res:${R.navigation.movies_nav}")
            val navController: TestNavHostController =
                NavTestUtility.testNavHostController(R.navigation.movies_nav)
            launchFragmentInHiltContainer<MovieListFragment> {
                view?.let { v ->
                    Navigation.setViewNavController(v, navController)
                }
            }
        }
    }

}