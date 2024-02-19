import SwiftUI

@main
struct NavigationStackApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView(expenses: Expense.sample)
        }
    }
}
