import SwiftUI

@main
struct EmojiArtApp: App {
    @ObservedObject var paletteStore = PaletteStore(named: "Main")
    
    var body: some Scene {
        DocumentGroup(newDocument: { EmojiArtDocument() }) { config in
            EmojiArtDocumentView(document: config.document)
                .environmentObject(paletteStore)
        }
    }
}
