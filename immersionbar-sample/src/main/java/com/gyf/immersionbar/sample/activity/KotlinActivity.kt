package com.gyf.immersionbar.sample.activity

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.gyf.immersionbar.ktx.*
import com.gyf.immersionbar.sample.R

/**
 * @author geyifeng
 * @date 2019/3/27 7:20 PM
 */
class KotlinActivity : BaseKotlinActivity(R.layout.activity_params) {

    private var mIsHideStatusBar = false

    override fun initImmersionBar() {
//        super.initImmersionBar()
        immersionBar {
            titleBar(binding.mToolbar)
            navigationBarColor(R.color.btn13)
            setOnNavigationBarListener {
                initView()
                val text = "导航栏${
                    if (it) {
                        "显示了"
                    } else {
                        "隐藏了"
                    }
                }"
                Toast.makeText(this@KotlinActivity, text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun initData() {
        super.initData()
        binding.mToolbar.title = intent.getCharSequenceExtra("title")
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        binding.mTvStatus.text = "${binding.mTvStatus.title}$statusBarHeight".content()
        binding.mTvHasNav.text = "${binding.mTvHasNav.title}$hasNavigationBar".content()
        binding.mTvNav.text = "${binding.mTvNav.title}$navigationBarHeight".content()
        binding.mTvNavWidth.text = "${binding.mTvNavWidth.title}$navigationBarWidth".content()
        binding.mTvAction.text = "${binding.mTvAction.title}$actionBarHeight".content()
        binding.mTvHasNotch.post {
            binding.mTvHasNotch.text = "${binding.mTvHasNotch.title}$hasNotchScreen".content()
        }
        binding.mTvNotchHeight.post {
            binding.mTvNotchHeight.text = "${binding.mTvNotchHeight.title}$notchHeight".content()
        }
        binding.mTvFits.text =
            "${binding.mTvFits.title}${findViewById<View>(android.R.id.content).checkFitsSystemWindows}".content()
        binding.mTvStatusDark.text =
            "${binding.mTvStatusDark.title}$isSupportStatusBarDarkFont".content()
        binding.mTvNavigationDark.text =
            "${binding.mTvNavigationDark.title}$isSupportNavigationIconDark".content()
    }

    @SuppressLint("SetTextI18n")
    override fun setListener() {
        binding.mBtnStatus.setOnClickListener {
            mIsHideStatusBar = if (!mIsHideStatusBar) {
                hideStatusBar()
                true
            } else {
                showStatusBar()
                false
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.mTvInsets) { _, windowInsetsCompat ->
            binding.mTvInsets.text =
                "${binding.mTvInsets.title}${windowInsetsCompat.systemWindowInsetTop}".content()
            windowInsetsCompat.consumeSystemWindowInsets()
        }
    }

    private fun String.content(): SpannableString {
        val split = split("   ")
        return SpannableString(this).apply {
            val colorSpan =
                ForegroundColorSpan(ContextCompat.getColor(this@KotlinActivity, R.color.btn3))
            setSpan(
                colorSpan,
                this.length - split[1].length,
                this.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
        }
    }

    private val TextView.title get() = text.toString().split("   ")[0] + "   "

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        initView()
    }
}