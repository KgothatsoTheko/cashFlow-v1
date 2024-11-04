package za.co.varsitycollege.st10092141.cashflow_v1

//data class that matches MongoDB Item schema
//Kgothato Theko
data class Item(
    val _id: String? = null,
    val name: String,
    val description: String,
    val amount: String
)
