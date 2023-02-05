import UIKit

struct Category {
    let label: String
    let categoryIcon: UIImage?
}

extension Category {
    static func createDummy() -> [Category] {
        return [
            Category(label: "Groceries", categoryIcon: UIImage(named: "groceries")),
            Category(label: "Eating Out", categoryIcon: UIImage(named: "eating_out")),
            Category(label: "Household-supplies", categoryIcon: UIImage(named: "household_supplies")),
            Category(label: "Housing", categoryIcon: UIImage(named: "housing")),
            Category(label: "Insurance", categoryIcon: UIImage(named: "insurance")),
            Category(label: "Medical", categoryIcon: UIImage(named: "medical")),
            Category(label: "Transportation", categoryIcon: UIImage(named: "transportation")),
            Category(label: "Internet", categoryIcon: UIImage(named: "internet")),
            Category(label: "Cellphone", categoryIcon: UIImage(named: "cellphone")),
            Category(label: "Electricity", categoryIcon: UIImage(named: "electricity")),
            Category(label: "Gas", categoryIcon: UIImage(named: "gas")),
            Category(label: "Water", categoryIcon: UIImage(named: "water")),
            Category(label: "Beauty", categoryIcon: UIImage(named: "beauty")),
            Category(label: "Recreation", categoryIcon: UIImage(named: "recreation")),
            Category(label: "Savings", categoryIcon: UIImage(named: "savings")),
            Category(label: "Automotive", categoryIcon: UIImage(named: "automotive")),
            Category(label: "Clothing", categoryIcon: UIImage(named: "clothing")),
            Category(label: "Donation", categoryIcon: UIImage(named: "donation")),
            Category(label: "Education", categoryIcon: UIImage(named: "education")),
            Category(label: "Social", categoryIcon: UIImage(named: "social")),
            Category(label: "Special", categoryIcon: UIImage(named: "special")),
            Category(label: "Tax", categoryIcon: UIImage(named: "tax")),
            Category(label: "Television", categoryIcon: UIImage(named: "television")),
            Category(label: "Travel", categoryIcon: UIImage(named: "travel")),
            Category(label: "Other", categoryIcon: UIImage(named: "other"))
        ]
    }
}
