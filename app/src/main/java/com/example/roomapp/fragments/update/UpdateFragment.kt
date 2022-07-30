package com.example.roomapp.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
import com.example.roomapp.model.User
import com.example.roomapp.viewmodel.UserViewModel


class UpdateFragment : Fragment() {
    private lateinit var edtUpdateFirstName: EditText
    private lateinit var edtUpdateLastName: EditText
    private lateinit var edtUpdateAge: EditText
    private lateinit var btnUpdate: Button

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        edtUpdateFirstName = view.findViewById(R.id.edtUpdateFirstName)
        edtUpdateLastName = view.findViewById(R.id.edtUpdateLastName)
        edtUpdateAge = view.findViewById(R.id.edtUpdateAge)
        btnUpdate = view.findViewById(R.id.btnUpdate)

        edtUpdateFirstName.setText(args.currentUser.firstName)
        edtUpdateLastName.setText(args.currentUser.lastName)
        edtUpdateAge.setText(args.currentUser.age.toString())

        btnUpdate.setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun updateItem(){
        val firstName = edtUpdateFirstName.text.toString()
        val lastName = edtUpdateLastName.text.toString()
        val age = Integer.parseInt(edtUpdateAge.text.toString())

        if(inputCheck(firstName, lastName, edtUpdateAge.text)){
            val updateUser = User(args.currentUser.id, firstName, lastName, age)
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Succesfully updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()

        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}