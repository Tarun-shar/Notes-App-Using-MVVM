package com.example.notesappusingmvvm

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    fun allNotes(): LiveData<List<Note>> = noteDao.getAllNote()

    suspend fun insertData(note: Note){
        noteDao.insert(note)
    }

    suspend fun deleteData(note: Note){
        noteDao.delete(note)
    }
}