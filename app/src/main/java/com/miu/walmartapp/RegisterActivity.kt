package com.miu.walmartapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    var user = User("","","","")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun createUser(view:View){
        var fName:String=et1.text.toString().trim()
        var lName:String=et2.text.toString().trim()
        var email:String=et3.text.toString().trim()
        var pass:String=et4.text.toString().trim()

        if(fName.equals("") || lName.equals("") || email.equals("") || pass.equals("")){
            Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_LONG)
                .show()

        }

        else {
            var userData:User= User(fName,lName,email,pass)

            val userdataIntent = Intent()
            userdataIntent.putExtra("userData", userData)
            setResult(RESULT_OK, userdataIntent)

            Toast.makeText(this, "Account is successfully created", Toast.LENGTH_LONG).show()

            finish();
        }
    }




}