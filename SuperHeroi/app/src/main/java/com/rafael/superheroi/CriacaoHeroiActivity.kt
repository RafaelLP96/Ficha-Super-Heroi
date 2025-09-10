package com.rafael.superheroi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class CriacaoHeroiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_criacao_heroi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val arquivo = getSharedPreferences("Data", MODE_PRIVATE)
        val codiNome = arquivo.getString("PREF_NAME","")
        val txtBoasVindas = findViewById<TextView>(R.id.txtBoasVindas)
        txtBoasVindas.text = "Seja bem vindo $codiNome!"

        ///Checar se ja foi editado
        val check1=intent.getBooleanArrayExtra("poderes")

        if (check1 != null && check1.size >= 6) {

            if (check1[0])
                findViewById<CheckBox>(R.id.Poder1).isChecked=true
            if (check1[1])
                findViewById<CheckBox>(R.id.Poder2).isChecked=true
            if (check1[2])
                findViewById<CheckBox>(R.id.Poder3).isChecked=true
            if (check1[3])
                findViewById<CheckBox>(R.id.Poder4).isChecked=true
            if (check1[4])
                findViewById<CheckBox>(R.id.Poder5).isChecked=true
            if (check1[5])
                findViewById<CheckBox>(R.id.Poder6).isChecked=true
        }

        ///Checar se avatar ja foi editado
        val check2=intent.getStringExtra("alinhamento")
        val gpAlinhamento = findViewById<RadioGroup>(R.id.radioAlinhamento)
        when (check2) {
            "Heroi" -> gpAlinhamento.check(R.id.rdAlinhamento1)
            "Vilão" -> gpAlinhamento.check(R.id.rdAlinhamento2)
            "Anti-Heroi" -> gpAlinhamento.check(R.id.rdAlinhamento3)
        }

        ///checar se avatar foi editado

        val check3=intent.getIntExtra("avatar", 0)
        val groupAvatares = findViewById<RadioGroup>(R.id.radioAvatares)
        when (check3){
            R.drawable.avatar1 -> groupAvatares.check(R.id.rdAvatar1)
            R.drawable.avatar2 -> groupAvatares.check(R.id.rdAvatar2)
            R.drawable.avatar3 -> groupAvatares.check(R.id.rdAvatar3)
            R.drawable.avatar4 -> groupAvatares.check(R.id.rdAvatar4)
        }

        val groupAlinhamento = findViewById<RadioGroup>(R.id.radioAlinhamento)

        val btnGerar = findViewById<Button>(R.id.btnGerar)

        btnGerar.setOnClickListener {
            val avatarSelecionado = groupAvatares.checkedRadioButtonId
            val alinhamentoSelecionado = groupAlinhamento.checkedRadioButtonId

            if (avatarSelecionado == -1){ ///Se nenhum avatar foi escolhido
                Toast.makeText(this, "Selecione um avatar!", Toast.LENGTH_SHORT).show()

            }else if (alinhamentoSelecionado == -1){ //Se nenhum alinhamento foi escolhido
                Toast.makeText(this, "Selecione um alinhamento!", Toast.LENGTH_SHORT).show()

            }else {//Salvar dados para gerar a ficha

                val avatar = when (avatarSelecionado){
                    R.id.rdAvatar1 -> R.drawable.avatar1
                    R.id.rdAvatar2 -> R.drawable.avatar2
                    R.id.rdAvatar3 -> R.drawable.avatar3
                    R.id.rdAvatar4 -> R.drawable.avatar4
                    else -> R.drawable.avatar1
                }
                val alinhamento = when (alinhamentoSelecionado){
                    R.id.rdAlinhamento1 -> "Heroi"
                    R.id.rdAlinhamento2 -> "Vilão"
                    R.id.rdAlinhamento3 -> "Anti-Heroi"
                    else -> "Heroi"
                }


                ///Coletar os poderes escolhidos
                val cb1 = findViewById<CheckBox>(R.id.Poder1)
                val cb2 = findViewById<CheckBox>(R.id.Poder2)
                val cb3 = findViewById<CheckBox>(R.id.Poder3)
                val cb4 = findViewById<CheckBox>(R.id.Poder4)
                val cb5 = findViewById<CheckBox>(R.id.Poder5)
                val cb6 = findViewById<CheckBox>(R.id.Poder6)

                //Salvar dados para serem tratados depois
                val poderesSelecionados = booleanArrayOf(
                    cb1.isChecked,
                    cb2.isChecked,
                    cb3.isChecked,
                    cb4.isChecked,
                    cb5.isChecked,
                    cb6.isChecked
                )

                val intent = Intent(this, FichaHeroiActivity::class.java)

                intent.putExtra("avatar", avatar)
                intent.putExtra("alinhamento", alinhamento)
                intent.putExtra("poderes", poderesSelecionados)

                startActivity(intent)
            }

        }

    }
}