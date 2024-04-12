package com.app.masterboatapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.masterboatapp.R
import com.app.masterboatapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        activityMainBinding.activity= this@MainActivity
    }

   fun onClickButton(isPostMethod:Boolean){
       try {
           if(isPostMethod){
               startActivity(Intent(this@MainActivity,ProductListActivity::class.java))
           }else{
               startActivity(Intent(this@MainActivity,PostListActivity::class.java))
           }
       }catch (e:Exception){
           e.stackTrace
       }
   }


}