import UIKit
import SafariServices

class ViewController: UIViewController, SFSafariViewControllerDelegate {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }

    @IBAction func browse(_ sender: Any) {
        guard let link = URL(string: "https://github.com/RandX829/Portfolio") else {
            return
        }
        let safariViewController = SFSafariViewController(url: link)
        safariViewController.delegate = self
        present(safariViewController, animated: true)
    }

}

