package com.gyf.immersionbar.sample.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ktx.immersionBar
import com.gyf.immersionbar.sample.AppManager
import com.gyf.immersionbar.sample.databinding.ActivityParamsBinding

/**
 * @author geyifeng
 * @date 2019/4/10 5:20 PM
 */
open class BaseKotlinActivity(@LayoutRes val layoutResID: Int) : AppCompatActivity() {

    lateinit var binding: ActivityParamsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.getInstance().addActivity(this)
//        setContentView(layoutResID)
        binding = ActivityParamsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initImmersionBar()
        //初始化数据
        initData()
        //view与数据绑定
        initView()
        //设置监听
        setListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.getInstance().removeActivity(this)
    }

    open fun initImmersionBar() {
        immersionBar()
    }

    open fun initData() {

    }

    open fun initView() {

    }

    open fun setListener() {

    }

}