import SwiftUI

struct ContentView: View {
    let expenses: [Expense]
    
    var body: some View {
        NavigationStack {
            List(expenses) { expense in
                NavigationLink(expense.label, value: expense)
            }
            .navigationTitle("Expenses")
            .navigationDestination(for: Expense.self) { expense in
                ExpenseDetialsView(amount: expense.amount)
            }
        }
    }
}

#Preview {
    ContentView(expenses: Expense.sample)
}
