package com.rajpurohit.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() ,ClickListener{
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView:RecyclerView= findViewById(R.id.recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)
         val adapter = NoteAdapter(this,this)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
       viewModel.allNotes.observe(this, Observer {
            adapter.updateList(it)
        })


    }

    override fun OnClickDelete(note: Note) {
    viewModel.deleteNote(note)
        Toast.makeText(this, "${note.title} Delete", Toast.LENGTH_SHORT).show()
    }

    fun submit(view: View) {

        val input :EditText = findViewById(R.id.input)
       val discription :EditText = findViewById(R.id.dispri)
        val noteText = input.text.toString()
        val discrip = discription.text.toString()
        Toast.makeText(this, "$noteText Inserted", Toast.LENGTH_SHORT).show()

        if(noteText.isNotEmpty())
        viewModel.insertNote(Note(noteText,discrip))
        input.text.clear()
        discription.text.clear()

    }
}