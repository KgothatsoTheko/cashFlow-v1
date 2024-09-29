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

class SignIn : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Initialize FirebaseAuth instance
        auth = FirebaseAuth.getInstance()

        // Initialize the EditText and Button views
        emailEditText = findViewById(R.id.email1)
        passwordEditText = findViewById(R.id.password3)
        signInBtn = findViewById(R.id.signInButton)

        // Set the sign-in button click listener
        signInBtn.setOnClickListener {
            signInUser()
        }
    }

    private fun signInUser() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Validate user input
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Perform sign-in using FirebaseAuth
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Sign in the user asynchronously
                auth.signInWithEmailAndPassword(email, password).await()

                // On successful sign-in, switch to the Landing activity
                runOnUiThread {
                    Toast.makeText(this@SignIn, "Sign-In Successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignIn, Landing::class.java)
                    startActivity(intent)
                    finish()  // Close the sign-in activity
                }
            } catch (e: Exception) {
                // Handle any exceptions that occur during sign-in
                runOnUiThread {
                    Toast.makeText(this@SignIn, "Sign-In Failed: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
