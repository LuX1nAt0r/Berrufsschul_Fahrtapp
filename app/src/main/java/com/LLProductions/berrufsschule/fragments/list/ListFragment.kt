package com.LLProductions.berrufsschule.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SortedList
import com.LLProductions.berrufsschule.R
import com.LLProductions.berrufsschule.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())

        //UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {user ->
            adapter.setData(user)
        })


        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)


        return view

    }

    fun sortbyabc(){


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }
        if(item.itemId == R.id.menu_sort_abc){
            Toast.makeText(requireContext(),"Test",Toast.LENGTH_SHORT).show()
            //val adapter = ListAdapter()
            mUserViewModel.readAllDataABC.observe(viewLifecycleOwner, Observer {user ->
                val adapter = ListAdapter()
                val recyclerView = recyclerview
                recyclerView.adapter = adapter
                recyclerView.layoutManager= LinearLayoutManager(requireContext())
                adapter.setData(user)
            })



        }
        if(item.itemId == R.id.menu_sort_age){
            mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {user ->
                val adapter = ListAdapter()
                val recyclerView = recyclerview
                recyclerView.adapter = adapter
                recyclerView.layoutManager= LinearLayoutManager(requireContext())
                adapter.setData(user)
            })


        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_,_ ->
            mUserViewModel.deleteAllUser()
            Toast.makeText(requireContext(),"Successfully removed everything", Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("No") {_,_ -> }

        builder.setTitle("Delete Everything?")
        builder.setMessage("Are you sure you want to delete all Users?")
        builder.create().show()
    }
}