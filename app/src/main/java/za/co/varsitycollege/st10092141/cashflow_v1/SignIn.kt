package za.co.varsitycollege.st10092141.cashflow_v1
//ST10092141 - Kgothatso theko
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat


class SignIn : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInBtn: Button
    private lateinit var googleSignInBtn: ImageView
    private lateinit var googleSignInClient: GoogleSignInClient

    // Activity Result Launcher for Google Sign-In
    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle the Google Sign-In result
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let {
                    googleAuthForFirebase(it)
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign-in failed: ${e.statusCode}", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Google sign-in canceled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val fingerprintButton: ImageView = findViewById(R.id.fingerprint)
        fingerprintButton.setOnClickListener {
            checkBiometricAvailability()
        }

        // Initialize FirebaseAuth instance
        auth = FirebaseAuth.getInstance()

        // Initialize the EditText and Button views
        emailEditText = findViewById(R.id.email1)
        passwordEditText = findViewById(R.id.password3)
        signInBtn = findViewById(R.id.signInButton)
        googleSignInBtn = findViewById(R.id.google2)

        // Configure Google Sign-In options
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // Ensure this matches your Firebase project
            .requestEmail()
            .build()

        // Initialize GoogleSignInClient
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Set the sign-in button click listener for email/password
        signInBtn.setOnClickListener {
            signInUser()
        }

        // Set the sign-in button click listener for Google Sign-In
        googleSignInBtn.setOnClickListener {
            initiateGoogleSignIn()
        }
    }

    private fun initiateGoogleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    //google sign in method adapted from Developers
    //https://developer.android.com/identity/sign-in/credential-manager-siwg
    //Developers
    private fun googleAuthForFirebase(account: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                auth.signInWithCredential(credentials).await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignIn, "Google Sign-In Successful!", Toast.LENGTH_SHORT).show()
                    navigateToLanding()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignIn, "Google Sign-In Failed: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //google sign in method adapted from Developers
    //https://developer.android.com/identity/sign-in/credential-manager-siwg
    //Developers
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
                auth.signInWithEmailAndPassword(email, password).await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignIn, "Sign-In Successful!", Toast.LENGTH_SHORT).show()
                    navigateToLanding()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignIn, "Sign-In Failed: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //navigate to the landing page
    //Kgothatso Theko
    private fun navigateToLanding() {
        val intent = Intent(this, Landing::class.java)
        startActivity(intent)
        finish() // Close the sign-in activity
    }

    private fun checkBiometricAvailability() {
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> initiateBiometricPrompt()
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Toast.makeText(this, "No biometric hardware available", Toast.LENGTH_SHORT).show()
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Toast.makeText(this, "Biometric hardware unavailable", Toast.LENGTH_SHORT).show()
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Toast.makeText(this, "No biometric enrolled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initiateBiometricPrompt() {
        val executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(this@SignIn, "Authentication succeeded!", Toast.LENGTH_SHORT).show()
                navigateToLanding() // Navigate to the next activity
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(this@SignIn, "Authentication error: $errString", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(this@SignIn, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Authenticate using your fingerprint")
            .setNegativeButtonText("Cancel")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }


}
