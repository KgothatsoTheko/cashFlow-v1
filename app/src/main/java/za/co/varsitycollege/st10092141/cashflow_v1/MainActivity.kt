package za.co.varsitycollege.st10092141.cashflow_v1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get the Sign Up button
        val signUpBtn: Button = findViewById(R.id.signUp)
        // Set up click listener
        signUpBtn.setOnClickListener{
            // Start the SignUp activity
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        // Get the Sign In button
        val signInBtn: Button = findViewById(R.id.signIn)
        // Set up click listener
        signInBtn.setOnClickListener{
            // Start the SignIn activity
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

    }
}