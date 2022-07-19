package com.example.notez

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var recyclerView: RecyclerView
        lateinit var adapter_rv : CustomAdapter
        lateinit var array_rv : ArrayList<DataClass>

        //recycler2
        lateinit var recyclerView2: RecyclerView
        lateinit var adapter_rv2 : CustomAdapter2
        lateinit var array_rv2 : ArrayList<DataClass2>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        buatAdapter()
        buatRecycleView()
        addDanDeleteNotes()
        saveData()
        noteClick()

        loadData2()
        buatAdapter2()
        buatRecycleView2()
        addDanDeleteNotes2()
        saveData2()
        noteClick2()

//        //gotostopwatch   GAGAL (PROJECTSTOPWATCH)
//        toStopwatch.setOnClickListener() {
//            Intent(this, StopwatchActivityBinding::class.java).also {
//                startActivity(it)
//            }
//        }


        //Ganti Theme
        val rollbutton: Button = findViewById(R.id.buttonTheme) //mencari id button
        rollbutton.setOnClickListener { //sett button

            //execute
            rollDice()
        }

        //fun search

        search.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                search(p0.toString())
            }
        })

        //tentang
        logonotes.setOnClickListener() {
            Intent(this, Tentang::class.java).also {
                startActivity(it)
            }
        }

        //GANTI POSISI SPAN LIST NOTES
        buttonGrid.setOnClickListener() {
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        buttonList.setOnClickListener() {
            recyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    private fun addDanDeleteNotes() {
        fabAddNote.setOnClickListener{
            Intent(this@MainActivity, TambahNotesActivity::class.java).also {
                startActivity(it)
            }
        }
    }


    private fun search(text: String) {
        val filteredList = ArrayList<DataClass>()

        for(item in array_rv){
            if (item.data_judul.lowercase().contains(text.lowercase())) {
                filteredList.add(item)
            }
        }
        adapter_rv.filteredData(filteredList)
    }

    private fun loadData() {
        val save = getSharedPreferences("shareData", Context.MODE_PRIVATE)
        val gson = Gson()
        array_rv = ArrayList()
        val emptyArray = gson.toJson(array_rv)
        val json = save.getString("json", emptyArray)
        val type = object : TypeToken<ArrayList<DataClass>>() {}.type
        array_rv = gson.fromJson(json, type)
    }

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


    private fun buatRecycleView() {
        recyclerView = am_rv_note
        //biarkekanan
        //recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter_rv
    }

    private fun buatAdapter() {
        adapter_rv = CustomAdapter(array_rv)
    }

    //ini onclick

    private fun noteClick(){
        adapter_rv.setItemClick(object : CustomAdapter.itemClick {
            override fun click(position: Int) {
                Intent(this@MainActivity, TambahNotesActivity::class.java).also {
                    it.putExtra("POSITION", position)
                    startActivity(it)
                }
            }
        })
    }

    //recycler2


    private fun addDanDeleteNotes2() {
        fabAddNotetodo.setOnClickListener{
            Intent(this@MainActivity, TambahTodoActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun loadData2() {
        val save = getSharedPreferences("shareData2", Context.MODE_PRIVATE)
        val gson = Gson()
        array_rv2 = ArrayList()
        val emptyArray = gson.toJson(array_rv2)
        val json = save.getString("json", emptyArray)
        val type = object : TypeToken<ArrayList<DataClass2>>() {}.type
        array_rv2 = gson.fromJson(json, type)
    }

    private fun saveData2() {
        val save = getSharedPreferences("shareData2", Context.MODE_PRIVATE)
        val editor = save.edit()
        val gson = Gson()
        val json = gson.toJson(array_rv2)

        editor.apply {
            putString("json", json)
            apply()
        }
    }


    private fun buatRecycleView2() {
        recyclerView2 = recycleview_kanan
//        recyclerView2.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView2.adapter = adapter_rv2
    }

    private fun buatAdapter2() {
        adapter_rv2 = CustomAdapter2(array_rv2)
    }

    //ini onclick

    private fun noteClick2(){
        adapter_rv2.setItemClick(object : CustomAdapter2.itemClick {
            override fun click(position: Int) {
                Intent(this@MainActivity, TambahTodoActivity::class.java).also {
                    it.putExtra("POSITION", position)
                    startActivity(it)
                }
            }
        })
    }


    //GANTI THEME
    private fun rollDice() {
        //Create new
        val tema = Dice(4)
        val gantiTema = tema.roll()

        //cari gambar di layout
        val diceImage: ImageView = findViewById(R.id.bgcatatan)
        val diceImage2: ImageView = findViewById(R.id.backgroundmain)
        val diceImage3: ImageView = findViewById(R.id.bgctodo)


        //ganti gambar
        when (gantiTema) {
            1 -> diceImage.setImageResource(R.drawable.merah).also { diceImage2.setImageResource(R.drawable.merah).also { diceImage3.setImageResource(R.drawable.merah) } }
            2 -> diceImage.setImageResource(R.drawable.biruuu).also { diceImage2.setImageResource(R.drawable.biruuu).also { diceImage3.setImageResource(R.drawable.biruuu) } }
            3 -> diceImage.setImageResource(R.drawable.ungu).also { diceImage2.setImageResource(R.drawable.ungu).also { diceImage3.setImageResource(R.drawable.ungu) } }
            4 -> diceImage.setImageResource(R.drawable.hijau2).also { diceImage2.setImageResource(R.drawable.hijau2).also { diceImage3.setImageResource(R.drawable.hijau2) } }

        }
    }
}