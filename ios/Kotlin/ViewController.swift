import SnapKit
import UIKit
import common

class ViewController: UIViewController {

    lazy var box = UITextView()

    override func loadView() {
        let view = UIView(frame: UIScreen.main.bounds)
        view.backgroundColor = .gray
        self.view = view
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        self.view.addSubview(box)
        box.snp.makeConstraints { (make) -> Void in
            make.width.height.equalTo(150)
            make.center.equalTo(self.view)
        }

        let x = Proxy().proxyHello()
        box.text = x
    }
}
