package com.example.flickrreplica.ui

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrreplica.R
import com.example.flickrreplica.adapter.PhotosAdapter
import com.example.flickrreplica.model.ContainerPhoto
import com.example.flickrreplica.util.PhotosViewModel

class MainActivity : AppCompatActivity() {
    private val photosViewModel: PhotosViewModel by viewModels()
    private val photosAdapter = PhotosAdapter()
    private var page: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nestedScroll: NestedScrollView = findViewById(R.id.scrollView)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val photosViewModel: PhotosViewModel by viewModels()
        val photosAdapter = PhotosAdapter()



        recyclerView.adapter = photosAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        photosViewModel.loadPhotos(page).observe(this,
            Observer<List<ContainerPhoto>> { list ->
                with(photosAdapter) {
                    photos.clear()
                    photos.addAll(list)
                    notifyDataSetChanged()
                }
            })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dx == recyclerView.getChildAt(0).measuredHeight - recyclerView.measuredHeight) {

                    val sdksd: Int = 1

                    photosViewModel.loadPhotos(page).observe(this@MainActivity,
                        Observer<List<ContainerPhoto>> { list ->
                            with(photosAdapter) {
                                photos.clear()
                                photos.addAll(list)
                                notifyDataSetChanged()
                            }
                        })
                    page++
                }

                /*override fun onScrollChange(
                    v: NestedScrollView?,
                    scrollX: Int,
                    scrollY: Int,
                    oldScrollX: Int,
                    oldScrollY: Int
                ) {
                    if(scrollX == v!!.getChildAt(0).measuredHeight - v.measuredHeight) {

                        val sdksd: Int = 1

                        photosViewModel.loadPhotos(page).observe(this@MainActivity,
                            Observer<List<ContainerPhoto>> { list ->
                                with(photosAdapter) {
                                    photos.clear()
                                    photos.addAll(list)
                                    notifyDataSetChanged()
                                }
                            })
                        page++


                    }
                }*/
            }
        })


        /*//recyclerView.adapter =  PhotoRecycAdapter()
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        getData(
            "flickr.photos.getRecent",
            "application/json",
            20,
            "347c82e61bd5c5154ad9c1cdb14e3c26",
            1
        )*/
    }

    /* private fun getData(
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

          val logging = HttpLoggingInterceptor()
          val okHttp = OkHttpClient.Builder()
          logging.level = HttpLoggingInterceptor.Level.BODY
          okHttp.addInterceptor(logging)

          val request = ServiceBuilder.buildService(UrlInterface::class.java)
          val getUrls = request.getCredentials(method, api, perPage, format, nojsoncallback)


          getUrls.enqueue(object : Callback<List<BasePhotosModel>> {
              override fun onResponse(
                  call: Call<List<BasePhotosModel>>,
                  response: Response<List<BasePhotosModel>>
              ) {


                  if (response.isSuccessful && response.body() != null) {
                      Toast.makeText(this@MainActivity, "in response", Toast.LENGTH_SHORT).show()
                      progressBar.visibility = View.GONE

                      val list = ArrayList<UrlBuilderModel>()
                      val listb = (response.body() as ArrayList<BasePhotosModel>?)!!
                      for (i in listb) {

                          val farm: String =
                              listb.forEach(Consumer { it.photos.photo.forEach(Consumer { it.farm }) })
                                  .toString()
                          val serverId: String =
                              listb.forEach(Consumer { it.photos.photo.forEach(Consumer { it.server }) })
                                  .toString()
                          val secretId: String =
                              listb.forEach(Consumer { it.photos.photo.forEach(Consumer { it.secret }) })
                                  .toString()
                          val id: String =
                              listb.forEach(Consumer { it.photos.photo.forEach(Consumer { it.id }) })
                                  .toString()

                          var data = UrlBuilderModel(farm, serverId, secretId, id)

                          list.add(data)

                      }


                      //parseResult(jsonArray)
                  }
              }

              override fun onFailure(call: Call<List<BasePhotosModel>>, t: Throwable) {
                  Toast.makeText(this@MainActivity, "infail", Toast.LENGTH_SHORT).show()

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

      }*/

}