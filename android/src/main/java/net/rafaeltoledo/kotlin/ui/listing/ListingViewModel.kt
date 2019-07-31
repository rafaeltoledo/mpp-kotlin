package net.rafaeltoledo.kotlin.ui.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.rafaeltoledo.kotlin.data.domain.Listing
import net.rafaeltoledo.kotlin.data.repository.RedditRepository

class ListingViewModel : ViewModel() {

    val listing: LiveData<Listing>
        get() = _listing

    private val _listing = MutableLiveData<Listing>()

    fun fetch() {
        viewModelScope.launch {
            val listing = RedditRepository.fetch()
            _listing.postValue(listing)
        }
    }
}