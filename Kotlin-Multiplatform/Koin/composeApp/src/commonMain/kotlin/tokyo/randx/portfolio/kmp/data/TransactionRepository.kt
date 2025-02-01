package tokyo.randx.portfolio.kmp.data

interface TransactionRepository {
    fun findTransaction(transactionId: String): Transaction?
    fun addTransaction(transactions: List<Transaction>)
}

class TransactionRepositoryImpl : TransactionRepository {
    private val _transactions = arrayListOf<Transaction>()

    init {
        addTransaction(dummyTransactions)
    }

    override fun findTransaction(transactionId: String): Transaction? {
        return _transactions.firstOrNull { it.transactionId == transactionId }
    }

    override fun addTransaction(transactions: List<Transaction>) {
        _transactions.addAll(transactions)
    }
}
