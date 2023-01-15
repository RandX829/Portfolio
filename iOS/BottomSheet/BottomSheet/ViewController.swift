import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var show: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @available(iOS 15.0, *)
    @IBAction func btnShowTapped(_ sender: Any) {
        let bottomSheetViewController = UIViewController(nibName: "BottomSheet", bundle: nil)
        let navigationController = UINavigationController(rootViewController: bottomSheetViewController)
        navigationController.modalPresentationStyle = .pageSheet
        if let sheet = navigationController.sheetPresentationController {
            sheet.detents = [.medium(), .large()]
            sheet.preferredCornerRadius = 16
            sheet.prefersGrabberVisible = true
        }
        present(navigationController, animated: true, completion: nil)
    }
}

