package com.yupao.app.model.bean

data class UserBean(
    val nickname: String,
    val token: String,
    val uid: Int,
    val username: String
) {
    override fun toString(): String {
        return "UserBean(nickname='$nickname', token='$token', uid=$uid, username='$username')"
    }
}
