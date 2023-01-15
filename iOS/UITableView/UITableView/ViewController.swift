import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var expenseTableView: UITableView!
    
    private var expenses:[Expense] = Expense.createDummy()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        expenseTableView.register(UINib(nibName: "TableViewCellExpense", bundle: nil), forCellReuseIdentifier: "TableViewCellExpense")
        expenseTableView.dataSource = self
        expenseTableView.delegate = self
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
