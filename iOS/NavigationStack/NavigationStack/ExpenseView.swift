import SwiftUI

struct ExpenseView: View {
    let expense: Expense
    
    var body: some View {
        HStack {
            Text(expense.label)
            Spacer()
            Text("\(expense.amount)")
        }
    }
}

#Preview {
    ExpenseView(expense: Expense.sample[0])
}
