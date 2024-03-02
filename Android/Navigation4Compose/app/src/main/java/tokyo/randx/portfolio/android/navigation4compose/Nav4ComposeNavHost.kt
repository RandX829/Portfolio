package tokyo.randx.portfolio.android.navigation4compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Nav4ComposeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ExpenseList.route,
        modifier = modifier
    ) {
        composable(route = ExpenseList.route) {
            ExpenseListScreen(
                onItemClick = {
                    navController.navigate(ExpenseDetails.route + "/" + it)
                }
            )
        }
        composable(
            route = ExpenseDetails.route + "/" + "{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType})
            ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            ExpenseDetailsScreen(
                id = id
            )
        }
    }
}