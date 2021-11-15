package com.example.dummyapp.ui.modules.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dummyapp.databinding.ActivityMainBinding
import com.example.dummyapp.ui.modules.userlist.UserListActivity
import com.example.dummyapp.ui.utils.viewBindings.activityViewBinding

class MainActivity: AppCompatActivity() {
    
    private val binding by activityViewBinding(ActivityMainBinding::inflate)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setupUI()
    }
    
    private fun setupUI() {
        with(binding) {
            userListButton.setOnClickListener {
                Intent(root.context, UserListActivity::class.java)
                    .also { startActivity(it) }
            }
        }
    }
}