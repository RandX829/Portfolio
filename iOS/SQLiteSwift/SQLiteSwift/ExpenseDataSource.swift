import Foundation
import SQLite

class ExpenseDataSource {
    
    static let shared = ExpenseDataSource()

    private var db: Connection? = nil
    
    private let DATABASE_NAME = "sqliteswift.db"
    
    private let tableExpense = Table("expense")
    
    private let recordId = Expression<String>("record_id")
    private let date = Expression<String>("date")
    private let category = Expression<String>("category")
    private let paymentMethod = Expression<String>("payment_method")
    private let memo = Expression<String>("memo")
    private let amount = Expression<Int>("amount")
    
    private init() {
        
        guard let dbPath = getPath() else {
            return
        }
        print(dbPath)
        
        do {
            db = try Connection(dbPath)
            
            guard let database = db else {
                return
            }

            try database.run(tableExpense.create(ifNotExists: true) { t in
                t.column(recordId, primaryKey: true)
                t.column(date)
                t.column(category)
                t.column(paymentMethod)
                t.column(amount)
                t.column(memo)
            })
            
        } catch {
            db = nil
            print("Error occurred")
        }
    }
    
    func insert(expense: Expense) -> Int64? {
        guard let database = db else { return nil }

        let insert = tableExpense.insert(self.recordId <- expense.recordId,
                                    self.date <- expense.date,
                                    self.category <- expense.category,
                                    self.paymentMethod <- expense.paymentMethod,
                                    self.amount <- expense.amount,
                                    self.memo <- expense.memo)
        do {
            let rowID = try database.run(insert)
            return rowID
        } catch {
            print(error)
            return nil
        }
    }
    
    func getExpenseList() -> [Expense] {
        var expenseList: [Expense] = []
        guard let database = db else { return [] }

        do {
            for expense in try database.prepare(self.tableExpense) {
                expenseList.append(Expense(recordId: expense[recordId],
                                           date: expense[date],
                                           category: expense[category],
                                           paymentMethod: expense[paymentMethod],
                                           amount: expense[amount],
                                           memo: expense[memo]))
            }
        } catch {
            print(error)
        }
        
        return expenseList
    }
    
    func delete(expense: Expense) -> Bool {
        guard let database = db else {
            return false
        }
        
        do {
            let filter = tableExpense.filter(self.recordId == expense.recordId)
            try database.run(filter.delete())
            return true
        } catch {
            print(error)
            return false
        }
    }
    
    func deleteAll() -> Bool {
        guard let database = db else {
            return false
        }
        
        do {
            try database.run(tableExpense.delete())
            return true
        } catch {
            print(error)
            return false
        }
    }
    
    private func getPath() -> String? {
        let paths = NSSearchPathForDirectoriesInDomains(.documentDirectory, .userDomainMask, true)
        
        guard let documentPath = paths.first else {
            return nil
        }
        let path = documentPath + "/Expense.sqlite3"
        
        return path
    }
}
