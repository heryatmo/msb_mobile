package com.example.heryatmo.msb_mobile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.heryatmo.msb_mobile.adapter.CustomAdapter
import com.example.heryatmo.msb_mobile.model.Post
import com.example.heryatmo.msb_mobile.remote.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(){

    private var mCompositeDisposable: CompositeDisposable? = null
    private var mPostArrayList: ArrayList<Post>? = null
    private val TAG = MainActivity::class.java.simpleName
    private val BASE_URL = "https://bluebox2.com/msb/"
    private var mAdapter: CustomAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager :RecyclerView.LayoutManager = LinearLayoutManager(this)
        rv_post_list.layoutManager = layoutManager
        rv_post_list.adapter = mAdapter
        rv_post_list.setHasFixedSize(true)
        mCompositeDisposable = CompositeDisposable()
        getData()

    }


    private fun getData(){

        val requestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(APIService::class.java)

        mCompositeDisposable?.add(requestInterface.tampilPost()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError))
    }

    private fun handleResponse(dataList: List<Post>) {
        mPostArrayList = ArrayList(dataList)
        mAdapter = CustomAdapter(mPostArrayList!!)

        rv_post_list.adapter = mAdapter

    }

    private fun handleError(error: Throwable) {

        Log.d(TAG, error.localizedMessage)

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }
}
