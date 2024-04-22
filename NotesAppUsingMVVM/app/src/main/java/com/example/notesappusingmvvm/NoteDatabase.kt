package com.example.notesappusingmvvm

import android.content.Context
import android.content.Entity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

//    use singleton means only one process can access this at a time
    companion object{

    @Volatile
    private var INSTANCE: NoteDatabase? = null

    fun getDatabase(context: Context): NoteDatabase {
        // if the INSTANCE is not null, then return it,
        // if it is, then create the database
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "Note_DB"
            ).build()
            INSTANCE = instance
            // return instance
            instance
        }
    }

//        fun getDatabase(context: Context):NoteDatabase{
//            if (INSTANCE == null) {
//                synchronized(NoteDatabase::class) {
//                    Room.databaseBuilder(context.applicationContext,
//                    NoteDatabase::class.java,
//                    "NoteDB")
//                        .build()
//                }
//            }
//            return INSTANCE!!
//        }
    }
}