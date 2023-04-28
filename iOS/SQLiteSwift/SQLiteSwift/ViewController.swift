import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var expenseTableView: UITableView!
    
    private var expenseViewModel = ExpenseViewModel()
    private var expenses:[Expense] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        expenseTableView.register(UINib(nibName: "TableViewCellExpense", bundle: nil), forCellReuseIdentifier: "TableViewCellExpense")
        expenseTableView.dataSource = self
        expenseTableView.delegate = self
        expenses = ExpenseDataSource.shared.getExpenseList()
    }
    
    @IBAction func newExpense(_ sender: Any) {
        
        let count = expenses.count
        let id = String(count + 1)
        let date = "2023-04-0" + id
        let amount = count * 5 + 500
        let expense = Expense(recordId: id, date: date, category: "groceries", paymentMethod: "cash", amount: amount, memo: "Food")
        
        if expenseViewModel.createExpense(expense: expense) != nil {
            expenses = expenseViewModel.getExpenseList()
            expenseTableView.reloadData()
        }
    }
    
    @IBAction func deleteLatestExpense(_ sender: Any) {
        let index = expenses.count - 1
        if index <= 0 {
            return
        }
        
        if expenseViewModel.deleteExpense(expense: expenses[index]) {
            expenses = expenseViewModel.getExpenseList()
            expenseTableView.reloadData()
        }
    }
    
    @IBAction func deleteAllExpenseList(_ sender: Any) {
        if expenseViewModel.deleteAllExpenseList() {
            expenses = expenseViewModel.getExpenseList()
            expenseTableView.reloadData()
        }
    }
}

extension ViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return expenses.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let cell = tableView.dequeueReusableCell(withIdentifier: "TableViewCellExpense") as? TableViewCellExpense {
            cell.bindCell(expenses[indexPath.row])
            return cell
        }
        return UITableViewCell()
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 96
    }
}

extension ViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        expenseTableView.deselectRow(at: indexPath, animated: true)
    }
}
