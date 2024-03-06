import SwiftUI
import SwiftData

struct ContentView: View {
    @Environment(\.modelContext) var context
    @State var category: String = ""
    @Query var expenses: [Expense]
    
    var body: some View {
        VStack {
            TextField("Category", text: $category)
                .textFieldStyle(RoundedBorderTextFieldStyle())
            Button("Add") {
                let exp = Expense(date: "2024-02-06", amount: Int.random(in: 100..<1000), category: category)
                context.insert(exp)
                category = ""
            }
            List {
                ForEach(expenses) {
                    expense in
                    ExpenseItemView(expense: expense)
                    
                }
                .onDelete { indexSet in
                    indexSet.forEach { index in
                        context.delete(expenses[index])
                    }
                }
            }
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
