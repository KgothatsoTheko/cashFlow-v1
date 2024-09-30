package za.co.varsitycollege.st10092141.cashflow_v1
//ST10092141 - Kgothatso Theko
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Get reference to the LinearLayouts
        val transactionPage = view.findViewById<LinearLayout>(R.id.transactionPage)
//        val budgetPage = view.findViewById<LinearLayout>(R.id.budgetPage)
        val settingsPage = view.findViewById<LinearLayout>(R.id.settingsPage)
        val reportsPage = view.findViewById<LinearLayout>(R.id.reportsPage)

        // Setting click listeners for each LinearLayout to call replaceFragment from landing activity
        // Kgothatso Theko
        transactionPage.setOnClickListener {
            (activity as? Landing)?.replaceFragment(Transactions())
        }
//        budgetPage.setOnClickListener {
//            (activity as? Landing)?.replaceFragment(BudgetFragment())
//        }
        settingsPage.setOnClickListener {
            (activity as? Landing)?.replaceFragment(Settings())
        }
        reportsPage.setOnClickListener {
            (activity as? Landing)?.replaceFragment(Reports())
        }
        return view
    }
    }

