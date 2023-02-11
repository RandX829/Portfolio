import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var show: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @available(iOS 15.0, *)
    @IBAction func btnShowTapped(_ sender: Any) {
        let modalViewController = UIViewController(nibName: "Modal", bundle: nil)
        let navigationController = UINavigationController(rootViewController: modalViewController)
        navigationController.modalPresentationStyle = .pageSheet
        if let sheet = navigationController.sheetPresentationController {
            sheet.detents = [.medium(), .large()]
            sheet.preferredCornerRadius = 16
            sheet.prefersGrabberVisible = true
        }
        present(navigationController, animated: true, completion: nil)
    }
}

