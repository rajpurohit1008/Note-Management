package com.rajpurohit.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val context: Context, private val Listener :ClickListener): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val allNotes = ArrayList<Note>()
    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val delete :ImageView = itemView.findViewById(R.id.image)
        val textView : TextView = itemView.findViewById(R.id.text)
        val discrip : TextView = itemView.findViewById(R.id.discrip)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentItem = allNotes[position]
        holder.textView.text = currentItem.title
        holder.discrip.text = currentItem.discription
        holder.delete.setOnClickListener{
            Listener.OnClickDelete(allNotes[position])
        }
    }

    override fun getItemCount(): Int = allNotes.size
    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface ClickListener{
    fun OnClickDelete(note:Note)
}