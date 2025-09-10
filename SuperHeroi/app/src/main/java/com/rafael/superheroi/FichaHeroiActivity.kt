package com.rafael.superheroi

import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FichaHeroiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ficha_heroi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)


            ///Adicionar os poderes
            val txtpoderes = findViewById<TextView>(R.id.txtPoderes)
            val poderes = intent.getBooleanArrayExtra("poderes")
            var mytxt = ""

            if (poderes != null && poderes.size >= 6) {

                if (poderes[0])
                    mytxt += "Super-Visão\n"
                if (poderes[1])
                    mytxt += "Raio-Laser\n"
                if (poderes[2])
                    mytxt += "Super-Força\n"
                if (poderes[3])
                    mytxt += "Super-Velocidade\n"
                if (poderes[4])
                    mytxt += "Voar\n"
                if (poderes[5])
                    mytxt += "Telepatia\n"
            }else
                mytxt=""
            txtpoderes.text = "Seus poderes são:\n$mytxt"

            ///Aplicar o avatar
            val imgavatar = findViewById<ImageView>(R.id.imgAvatar)
            val avatarResId = intent.getIntExtra("avatar", R.drawable.avatar1)
            imgavatar.setImageResource(avatarResId)


            //Aplicar o codinome
            val arquivo = getSharedPreferences("Data", MODE_PRIVATE)
            val codiNome = arquivo.getString("PREF_NAME","")
            val tvcodinome = findViewById<TextView>(R.id.tvCodinome)
            tvcodinome.text = "Codinome:$codiNome";

            ///Alinhamento
            val alinhamento = findViewById<TextView>(R.id.tvAlinhamento)
            val getAlinhamento = intent.getStringExtra("alinhamento")
            alinhamento.text = "Alinhamento:$getAlinhamento";

            val layout = findViewById<ConstraintLayout>(R.id.main)
            var colour=""
            when (getAlinhamento){
                "Heroi" -> colour="#4763ff"
                "Vilão" -> colour="#ff6347"
                "Anti-Heroi" -> colour="#7a6e7a"
            }
            layout.setBackgroundColor(Color.parseColor(colour))

            ///Editar
            val btnEditar = findViewById<Button>(R.id.btnEditar)

            btnEditar.setOnClickListener {
                val intent = Intent(this, CriacaoHeroiActivity::class.java)

                intent.putExtra("poderes", poderes)
                intent.putExtra("alinhamento", getAlinhamento)
                intent.putExtra("avatar",avatarResId)
                startActivity(intent)
            }


            insets
        }

    }
}