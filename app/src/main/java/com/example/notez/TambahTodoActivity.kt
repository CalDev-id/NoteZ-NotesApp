package com.example.notez

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.notes.*
import kotlinx.android.synthetic.main.todolist.*

fun main() {
    val myFirstDice = Dice2(3)
    val rollResult = myFirstDice.roll()
    // println("Your ${myFirstDice.numSides} sided dice rolled ${rollResult}!")

}
// class rolls gacha
class Dice2(val numSides: Int) { //privat biar ga kemana mana
    fun roll(): Int {
        return (1..numSides).random()
    }
}


class TambahTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todolist)

        //Ganti Theme
        val rollbutton2: Button = findViewById(R.id.buttonthemetodo) //mencari id button
        rollbutton2.setOnClickListener { //sett button

            //execute
            rollDice2()
        }

        //deklarasi pos as load data
        val pos2 = intent.getIntExtra("POSITION",-1)

        if (pos2 != -1){
            n_et_judultodo.setText(MainActivity.array_rv2[pos2].data_waktu)
            n_et_todo.setText(MainActivity.array_rv2[pos2].data_todo)
        }
        //summon load data disini
        //lana  loadData()

        fabSaveTodo.setOnClickListener{

            val judulTodo = n_et_judultodo.text.toString()
            val catatantodo = n_et_todo.text.toString()

            //loaddataspnj
            if(judulTodo.isNotBlank()){
                if(pos2 != -1){
                    MainActivity.array_rv2[pos2] = DataClass2(judulTodo, catatantodo)
                    MainActivity.adapter_rv2.notifyItemChanged(pos2)
                } else {
                    MainActivity.array_rv2.add(DataClass2(judulTodo, catatantodo))
                    MainActivity.adapter_rv2.notifyItemInserted(MainActivity.array_rv2.size)
                }
            }
            saveData2()
            finish()
        }
        buttonBacktodo.setOnClickListener {
            finish()
        }
        buttonDeltodo.setOnClickListener {
            MainActivity.array_rv2.removeAt(pos2)
            MainActivity.adapter_rv2.notifyItemRemoved(pos2)
            saveData2()
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
    private fun saveData2() {
        //share aja, gw disini seminggu benerin bug cuy, alhamdulillah bisa
        val save2 = getSharedPreferences("shareData2", Context.MODE_PRIVATE)
        val editor2 = save2.edit()
        val gson2 = Gson()
        val json2 = gson2.toJson(MainActivity.array_rv2)

        editor2.apply {
            putString("json", json2)
            apply()
        }
    }

    //GANTI THEME
    private fun rollDice2() {
        //Create new
        val dice2 = Dice2(3)
        val diceRoll = dice2.roll()

        //cari gambar di layout
        val diceImage: ImageView = findViewById(R.id.backgroundtodo)

        //ganti gambar gacha tiap angka
        when (diceRoll) {
            1 -> diceImage.setImageResource(R.drawable.merah)
            2 -> diceImage.setImageResource(R.drawable.biruuu)
            3 -> diceImage.setImageResource(R.drawable.ungu)
            4 -> diceImage.setImageResource(R.drawable.hijau2)
        }
    }

}
