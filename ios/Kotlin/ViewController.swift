import SnapKit
import UIKit
import common

class ViewController: UITableViewController {
    
    var listing = [Topic]()
    
    let tableview: UITableView = {
        let tv = UITableView()
        tv.backgroundColor = UIColor.white
        tv.translatesAutoresizingMaskIntoConstraints = false
        return tv
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        setupTableView()
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
        
        RedditRepository().fetch(subreddit: "kotlin", completion: {
            self.listing.append(contentsOf: $0.children)
            DispatchQueue.main.async {
               self.tableView.reloadData()
            }
        })
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.listing.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableview.dequeueReusableCell(withIdentifier: "cellId", for: indexPath)
        cell.textLabel?.text = self.listing[indexPath.row].title
        return cell
    }
}
