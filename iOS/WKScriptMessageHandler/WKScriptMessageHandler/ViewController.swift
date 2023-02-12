import Foundation
import UIKit
import WebKit

class ViewController: UIViewController, WKUIDelegate {
    
    @IBOutlet weak var webview: WKWebView!
    @IBOutlet weak var textview: UITextView!

    override func viewDidLoad() {
        super.viewDidLoad()
        webview.uiDelegate = self
        
        webview.configuration.defaultWebpagePreferences.allowsContentJavaScript = true
        webview.configuration.userContentController.add(self, name: "CustomWKScriptMessageHandler")
        let scriptString = """
window.addEventListener ('message', event => {
        window.webkit.messageHandlers.CustomWKScriptMessageHandler.postMessage(JSON.stringify(event.data));
        });
"""
        let script = WKUserScript(source: scriptString, injectionTime: .atDocumentEnd, forMainFrameOnly: true)
        webview.configuration.userContentController.addUserScript(script)
        let url = Bundle.main.url(forResource: "index", withExtension: "html")
        let request = URLRequest(url: url!)
        webview.load(request)
        print("WebView loaded")
    }
    
    @IBAction func sendHello2Web(_sender: Any) {
        webview.evaluateJavaScript("window.postMessage({ type: \"native_to_web\", payload: \"Hello from Native\" }, \"*\");")
        print("Hello clicked")
    }
    
}

extension ViewController: WKScriptMessageHandler {
    func userContentController(_ userContentController: WKUserContentController, didReceive message: WKScriptMessage) {
        print("Message received")
                
        let body = message.body as! String
        let data = body.data(using: .utf8)!
        do {
            if let msg = try JSONSerialization.jsonObject(with: data, options : .allowFragments) as? Dictionary<String, String>
            {
                let type = msg["type"]!
                if(type == "web_to_native") {
                    let payload = msg["payload"]!
                    print(payload)
                    let date = Date()
                    let hour = (Calendar.current.component(.hour, from: date))
                    let min = (Calendar.current.component(.minute, from: date))
                    let sec = (Calendar.current.component(.second, from: date))
                    let time = "\(hour):\(min):\(sec)"
                    print(time)
                    textview.text += time + "\n" + payload + "\n"
                }
            } else {
                print("bad json")
            }
        } catch let error as NSError {
            print(error)
        }
    }
}
