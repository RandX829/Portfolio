import SwiftUI

struct ScrumsView: View {
    @State private var shouldPresentingNewScrumView = false
    @Environment(\.scenePhase) private var scenePhase
    @Binding var scrums: [DailyScrum]
    let saveAction: () -> Void
    
    var body: some View {
        NavigationStack {
            List($scrums) { $scrum in
                NavigationLink(destination: DetailView(scrum: $scrum)) {
                    CardView(scrum: scrum)
                }
                .listRowBackground(scrum.theme.mainColor)
            }
            .navigationTitle("Daily Scrums")
            .toolbar {
                Button(action: {
                    shouldPresentingNewScrumView = true
                }) {
                    Image(systemName: "plus")
                }
                .accessibilityLabel("New Scrum")
            }
            .sheet(isPresented: $shouldPresentingNewScrumView) {
                NewScrumSheet(scrums: $scrums, shouldPresentingNewScrumView: $shouldPresentingNewScrumView)
            }
            .onChange(of: scenePhase) { phase in
                if phase == .inactive { saveAction() }
            }
        }
    }
}

#Preview {
    ScrumsView(scrums: .constant(DailyScrum.sampleData), saveAction: {})
}
