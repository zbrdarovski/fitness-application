package si.um.feri.fitness

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import timber.log.Timber
import java.util.*
import com.google.gson.Gson
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.lang.Exception
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

const val MY_SP_FILE_NAME = "myshared.data"
const val FILE_NAME = "data.json"

class MyApplication : Application() {

    var fitnessList = mutableListOf<Fitness>()
    lateinit var gson: Gson
    lateinit var file: File


    lateinit var sharedPref: SharedPreferences
    lateinit var configuration: Configuration

    override fun onCreate() {
        super.onCreate()
        initShared()
        if (!containsID()) {
            saveID(UUID.randomUUID().toString().replace("-", ""))
        }
        Timber.d("onCreate MyApplication")
        configuration = resources.configuration
        val locale = Locale(sharedPref.getString("LG", "DefaultNoData")!!)
        configuration.setLocale(locale)

        file = File(filesDir, FILE_NAME)
        gson = GsonBuilder().setPrettyPrinting().create()

        generateFitness()
        initData()
    }

    private fun initShared() {
        sharedPref = getSharedPreferences(MY_SP_FILE_NAME, Context.MODE_PRIVATE)
    }

    private fun saveID(id: String) {
        with(sharedPref.edit()) {
            putString("ID", id)
            apply()
        }
    }

    private fun containsID(): Boolean {
        return sharedPref.contains("ID")
    }

    private fun generateFitness() {
        val fitnessName = "fit"
        val fitnessAddress = "fit"
        var number = 1
        for (i in 0..99) {
            try {
                val fit = Fitness(fitnessName + number, fitnessAddress + number)
                fitnessList.add(fit)
                number++
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        saveToFile()
    }

    fun saveToFile() {
        try {
            FileUtils.writeStringToFile(file, gson.toJson(fitnessList))
            Timber.d("Save to file.")
        } catch (e: IOException) {
            Timber.d("Can't save " + file.path)
        }
    }

    fun initData() {
        fitnessList = try {
            Timber.d("My file data:${FileUtils.readFileToString(file)}")
            gson.fromJson(FileUtils.readFileToString(file), object : TypeToken<List<Fitness>>() {}.type)
        } catch (e: IOException) {
            Timber.d("No file init data.")
            mutableListOf()
        }
    }

}




