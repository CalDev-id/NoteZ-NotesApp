package com.example.notez

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.notez.MainActivity.Companion.adapter_rv
import com.example.notez.MainActivity.Companion.array_rv
import com.google.gson.Gson
import kotlinx.android.synthetic.main.notes.*

fun main() {
    val myFirstDice = Dice(3)
    val rollResult = myFirstDice.roll()

}
// class rolls gacha
class Dice(val numSides: Int) { //privat biar ga kemana mana
    fun roll(): Int {
        return (1..numSides).random()
    }
}


class TambahNotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes)

        //Ganti Theme
        val rollbutton: Button = findViewById(R.id.buttonthemenotes) //mencari id button
        rollbutton.setOnClickListener { //sett button

            //execute
            rollDice()
        }

        //deklarasi pos as load data
        val pos = intent.getIntExtra("POSITION",-1)

        if (pos != -1){
            n_et_judul.setText(array_rv[pos].data_judul)
            n_et_catatan.setText(array_rv[pos].data_catatan)
        }
        //summon load data disini
        //lana  loadData()

        fabSaveNote.setOnClickListener{

            val judul = n_et_judul.text.toString()
            val catatan = n_et_catatan.text.toString()

            //loaddataspnj
            if(judul.isNotBlank()){
                if(pos != -1){
                    array_rv[pos] = DataClass(judul, catatan)
                    adapter_rv.notifyItemChanged(pos)
                } else {
                    array_rv.add(DataClass(judul, catatan))
                    adapter_rv.notifyItemInserted(array_rv.size)
                }
            }
            saveData()
            finish()
        }
        buttonBack.setOnClickListener {
            finish()
        }
        buttonDel.setOnClickListener {
            array_rv.removeAt(pos)
            adapter_rv.notifyItemRemoved(pos)
            saveData()
            finish()
        }
    }
/*
    //load data ini lana
    private fun loadData(){
    val extra: Bundle? = intent.extras
    if(extra != null) {
        val data = array_rv[extra.getInt("POSITION")]
        n_et_judul.setText( data.data_judul)
        n_et_catatan.setText(data.data_catatan)
    }

}
 */

    private fun saveData() {
        val save = getSharedPreferences("shareData", Context.MODE_PRIVATE)
        val editor = save.edit()
        val gson = Gson()
        val json = gson.toJson(array_rv)

        editor.apply {
            putString("json", json)
            apply()
        }
    }

    //GANTI THEME
    private fun rollDice() {
        //Create new
        val dice = Dice(3)
        val diceRoll = dice.roll()

        //cari gambar di layout
        val diceImage: ImageView = findViewById(R.id.backgroundnotes)
        //diceImage.setImageResource(R.drawable.dice_2) gw apus karna kan gacha

        //ganti gambar gacha tiap angka
        when (diceRoll) {
            1 -> diceImage.setImageResource(R.drawable.merah)
            2 -> diceImage.setImageResource(R.drawable.biruuu)
            3 -> diceImage.setImageResource(R.drawable.ungu)
            4 -> diceImage.setImageResource(R.drawable.hijau2)
        }
    }

}
