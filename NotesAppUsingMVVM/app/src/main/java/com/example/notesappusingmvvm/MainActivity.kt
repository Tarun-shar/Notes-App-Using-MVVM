package com.example.notesappusingmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteItemAdapter(this,this)
        recyclerView.adapter = adapter

//        Access viewModel
        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

//        only those classes can observe this viewmodel which having life cycle like fragment and Activity coz
//        they life cycle methods like:- OnCreate(),OnStart(),etc.
        viewModel.allData.observe(this, Observer {list ->
//            check if list is not empty then it will print it
            list?.let {
                adapter.updateList(it)
            }
        })


    }

    override fun onItemClicked(note: Note) {
       viewModel.deleteNote(note)
        Toast.makeText(this, "Note Deleted!", Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {

        val inputText = findViewById<EditText>(R.id.input_note)
        if (inputText.text.toString().isNotEmpty()){
            viewModel.insertNote(Note(inputText.text.toString()))
            Toast.makeText(this, "${inputText.text.toString()} Inserted", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Please enter note!", Toast.LENGTH_SHORT).show()
        }
    }
}
