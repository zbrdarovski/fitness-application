package si.um.feri.fitness

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import java.util.*

const val MY_SP_SETTINGS_FILE_NAME = "mysharedsettings.data"
lateinit var sharedPref: SharedPreferences

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        initShared()
        Timber.d("onCreate SettingsActivity")


        val english: Button = findViewById(R.id.english)
        english.setOnClickListener {
            saveLanguage("en_US")
        }

        val slovenian: Button = findViewById(R.id.slovenian)
        slovenian.setOnClickListener {
            saveLanguage("sl_SI")
        }
        val macedonian: Button = findViewById(R.id.macedonian)
        macedonian.setOnClickListener {
            saveLanguage("mk_MK")
        }
    }

    fun initShared() {
        sharedPref = getSharedPreferences(MY_SP_SETTINGS_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun saveLanguage(language: String) {
        with(sharedPref.edit()) {
            putString("LG", language)
            apply()
        }
    }
}