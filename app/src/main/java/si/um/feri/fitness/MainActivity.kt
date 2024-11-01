package si.um.feri.fitness

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import java.util.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler: Button = findViewById(R.id.recycler)
        recycler.setOnClickListener {
            val i = Intent(this@MainActivity, RecyclerViewActivity::class.java)
            startActivity(i)
        }

        val language: Button = findViewById(R.id.language)
        language.setOnClickListener {
            val i = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(i)
        }

        val info: Button = findViewById(R.id.info)
        info.setOnClickListener {
            for (f in app.fitnessList)
                Timber.i(f.toString())
        }

        val getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    val n = data?.getStringExtra("name")
                    val a = data?.getStringExtra("address")
                    val f = Fitness(n.toString(), a.toString())
                    app.fitnessList.add(f)
                }
            }

        val add
                : Button = findViewById(R.id.add)
        add.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            getContent.launch(intent)
        }

        val about: Button = findViewById(R.id.about)
        about.setOnClickListener {
            val i = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(i)
        }

        val exit: Button = findViewById(R.id.exit)
        exit.setOnClickListener {
            moveTaskToBack(true)
            exitProcess(-1)
        }
    }
}