package tokyo.randx.portfolio.android.navigation4compose

interface Nav4ComposeDestination {
    val route: String
}

object ExpenseList: Nav4ComposeDestination {
    override val route = "list"
}

object ExpenseDetails: Nav4ComposeDestination {
    override val route = "details"
}