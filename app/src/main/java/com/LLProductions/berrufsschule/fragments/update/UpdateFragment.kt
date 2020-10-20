package com.LLProductions.berrufsschule.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.LLProductions.berrufsschule.R
import com.LLProductions.berrufsschule.model.User
import com.LLProductions.berrufsschule.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        val mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)

        view.et_updateFirstName.setText(args.currentUser.firstName)
        view.et_updateLastName.setText(args.currentUser.lastName)
        view.et_updateAge.setText(args.currentUser.age.toString())


        view.btnUpdate.setOnClickListener {

        }

        return view
    }

    private fun updateItem(){
        val firstName = et_updateFirstName.text.toString()
        val lastName = et_updateLastName.text.toString()
        val age = Integer.parseInt(et_updateAge.text.toString())

        if(inputCheck(firstName, lastName, et_updateAge.text)) {
            //Create User Object
            val updateUser = User(args.currentUser.id, firstName, lastName, age)
            //Update Current User
            mUserViewModel.updateUser(updateUser)
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


}