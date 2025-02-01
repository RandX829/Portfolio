package tokyo.randx.portfolio.kmp.data

data class Transaction(
    val transactionId: String,
    val amount: Int
)

val dummyTransactions = listOf(
    Transaction("1", 5000),
    Transaction("2", 1000),
    Transaction("3", 10000)
)
