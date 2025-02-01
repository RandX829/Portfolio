package tokyo.randx.portfolio.kmp.viewmodel

import androidx.lifecycle.ViewModel
import tokyo.randx.portfolio.kmp.Platform
import tokyo.randx.portfolio.kmp.data.TransactionRepository

class TransactionViewModel(
    private val repository: TransactionRepository,
    private val platform: Platform
) : ViewModel() {
    fun showTransaction(transactionId: String): String {
        val transaction = repository.findTransaction(transactionId)
        return transaction?.let { "ID is $transactionId Amount is ${it.amount} " } ?: "Transaction $transactionId not found!"
    }

    fun getPlatform() = platform.name
}