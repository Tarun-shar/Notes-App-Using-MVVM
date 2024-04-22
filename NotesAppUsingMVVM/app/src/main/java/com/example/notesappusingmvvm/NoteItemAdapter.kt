package com.example.notesappusingmvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteItemAdapter(private val context:Context, private val listener: INotesRVAdapter):RecyclerView.Adapter<NoteItemAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){

        val note: TextView = view.findViewById(R.id.note)
        val deleteNote: ImageView = view.findViewById(R.id.delete_note)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemAdapter.ViewHolder {
        val view = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_file,parent,false))
        view.deleteNote.setOnClickListener {
            listener.onItemClicked(allNotes[view.adapterPosition])
        }
        return view
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteItemAdapter.ViewHolder, position: Int) {
        val module = allNotes.get(position)
        holder.note.text = module.text

    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }

}

// Make interface for handling the clicks
interface INotesRVAdapter{
    fun onItemClicked(note: Note)
}