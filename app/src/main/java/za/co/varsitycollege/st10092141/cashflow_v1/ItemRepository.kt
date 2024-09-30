package za.co.varsitycollege.st10092141.cashflow_v1

import retrofit2.Response

class ItemRepository {
    private val api = RetrofitInstance.api

    //Add item repository
    //Kgothatso Theko
    suspend fun addItem(item: Item): Response<Item> {
        return api.addItem(item)
    }

    //Get items repository
    //Kgothatso Theko
    suspend fun getItems() = api.getItems()

    suspend fun getItemById(id: String) = api.getItemById(id)

    suspend fun updateItem(name: String, item: Item) = api.updateItem(name, item)

    suspend fun deleteItem(name: String) = api.deleteItem(name)
}