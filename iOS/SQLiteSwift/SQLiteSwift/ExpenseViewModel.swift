import Foundation

class ExpenseViewModel {
        
    func getExpenseList() -> [Expense] {
        return ExpenseDataSource.shared.getExpenseList()
    }
    
    func deleteExpense(expense: Expense) -> Bool {
        return ExpenseDataSource.shared.delete(expense: expense)
    }
    
    func deleteAllExpenseList() -> Bool {
        return ExpenseDataSource.shared.deleteAll()
    }
    
    func createExpense(expense: Expense) -> Int64? {
        return ExpenseDataSource.shared.insert(expense: expense)
    }
    
}
