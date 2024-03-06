import SwiftUI

struct ExpenseItemView: View {
    let expense: Expense
    
    var body: some View {
        HStack(alignment: .center) {
            VStack(alignment: .leading) {
                Text(expense.date)
                Text(expense.category)
            }
            Spacer()
            Text("¥\(expense.amount)")
        }
    }
}
