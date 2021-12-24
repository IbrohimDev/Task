package uz.gita.task.data.local

import android.content.Context
import com.securepreferences.SecurePreferences

class LocalStorage constructor(context:Context) {

    private val pref = SecurePreferences(context, "1234567", "ibrohim")
}