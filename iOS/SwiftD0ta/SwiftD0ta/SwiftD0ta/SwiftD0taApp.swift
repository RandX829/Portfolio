import SwiftUI
import SwiftData

@main
struct SwiftD0taApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
        .modelContainer(for: Expense.self)
    }
}
