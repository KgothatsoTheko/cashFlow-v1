package za.co.varsitycollege.st10092141.cashflow_v1
//ST10092141 - Kgothaso Theko
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat

class Settings : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

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

        return view // Return the inflated view
    }
}
