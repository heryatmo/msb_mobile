package com.example.heryatmo.msb_mobile

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.heryatmo.msb_mobile.model.Login
import com.example.heryatmo.msb_mobile.remote.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var tEmail = findViewById(R.id.txtEmail) as EditText
        var tPass = findViewById(R.id.txtPassword) as EditText

        var btnLogin = findViewById(R.id.btnLogin) as Button
        var email = tEmail.text.toString();
        var password = tPass.text.toString();

        btnLogin.setOnClickListener(View.OnClickListener {
            doLogin(Login(email, password))

        });

    }
//    private fun validateLogin(email : String, password : String):Boolean{
//
//        if (email == null || email.trim().length == 0){
//            Toast.makeText(this,"Email is required", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (password == null || password.trim().length == 0){
//            Toast.makeText(this,"Password is required", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true
//    }

    fun doLogin(login : Login) {

        val mApiService = APIService.create()
        val call = mApiService.loginAPI(login)

        call.enqueue(object : Callback<Login>{
            override fun onResponse(call: Call<Login>?, response: Response<Login>?) {

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<Login>?, t: Throwable?) {
                Toast.makeText(this@LoginActivity,"Login Gagal",Toast.LENGTH_SHORT).show()
            }
        })

    }

}
