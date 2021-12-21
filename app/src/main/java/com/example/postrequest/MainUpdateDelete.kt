package com.example.postrequest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainUpdateDelete : AppCompatActivity() {

    lateinit var userId: EditText
    lateinit var userName: EditText
    lateinit var userLocation: EditText
    lateinit var btnUpdate: Button
    lateinit var btnDelete: Button

    var idOfUser = 0
    var idOfUser1 = 0
    var nameOfUser = ""
    var locationForUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)

        userId = findViewById(R.id.etUserId)
        userName = findViewById(R.id.etUserName)
        userLocation = findViewById(R.id.etUserLocation)
        btnUpdate = findViewById(R.id.btn_Update)
        btnDelete = findViewById(R.id.btn_Delete)
        btnUpdate.setOnClickListener {

            idOfUser = Integer.parseInt(userId.text.toString())
            nameOfUser = userName.text.toString()
            locationForUser = userLocation.text.toString()
            updateUser()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnDelete.setOnClickListener {
            idOfUser = Integer.parseInt(userId.text.toString())
            deleteUser()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


    fun updateUser() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.updateUser(idOfUser, UserItem(nameOfUser, locationForUser, idOfUser))
            ?.enqueue(object :
                Callback<UserItem> {

                override fun onResponse(call: Call<UserItem>, response: Response<UserItem>) {
                    Toast.makeText(this@MainUpdateDelete, "update", Toast.LENGTH_LONG).show()


                }

                override fun onFailure(call: Call<UserItem>, t: Throwable) {
                    Toast.makeText(this@MainUpdateDelete, "ERROR", Toast.LENGTH_LONG).show()
                }
            })

    }

    fun deleteUser() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.deleteUser(idOfUser)?.enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@MainUpdateDelete, "deleted the user", Toast.LENGTH_LONG).show()


            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MainUpdateDelete, "ERROR", Toast.LENGTH_LONG).show()
            }
        })

    }
}
