import SnapKit
import UIKit
import common

class ViewController: UIViewController {

    lazy var box = UITextView()

    override func loadView() {
        let view = UIView(frame: UIScreen.main.bounds)
        self.view = view
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        self.view.addSubview(box)
        box.textAlignment = .center
        box.snp.makeConstraints { (make) -> Void in
            make.width.equalTo(self.view.bounds.width)
            make.height.equalTo(self.view.bounds.height)
            //make.top.equalTo(self.view.safeAreaInsets.top)
        }

        let x = Proxy().proxyHello()
        box.text = x
    }
}
