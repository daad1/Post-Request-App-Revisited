package com.example.postrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RecyclerAdapter

    private lateinit var etName: EditText
    private lateinit var etLocation: EditText
    private lateinit var btAdd: Button

    private lateinit var users: Users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        users = Users()

        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RecyclerAdapter(users)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.getAll()?.enqueue(object: Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                users = response.body()!!
                rvAdapter.update(users)
            }
            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.d("MAIN", "Unable to get data")
            }
        })

        etName = findViewById(R.id.edName)
        etLocation = findViewById(R.id.edLocation)
        btAdd = findViewById(R.id.btnAdd)
        btAdd.setOnClickListener {
            apiInterface!!.addUser(
                UserItem(
                    etLocation.text.toString(),  // location then name would make more sense here, this is an API issue
                    etName.text.toString(),
                    0
                )
            ).enqueue(object : Callback<UserItem> {
                override fun onResponse(call: Call<UserItem>, response: Response<UserItem>) {
                    Toast.makeText(applicationContext, "User added!", Toast.LENGTH_LONG).show()
                    recreate()
                }

                override fun onFailure(call: Call<UserItem>, t: Throwable) {
                    Log.d("MAIN", "Something went wrong!")
                }

            })
        }
    }
}

