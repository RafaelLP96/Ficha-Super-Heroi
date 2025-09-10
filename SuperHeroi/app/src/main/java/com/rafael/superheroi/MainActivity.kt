package com.rafael.superheroi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }


        val codi = getSharedPreferences("Data", MODE_PRIVATE)
        val check = codi.getString("PREF_NAME","")
        val button = findViewById<Button>(R.id.button)
        val edtCodi = findViewById<EditText>(R.id.edtCodi)

        if (check != "") {
            edtCodi.setText(check)
        }

        button.setOnClickListener {
            val getname: String = edtCodi.getText().toString()
            val quantidade:Int = getname.length

            if (quantidade<=0)
                Toast.makeText(this, "Por favor, escreva seu codinome", Toast.LENGTH_SHORT).show()
            else {
                val intent = Intent(this, CriacaoHeroiActivity::class.java)

                val editor = codi.edit()

                editor.putString("PREF_NAME", getname)

                editor.apply()

                startActivity(intent)
            }
        }


    }
}