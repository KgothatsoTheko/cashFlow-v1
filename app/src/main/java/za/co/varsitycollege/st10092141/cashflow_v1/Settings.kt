package za.co.varsitycollege.st10092141.cashflow_v1
//ST10092141 - Kgothaso Theko
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import java.util.Locale

class Settings : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Initialize FirebaseAuth and GoogleSignInClient
        auth = FirebaseAuth.getInstance()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), GoogleSignInOptions.DEFAULT_SIGN_IN)

        val signOutButton: Button = view.findViewById(R.id.signOutButton)
        signOutButton.setOnClickListener {
            signOutUser()
        }

        //method to use switch button to push notification adapted from GeeksForGeeks
        //https://www.geeksforgeeks.org/switch-in-kotlin/
        //GeeksForGeeks
        val sw1 = view.findViewById<SwitchCompat>(R.id.switch1)
        sw1?.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Notification: ON" else "Notification: OFF"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }


        //method and steps how to use spanner adapted from stackoverflow
        //https://stackoverflow.com/a/11731630
        //balaji
        //https://stackoverflow.com/users/1564344/balaji
        val spinnerLanguages: Spinner = view.findViewById(R.id.spinner_languages)
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(), // Get the correct context for a fragment
            R.array.languages, // The array you have defined in strings.xml
            android.R.layout.simple_spinner_item // The default layout for the spinner item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinnerLanguages.adapter = adapter
        // Add an item selected listener (optional, to show a Toast message when an item is selected)
        spinnerLanguages.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedLanguage = parent.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(), "Selected: $selectedLanguage", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        val spinnerLanguages1: Spinner = view.findViewById(R.id.spinner_languages1)
        val languagesAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.app_languages,
            android.R.layout.simple_spinner_item
        )
        languagesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLanguages1.adapter = languagesAdapter

        spinnerLanguages1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedLanguage = when (position) {
                    0 -> "en" // English
                    1 -> "af" // Afrikaans
                    2 -> "zu" // Zulu
                    else -> "en"
                }
                setLocale(selectedLanguage)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        return view // Return the inflated view
    }

    private fun signOutUser() {
        // Firebase sign-out
        auth.signOut()

        // Google sign-out
        googleSignInClient.signOut().addOnCompleteListener(requireActivity()) {
            Toast.makeText(requireContext(), "Signed out successfully!", Toast.LENGTH_SHORT).show()
            navigateToSignIn()
        }
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        // Refresh the fragment to apply language change
        parentFragmentManager.beginTransaction()
            .detach(this)
            .attach(this)
            .commit()
    }

    private fun navigateToSignIn() {
        val intent = Intent(requireContext(), SignIn::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        requireActivity().finish()
    }
}
