package com.cc.ktx_ext_base.core

import android.os.Looper


fun isOnMainThread() = Looper.myLooper() == Looper.getMainLooper()