package com.buchi.fullentry.utilities

import androidx.annotation.NavigationRes
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.statement.UiThreadStatement

object NavTestUtility {

    /**
     * Create test nav controller with navigation graph and return the nav controller instance
     * @param navigationRes Navigation resource to be be set for the navController graph.
     * @return TestNavHostController instance to perform navigation tests with.
     */
    fun testNavHostController(@NavigationRes navigationRes: Int): TestNavHostController {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        UiThreadStatement.runOnUiThread {
            println("Navigation Res:$navigationRes")
            navController.setGraph(navigationRes)
        }
        return navController
    }

}