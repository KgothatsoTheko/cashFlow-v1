package za.co.varsitycollege.st10092141.cashflow_v1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
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

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "task_notifications",
                "Task Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }


        //method to change navigation depending on which item is selected (Add on from Dima Ps method⬇️)
        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId) {
                R.id.home -> replaceFragment(Home())
                R.id.transactions -> replaceFragment(Transactions())
                R.id.addTransaction -> openAddItem()
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

    //method to open the new transaction activity
    //Kgothatso Theko
    private fun openAddItem() {
        val intent = Intent(this, AddItemActivity::class.java)
        startActivityForResult(intent, ADD_ITEM_REQUEST_CODE)
    }

    // Handle the result in `onActivityResult`
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            // Return to the previous fragment and show a notification
            showCompletionNotification()
        }
    }

    private fun showCompletionNotification() {
        val notificationBuilder = NotificationCompat.Builder(this, "task_notifications")
            .setSmallIcon(R.drawable.icon)
            .setContentTitle("Item Added")
            .setContentText("You successfully added a new item.")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notificationBuilder.build())
    }

    companion object {
        const val ADD_ITEM_REQUEST_CODE = 1001
    }

}