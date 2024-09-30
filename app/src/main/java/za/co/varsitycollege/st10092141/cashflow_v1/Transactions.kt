package za.co.varsitycollege.st10092141.cashflow_v1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Transactions : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val viewModel: ItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //call fetch items method when page is created
        fetchItems()


    }

    //using recycleView adapter adapted from you GeeksForGeeks
    //https://www.geeksforgeeks.org/android-recyclerview-in-kotlin/
    //GeeksForGeeks
    private fun fetchItems() {
        viewModel.getItems.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { items ->
                    recyclerView.adapter = ItemAdapter(items)
                }
            } else {
                Toast.makeText(requireContext(), "Failed to fetch items", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
