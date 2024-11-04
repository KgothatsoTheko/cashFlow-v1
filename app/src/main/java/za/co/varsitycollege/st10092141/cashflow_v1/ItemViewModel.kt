package za.co.varsitycollege.st10092141.cashflow_v1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

// view model adapted from Medium
//https://medium.com/android-beginners/mvvm-with-retrofit-and-recyclerview-in-kotlin-example-f01a7bd41073#:~:text=MVVM%20With%20Retrofit%20and%20Recyclerview%20in%20Kotlin%20[Example]%20MVVM%20architecture
//Velmurugan Murugesan
//https://velmurugan-murugesan.medium.com/

class ItemViewModel : ViewModel() {
    private val repository = ItemRepository()

    val addItem = liveData(Dispatchers.IO) {
        // first method to add transaction
        val item = Item(name = "Sample Item", description = "This is a sample.", amount = "250")
        val response = repository.addItem(item)
        emit(response)
    }

    //get all items
    val getItems = liveData(Dispatchers.IO) {
        val response = repository.getItems()
        emit(response)
    }

    //add transactions
    suspend fun addItem(item: Item): Response<Item> {
        return repository.addItem(item)
    }


}