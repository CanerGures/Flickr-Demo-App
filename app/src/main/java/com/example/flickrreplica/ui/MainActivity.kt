package com.example.flickrreplica.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrreplica.R
import com.example.flickrreplica.model.BasePhotosModel
import com.example.flickrreplica.util.ServiceBuilder
import com.example.flickrreplica.util.UrlInterface
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.function.Consumer
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    var page by Delegates.notNull<Int>()
    var item by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nestedScroll: NestedScrollView = findViewById(R.id.scrollView)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)


        //recyclerView.adapter =  PhotoRecycAdapter()
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        getData(
            "flickr.photos.getRecent",
            "application/json",
            20,
            "347c82e61bd5c5154ad9c1cdb14e3c26",
            1
        )
    }

    private fun getData(
        method: String,
        format: String,
        perPage: Int,
        api: String,
        nojsoncallback: Int
    ) {
        //val retrofit = Retrofit.Builder()
        //.baseUrl("https://www.flickr.com/")
        // .addConverterFactory(GsonConverterFactory.create()).build()

        //urlInterface = retrofit.create(UrlInterface::class.java)
        //val getUrls = urlInterface.getCredentials()

        val request = ServiceBuilder.buildService(UrlInterface::class.java)
        val getUrls = request.getCredentials(method, api, perPage, format, nojsoncallback)


        getUrls.enqueue(object : Callback<BasePhotosModel> {
            override fun onResponse(
                call: Call<BasePhotosModel>,
                response: Response<BasePhotosModel>
            ) {


                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(this@MainActivity, "in response", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE

                    val list = (response.body() as ArrayList<BasePhotosModel>?)!!
                    val farmId: String =
                        list.forEach(Consumer { it.photos.photo.forEach(Consumer { it.farm }) })
                            .toString()
                    val serverId: String =
                        list.forEach(Consumer { it.photos.photo.forEach(Consumer { it.server }) })
                            .toString()
                    val secretId: String =
                        list.forEach(Consumer { it.photos.photo.forEach(Consumer { it.secret }) })
                            .toString()
                    val id: String =
                        list.forEach(Consumer { it.photos.photo.forEach(Consumer { it.id }) })
                            .toString()


                    //parseResult(jsonArray)
                }
            }

            override fun onFailure(call: Call<BasePhotosModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "in fail", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun parseResult(jsonArray: JSONArray) {

        for (i in arrayOf(jsonArray.length())) {
            try {

                val jsonobject: JSONObject = jsonArray.getJSONObject(i)


            } catch (e: Exception) {

            }

        }

    }

}