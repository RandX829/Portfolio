import Foundation
import SwiftData

@Model
class Expense: Identifiable {
    @Attribute(.unique) let id: UUID
    let date: String
    let amount: Int
    let category: String
    
    init(id: UUID = UUID(), date: String, amount: Int, category: String) {
        self.id = id
        self.date = date
        self.amount = amount
        self.category = category
    }
}
