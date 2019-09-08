package com.electiva1.todolist

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ArrayAdapter
import android.widget.ListView
import android.view.View
import android.widget.AdapterView
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    private var tareas: ArrayList<String>? = null
    private var adaptador1: ArrayAdapter<String>? = null
    private var lv1: ListView? = null
    private var et1: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tareas = ArrayList()
        tareas!!.add("marcos : 43734843")
        tareas!!.add("luis : 6554343")
        tareas!!.add("ana : 7445434")
        adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tareas!!)
        lv1 = findViewById(R.id.listView) as ListView
        lv1!!.adapter = adaptador1

        et1 = findViewById(R.id.editText) as EditText

        lv1!!.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->
            val dialogo1 = AlertDialog.Builder(this@MainActivity)
            dialogo1.setTitle("Importante")
            dialogo1.setMessage("¿ Elimina esta tarea?")
            dialogo1.setCancelable(false)
            dialogo1.setPositiveButton("Confirmar") { dialogo1, id ->
                tareas!!.removeAt(i)
                adaptador1!!.notifyDataSetChanged()
            }
            dialogo1.setNegativeButton("Cancelar") { dialogo1, id -> }
            dialogo1.show()

            false
        }
    }

    fun agregar(v: View) {
        tareas!!.add(et1!!.text.toString())
        adaptador1!!.notifyDataSetChanged()
        et1!!.setText("")
    }
}
