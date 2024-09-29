package za.co.varsitycollege.st10092141.cashflow_v1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignUp : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var signUp: Button
    private lateinit var email: EditText
    private lateinit var password1: EditText
    private lateinit var password2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Initialize views
        signUp = findViewById(R.id.signUpButton)
        email = findViewById(R.id.email)
        password1 = findViewById(R.id.password1)
        password2 = findViewById(R.id.password2)

        // Set signUp button click listener
        signUp.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val emailStr = email.text.toString().trim()
        val passwordStr1 = password1.text.toString().trim()
        val passwordStr2 = password2.text.toString().trim()

        // Validate input fields
        if (emailStr.isEmpty() || passwordStr1.isEmpty() || passwordStr2.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (passwordStr1 != passwordStr2) {
            Toast.makeText(this, "Passwords must match!", Toast.LENGTH_SHORT).show()
            return
        }

        // Perform Firebase registration in a coroutine to handle async task
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Create user with Firebase Auth
                auth.createUserWithEmailAndPassword(emailStr, passwordStr1).await()

                // Switch to Main Thread to update UI
                runOnUiThread {
                    Toast.makeText(this@SignUp, "Registration Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignUp, SignIn::class.java)
                    startActivity(intent)
                    finish() // Close the current activity
                }
            } catch (e: Exception) {
                // Handle the exception and show error message
                runOnUiThread {
                    Toast.makeText(this@SignUp, "Registration failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
