import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var collectionViewCategory: UICollectionView!
    
    private var categoryList: [Category] = Category.createDummy()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        collectionViewCategory.register(UINib(nibName: "CollectionViewCellCategory", bundle: nil), forCellWithReuseIdentifier: "CollectionViewCellCategory")
        collectionViewCategory.dataSource = self
        collectionViewCategory.delegate = self
    }
}

extension ViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        collectionViewCategory.deselectItem(at: indexPath, animated: true)
    }
}

extension ViewController: UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return categoryList.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        if let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "CollectionViewCellCategory", for: indexPath) as? CollectionViewCellCategory {
            cell.bindCell(categoryList[indexPath.row])
            return cell
        }
        return UICollectionViewCell()
    }
}

extension ViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: 72, height: 96)
    }
}
