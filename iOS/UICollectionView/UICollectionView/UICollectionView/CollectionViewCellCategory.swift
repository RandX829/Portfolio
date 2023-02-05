import UIKit

class CollectionViewCellCategory: UICollectionViewCell {
    
    @IBOutlet weak var label: UILabel!
    @IBOutlet weak var categoryIcon: UIImageView!

    func bindCell(_ cell: Category) {
        label.text = cell.label
        categoryIcon.image = cell.categoryIcon
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

}
