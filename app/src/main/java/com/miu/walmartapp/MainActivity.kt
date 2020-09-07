package com.miu.walmartapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

   var users = arrayListOf<User>(User("Thierry", "Manzi", "manzithierry1@gmail.com","thierry123"),
                        User("Lise", "Laura", "lise@gmail.com","lise123"),
                        User("Jean", "Jacques", "jean@gmail.com","jean123")

   )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun signIn(view: View) {
        var name:String=""
        var exist = false
        var username = email.text.toString()
        var userpassword = password.text.toString()
         for(i in users){
             if(i.email.equals(username) && i.password.equals(password)){
                 exist=true
             }
             name=i.email
         }
        if(exist){
            val intent = Intent(this, Shopping_activity::class.java)
            intent.putExtra("name", "Welcome $name")
            startActivity(intent)
        }else{
            Toast.makeText(this,"invalid email and password", Toast.LENGTH_LONG).show()
        }
    }

    fun create(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivityForResult(intent,1)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if(requestCode==1 && resultCode == Activity.RESULT_OK){
            var createdUser = data!!.getSerializableExtra("userData") as User
            users.add(createdUser)
        }
    }

    fun passwordReminder(view: View) {
        var passwordforgot:String=""
        var fName:String=""
        if(email.text.toString().equals("")||email.text.toString()==null){
            Toast.makeText(this,"Please enter a valid email address", Toast.LENGTH_SHORT).show()
        }else{
            var userEmail = email.text.toString()
            for(i in users){
                if(i.email.equals(userEmail)){
                    passwordforgot = i.password
                    fName= i.firstName
                }
                if(passwordforgot.trim().equals("")){

                    Toast.makeText(this, "your account does not exit please create one ", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun send(userEmail:String, password:String, fName:String){
        var message:String = "Hi $fName! your password is :$password "

        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.data= Uri.parse("mailto")
        sendIntent.type ="text/plain"
        sendIntent.putExtra(Intent.EXTRA_EMAIL, arrayListOf(userEmail))
        sendIntent.putExtra(Intent.EXTRA_SUBJECT,"FORGOT PASSWORD")
        sendIntent.putExtra(Intent.EXTRA_TEXT,message)
        //startActivity(mailIntent)

        try {
            startActivity(Intent.createChooser(sendIntent,"Select client"))
        }
        catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
}