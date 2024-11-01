package si.um.feri.fitness

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val send: Button = findViewById(R.id.send_input)

        send.setOnClickListener {
            val data = Intent()
            val name = findViewById<TextView>(R.id.input_fitness_name)
            val address = findViewById<TextView>(R.id.input_fitness_address)
            data.putExtra("name", name.text.toString())
            data.putExtra("address", address.text.toString())
            name.text = ""
            address.text = ""
            setResult(RESULT_OK, data)
            finish()
        }
        val qrScan: Button = findViewById(R.id.qr_scan)
        qrScan.setOnClickListener {
            val i = Intent(this@InputActivity, QrActivity::class.java)
            startActivity(i)
        }
    }
}