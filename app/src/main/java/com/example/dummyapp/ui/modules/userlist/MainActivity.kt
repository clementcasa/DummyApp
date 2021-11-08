package com.example.dummyapp.ui.modules.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dummyapp.R
import com.example.dummyapp.databinding.ActivityMainBinding
import com.example.dummyapp.ui.utils.viewBindings.activityViewBinding

class MainActivity: AppCompatActivity() {
    
    private val binding by activityViewBinding(ActivityMainBinding::inflate)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        
    }
}