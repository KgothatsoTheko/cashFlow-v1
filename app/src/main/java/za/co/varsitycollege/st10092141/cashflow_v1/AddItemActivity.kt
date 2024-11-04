package za.co.varsitycollege.st10092141.cashflow_v1
//ST10092141 - Kgothatso Theko
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddItemActivity : ComponentActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var editTextAmount: EditText
    private lateinit var buttonSubmit: Button

    private val viewModel: ItemViewModel by viewModels() // Using ViewModel delegation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        // Initialize views
        editTextName = findViewById(R.id.editTextName)
        editTextDescription = findViewById(R.id.editTextDescription)
        editTextAmount = findViewById(R.id.editTextAmount)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        // Set click listener for the submit button
        buttonSubmit.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val description = editTextDescription.text.toString().trim()
            val amount = editTextAmount.text.toString().trim()

            //validation
            if (name.isEmpty()) {
                editTextName.error = "Name is required"
                editTextName.requestFocus()
                return@setOnClickListener
            }

            //validation
            if (description.isEmpty()) {
                editTextDescription.error = "Description is required"
                editTextDescription.requestFocus()
                return@setOnClickListener
            }

            //validation
            if (amount.isEmpty()) {
                editTextAmount.error = "Amount is required"
                editTextAmount.requestFocus()
                return@setOnClickListener
            }

            //save items in variable and call add items method
            val newItem = Item(name = name, description = description, amount = amount)
            addItem(newItem)
        }
    }

    //method to save data to database using coroutine adapted from you GeeksForGeeks
    //https://www.geeksforgeeks.org/retrofit-with-kotlin-coroutine-in-android/
    //GeeksForGeeks
    private fun addItem(item: Item) {
        // Show a loading indicator here?

        // Launch a coroutine to perform the network operation
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = viewModel.addItem(item)

                runOnUiThread {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AddItemActivity, "Item added successfully", Toast.LENGTH_SHORT).show()
                        // Clear the input fields
                        editTextName.text.clear()
                        editTextDescription.text.clear()
                        editTextAmount.text.clear()
                        // finish activity to return to the previous screen
                        setResult(RESULT_OK)
                        finish()
                    } else {
                        Toast.makeText(this@AddItemActivity, "Failed to add item: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@AddItemActivity, "An error occurred: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}