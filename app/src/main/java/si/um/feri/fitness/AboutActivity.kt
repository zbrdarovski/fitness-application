package si.um.feri.fitness
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val sharedPref = getSharedPreferences("myshared.data", Context.MODE_PRIVATE)
        val newText: TextView = findViewById(R.id.app_uuid)
        newText.text = "UUID: " + sharedPref.getString("ID", "DefaultNoData")
    }
}