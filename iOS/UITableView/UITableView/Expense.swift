import UIKit

struct Expense {
    let date: String
    let amount: String
    let category: String
    let categoryIcon: UIImage?
    let paymentMethod: UIImage?
}

extension Expense {
    static func createDummy() -> [Expense] {
        return [Expense(date: "2022-11-01 19:30", amount: "￥1,000", category: "Groceries", categoryIcon: UIImage(named: "groceries"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-02 15:30", amount: "￥5,000", category: "Electricity", categoryIcon: UIImage(named: "electricity"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-03 15:33", amount: "￥15,000", category: "Clothing", categoryIcon: UIImage(named: "clothing"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-04 20:33", amount: "￥50,000", category: "Medical", categoryIcon: UIImage(named: "medical"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-05 15:25", amount: "￥90,000", category: "Eating Out", categoryIcon: UIImage(named: "eating-out"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-06 16:00", amount: "￥55,000", category: "Beauty", categoryIcon: UIImage(named: "beauty"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-07 11:00", amount: "￥995,000", category: "Other", categoryIcon: UIImage(named: "other"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-08 09:00", amount: "￥5,000", category: "Household Supplies", categoryIcon: UIImage(named: "household-supplies"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-09 09:10", amount: "￥5,000", category: "Transportation", categoryIcon: UIImage(named: "transportation"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-10 22:50", amount: "￥2,000", category: "Water", categoryIcon: UIImage(named: "water"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-11 12:40", amount: "￥3,000", category: "Recreation", categoryIcon: UIImage(named: "recreation"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-12 11:40", amount: "￥4,000", category: "Groceries", categoryIcon: UIImage(named: "groceries"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-13 10:45", amount: "￥5,400", category: "Groceries", categoryIcon: UIImage(named: "groceries"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-14 16:50", amount: "￥6,000", category: "Eating Out", categoryIcon: UIImage(named: "eating-out"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-15 16:55", amount: "￥7,000", category: "Gas", categoryIcon: UIImage(named: "gas"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-16 11:55", amount: "￥8,000", category: "Groceries", categoryIcon: UIImage(named: "groceries"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-17 08:55", amount: "￥11,000", category: "Gas", categoryIcon: UIImage(named: "gas"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-18 18:25", amount: "￥4,000", category: "Clothing", categoryIcon: UIImage(named: "clothing"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-19 12:55", amount: "￥5,000", category: "Groceries", categoryIcon: UIImage(named: "groceries"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-20 12:48", amount: "￥6,000", category: "Household Supplies", categoryIcon: UIImage(named: "household-supplies"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-21 11:18", amount: "￥7,000", category: "Household Supplies", categoryIcon: UIImage(named: "household-supplies"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-22 10:00", amount: "￥5,700", category: "Eating Out", categoryIcon: UIImage(named: "eating-out"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-23 12:00", amount: "￥5,700", category: "Groceries", categoryIcon: UIImage(named: "groceries"), paymentMethod: UIImage(named: "cash")),
                Expense(date: "2022-11-24 19:11", amount: "￥1,300", category: "Groceries", categoryIcon: UIImage(named: "groceries"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-25 11:11", amount: "￥534,000", category: "Groceries", categoryIcon: UIImage(named: "groceries"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-26 16:19", amount: "￥524,000", category: "Eating Out", categoryIcon: UIImage(named: "eating-out"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-27 16:19", amount: "￥54,500", category: "Groceries", categoryIcon: UIImage(named: "groceries"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-28 10:29", amount: "￥5,500", category: "Groceries", categoryIcon: UIImage(named: "groceries"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-29 11:45", amount: "￥4,000", category: "Eating Out", categoryIcon: UIImage(named: "eating-out"), paymentMethod: UIImage(named: "credit-card")),
                Expense(date: "2022-11-30 15:45", amount: "￥1,000", category: "Groceries", categoryIcon: UIImage(named: "groceries"), paymentMethod: UIImage(named: "cash"))]
    }
}
