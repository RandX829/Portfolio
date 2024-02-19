import Foundation
import SwiftUI

struct Expense: Hashable, Identifiable {
    let id: UUID
    let label: String
    let amount: Int
    
    init(label: String, amount: Int) {
        self.id = UUID()
        self.label = label
        self.amount = amount
    }
}

extension Expense {
    static let sample: [Expense] =
    [
        Expense(label: "Grocery", amount: 500),
        Expense(label: "Transportation", amount: 300),
        Expense(label: "Medical", amount: 800),
    ]
}
