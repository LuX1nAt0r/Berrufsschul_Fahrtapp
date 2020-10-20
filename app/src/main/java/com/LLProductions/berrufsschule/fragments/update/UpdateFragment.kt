package com.LLProductions.berrufsschule.fragments.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.LLProductions.berrufsschule.R
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        view.et_updateFirstName.setText(args.currentUser.firstName)
        view.et_updateLastName.setText(args.currentUser.lastName)
        view.et_updateAge.setText(args.currentUser.age.toString())

        return view
    }


}