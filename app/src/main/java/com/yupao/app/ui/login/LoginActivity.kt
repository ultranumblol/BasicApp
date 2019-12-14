package com.yupao.app.ui.login

import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.cc.ktx_ext_base.base.BaseVMActivity
import com.yupao.app.R
import com.yupao.app.util.Preference
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseVMActivity<LoginViewModel>() {
    override fun getLayoutResId(): Int = R.layout.activity_login
    override fun providerVMClass(): Class<LoginViewModel>? = LoginViewModel::class.java
    private val isLogin by Preference(Preference.IS_LOGIN, false)

    override fun initView() {
        login.setOnClickListener {
            mViewModel.login(username = userNameEt.text.toString(),password = passwordEt.text.toString())
        }
    }

    override fun initData() {

    }

    override fun startObserve() {
        mViewModel.uiState.observe(this, Observer {

            it.showSuccess?.let {
                 MaterialDialog(this).show {
                     lifecycleOwner()
                     message(text = "登录成功！！！！")
                 }
            }

        })
    }
}