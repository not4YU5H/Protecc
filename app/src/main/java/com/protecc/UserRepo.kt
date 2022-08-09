package com.protecc

class UserRepository(private val appPref: AppPref) {

    fun saveUserData(name: String, email: String) {
        appPref.apply {
            setUserName(name)
            setUserEmail(email)
        }
    }

    fun getUserName() = appPref.getUserName()
    fun getUserEmail() = appPref.getUserEmail()

    fun setMasterKey(key: String) = appPref.setMasterKey(key)
    fun getMasterKey() = appPref.getMasterKey()

}