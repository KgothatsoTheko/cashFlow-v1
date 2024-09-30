package za.co.varsitycollege.st10092141.cashflow_v1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import za.co.varsitycollege.st10092141.cashflow_v1.databinding.ActivityLandingBinding

class Landing : AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        //method to change navigation depending on which item is selected (Add on from Dima Ps method⬇️)
        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId) {
                R.id.home -> replaceFragment(Home())
                R.id.transactions -> replaceFragment(Transactions())
                R.id.reports -> replaceFragment(Reports())
                R.id.settings -> replaceFragment(Settings())
                else -> {}
            }
            true
        }

    }

    //method to replace the default frame layout and change fragment adapted from stack overflow
    //https://stackoverflow.com/questions/52318195/how-to-change-fragment-kotlin#:~:text=private%20fun%20replaceFragment(fragment:%20Fragment)%20{%20val%20transaction%20=%20supportFragmentManager.beginTransaction()%20transaction.replace(R.id.frame,
    //Dima Ps
    //https://stackoverflow.com/users/9859169/dimas-ps
    public fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}