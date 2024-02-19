import SwiftUI

struct ExpenseDetialsView: View {
    let amount: Int
    
    var body: some View {
        Text("\(amount)")
            .font(.largeTitle)
    }
}

#Preview {
    ExpenseDetialsView(amount: 500)
}
