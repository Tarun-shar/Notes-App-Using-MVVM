package com.example.notesappusingmvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val repository : NoteRepository
    val allData : LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(dao)
        allData = repository.allNotes()
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteData(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertData(note)
    }
}
