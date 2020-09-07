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

    fun signIn(view:View){
        var name=""
        var exist=false
        var username:String =email.text.toString()
        var pass:String=password.text.toString()
        for (i in users){
            if(i.email.equals(username) && i.password.equals(pass))
                exist=true
            name=i.firstName.toString()

        }
        if (exist) {
            val shppingIntent = Intent(this, Shopping_activity::class.java)
            shppingIntent.putExtra("name",name)
            startActivity(shppingIntent);
        }
        else
            Toast.makeText(this, "invalid UserName or Password", Toast.LENGTH_LONG).show()
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

    fun passwordReminder(view: View){
        var passwordforgot:String=""
        var fName:String=""
        if (email.text.toString().trim().equals("") ||email.text.toString()==null ){
            Toast.makeText(this, "Please Enter a valid email ", Toast.LENGTH_SHORT).show()

        }
        else{
            var username= email.text.toString()
            for(i in users) {
                if (i.email.equals(username)) {
                    passwordforgot = i.password
                    fName=i.firstName
                }
            }
            if(passwordforgot.trim().equals("")){

                Toast.makeText(this, "Account does not exit please create one ", Toast.LENGTH_LONG).show()
            }
            else {
                send(username, passwordforgot, fName)
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