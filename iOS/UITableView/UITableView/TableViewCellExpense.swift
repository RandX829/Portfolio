import UIKit

class TableViewCellExpense: UITableViewCell {

    @IBOutlet weak var date: UILabel!
    @IBOutlet weak var amount: UILabel!
    @IBOutlet weak var category: UILabel!
    @IBOutlet weak var categoryIcon: UIImageView!
    @IBOutlet weak var paymentMethod: UIImageView!
    
    func bindCell(_ cell: Expense) {
        date.text = cell.date
        amount.text = cell.amount
        category.text = cell.category
        categoryIcon.image = cell.categoryIcon
        paymentMethod.image = cell.paymentMethod
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
}
