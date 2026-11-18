package com.example.expensenote_03

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.temporal.TemporalAmount

class   MainActivity : AppCompatActivity() {

    //สร้างตัวแปรสำหรับหน้า UI Element
     private lateinit var etAmount: EditText
     private lateinit var  etNote: EditText
     private lateinit var  btnAdd: Button
     private lateinit var  btnClear: Button
     private lateinit var  tvLastItem: TextView
     private lateinit var  tvtotal: TextView

     private var total: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etAmount = findViewById<EditText>(R.id.etAmount)
        etNote = findViewById<EditText>(R.id.etDescription)
        btnAdd = findViewById<Button>(R.id.btnSave)
        btnClear = findViewById<Button>(R.id.btnClear)
        tvLastItem = findViewById<TextView>(R.id.tvRecentItems)
        tvtotal = findViewById<TextView>(R.id.tvTotalAmount)

        btnAdd.setOnClickListener{

            val amountText = etAmount.text.toString().trim()
            val noteText = etNote.text.toString().trim()

            if (amountText.isEmpty()){
                Toast.makeText(this,"กรุณากรอกจำนวนเงิน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val amount = amountText.toDouble()
            if(amount == null){
                Toast.makeText(this,"กรุณากรอกจำนวนเงินเป็นตัวเลข", Toast.LENGTH_SHORT).show()
            }
            total += amount

            val showNote = if(noteText.isNotBlank()) noteText else "ไม่ระบุ"

            tvLastItem.text = "รายการล่าสุด: $showNote - %.2f บาท" .format(amount)
            tvtotal.text = "ยอดรวมวันนี้ : %.2f บาท".format(total)

            etAmount.text.clear()
            etNote.text.clear()

        }
        btnClear.setOnClickListener {
            total = 0.0

            etAmount.text.clear()
            etNote.text.clear()

            tvLastItem.text = "รายการล่าสุด : - "
            tvtotal.text = "ยอดรวมวันนี้ 0.00 บาท"
        }
    }
}