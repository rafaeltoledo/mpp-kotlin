import SnapKit
import UIKit
import common
import RxSwift

class ViewController: UITableViewController {
    
    let disposeBag = DisposeBag()
    var viewModel = ListingViewModel()
    
    var listing = [Topic]()
    
    lazy var tableview: UITableView = {
        let tv = UITableView()
        tv.backgroundColor = UIColor.white
        tv.translatesAutoresizingMaskIntoConstraints = false
        return tv
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        setupTableView()
        title = "Kotlin Native Example"
        
        viewModel.listing
            .subscribe(onNext: { listing in
                self.listing = listing.children
                self.tableView.reloadData()
            }).disposed(by: disposeBag)
        
        viewModel.fetch()
    }
    
    func setupTableView() {
        tableview.delegate = self
        tableview.dataSource = self
        
        tableview.register(UITableViewCell.self, forCellReuseIdentifier: "cellId")
        
        view.addSubview(tableview)
        
        NSLayoutConstraint.activate([
            tableview.topAnchor.constraint(equalTo: self.view.topAnchor),
            tableview.bottomAnchor.constraint(equalTo: self.view.bottomAnchor),
            tableview.rightAnchor.constraint(equalTo: self.view.rightAnchor),
            tableview.leftAnchor.constraint(equalTo: self.view.leftAnchor)
        ])
        
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        self.listing.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableview.dequeueReusableCell(withIdentifier: "cellId", for: indexPath)
        cell.textLabel?.text = self.listing[indexPath.row].title
        cell.textLabel?.numberOfLines = 0
        return cell
    }
}
