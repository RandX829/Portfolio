package tokyo.randx.portfolio.android.navigation4compose

data class Expense(
    val id: Int,
    val category: String,
    val amount: Int
)

val expenses = mutableListOf<Expense> (
    Expense(id = 1, category = "Grocery", amount = 1000),
    Expense(id = 2, category = "Medical", amount = 5000),
    Expense(id = 3, category = "Transport", amount = 500)
)

