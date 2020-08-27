//
//  ListingViewModel.swift
//  Kotlin
//
//  Created by Rafael Toledo on 27/08/20.
//  Copyright © 2020 Rafael Toledo. All rights reserved.
//

import Foundation
import RxSwift
import RxRelay
import common

class ListingViewModel {
    
    private let _listing = PublishRelay<Listing>()
    
    func fetch() {
        RedditRepository().fetch(subreddit: "kotlin") { data, error in
            if let content = data {
                self._listing.accept(content)
            }
        }
    }
    
    var listing: Observable<Listing> {
        return _listing.asObservable()
    }
}
