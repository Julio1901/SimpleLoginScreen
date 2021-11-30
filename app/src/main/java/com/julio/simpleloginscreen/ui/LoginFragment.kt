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
import com.julio.simpleloginscreen.dao.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonEnter = view.findViewById<Button>(R.id.btn_enter)
        val buttonCreateAcccount = view.findViewById<Button>(R.id.btn_create_account)

        val editTextUserName = view.findViewById<EditText>(R.id.editText_user_name_login)
        val editTextPassword = view.findViewById<EditText>(R.id.editText_password_login)
        val textViewLoginError = view.findViewById<TextView>(R.id.textView_login_error)

        val dataBaseInstance = DataBaseSimpleLoginForm.getDatabaseInstance(this.requireContext()).dao()
        val userRepositoryInstance = UserRepository(dataBaseInstance)

        buttonEnter.setOnClickListener {
            val name = editTextUserName.text.toString()
            val passWord = editTextPassword.text.toString()

            MainScope().launch (Dispatchers.IO) {
                try {
                    val user = userRepositoryInstance.getUserInDb(name)

                    if (user.password == passWord) {
                        val action = LoginFragmentDirections.actionLoginToHome()
                        findNavController().navigate(action)
                    }else{
                        textViewLoginError.setTextColor(resources.getColor(R.color.red))
                    }
                } catch (e: Exception) {
                    textViewLoginError.setTextColor(resources.getColor(R.color.red))
                }
            }
        }





        buttonCreateAcccount.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginToCreateAccountForm()
            findNavController().navigate(action)
        }


    }


}