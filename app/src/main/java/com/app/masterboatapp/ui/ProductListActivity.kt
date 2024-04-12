package com.app.masterboatapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.masterboatapp.R
import com.app.masterboatapp.data.ApiResponseData
import com.app.masterboatapp.data.ApiResultHandler
import com.app.masterboatapp.databinding.ActivityProductListBinding
import com.app.masterboatapp.viewmodels.MainViewModel
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductListActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    lateinit var activityMainBinding: ActivityProductListBinding
    lateinit var productListAdapter: ProductListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this@ProductListActivity, R.layout.activity_product_list)
        init()
        getProducts()
        observeProductData()
    }

    private fun init() {
        try {
            productListAdapter = ProductListAdapter()
            activityMainBinding.list.apply { adapter = productListAdapter }
            activityMainBinding.swipeRefreshLayout.setOnRefreshListener { getProducts() }
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    private fun observeProductData() {
        try {
            mainViewModel.response.observe(this) { response ->
                val apiResultHandler = ApiResultHandler<ApiResponseData>(this@ProductListActivity,
                    onLoading = {
                        activityMainBinding.progress.visibility = View.VISIBLE
                    },
                    onSuccess = { data ->
                        activityMainBinding.progress.visibility = View.GONE
                        data?.Data?.marketList?.let { productListAdapter.setProducts(it) }
                        activityMainBinding.swipeRefreshLayout.isRefreshing = false
                    },
                    onFailure = {
                        activityMainBinding.progress.visibility = View.GONE
                    })
                apiResultHandler.handleApiResult(response)
            }
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    private fun getProducts() {
        try {
            /*create your json for post request*/
            var jsonObject = JsonObject().apply {

            }
            mainViewModel.getProductsList(jsonObject)
        } catch (e: Exception) {
            e.stackTrace
        }
    }

}