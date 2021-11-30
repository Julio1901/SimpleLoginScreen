package com.julio.simpleloginscreen.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.julio.simpleloginscreen.R
import com.julio.simpleloginscreen.dao.DataBaseSimpleLoginForm
import com.julio.simpleloginscreen.dao.UserEntity
import com.julio.simpleloginscreen.dao.UserRepository
import kotlinx.android.synthetic.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class FormCreateAnAccountFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_create_an_account, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: replace it with viewModel class

        val dataBaseInstance = DataBaseSimpleLoginForm.getDatabaseInstance(this.requireContext()).dao()
        val userRepositoryInstance = UserRepository(dataBaseInstance)

        val button : Button = view.findViewById(R.id.btn_form_create)
        val editTextName : EditText = view.findViewById(R.id.editText_form_user_name)
        val editTextEmail : EditText = view.findViewById(R.id.editText_form_email)
        val editTextPassword : EditText = view.findViewById(R.id.editText_form_password)
        val textViewError : TextView = view.findViewById(R.id.textView_registration_error)

        button.setOnClickListener {
            val name = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            val newUser = UserEntity(name, email, password)

            MainScope().launch (Dispatchers.IO){
                val successfulRegistration = userRepositoryInstance.registerUser(newUser)

                if (successfulRegistration == (-1).toLong()){
                    textViewError.setText("It is not possible to create the login with this data. Try again.")
                    //textViewError.setTextColor(resources.getColor(R.color.red))
                }else{ val action = FormCreateAnAccountFragmentDirections.actionFormToHome()
                    findNavController().navigate(action)
                }
            }
        }
    }

}