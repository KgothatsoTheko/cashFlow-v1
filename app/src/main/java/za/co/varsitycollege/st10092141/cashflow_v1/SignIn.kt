package za.co.varsitycollege.st10092141.cashflow_v1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signInPage)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get the references for EditText fields
        val emailEditText: EditText = findViewById(R.id.email1)
        val passwordEditText: EditText = findViewById(R.id.password3)

        // Get the references for the button
        val signInBtn: Button = findViewById(R.id.signInButton)

        signInBtn.setOnClickListener{
            val email = emailEditText.text.toString()
            val password3 = passwordEditText.text.toString()

            if (email.isEmpty() || password3.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Perform sign-in logic here
                Toast.makeText(this, "Sign-In Successful!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, Landing::class.java)
                startActivity(intent)
            }
        }
    }
}