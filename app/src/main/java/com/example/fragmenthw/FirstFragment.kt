package com.example.fragmenthw

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class FirstFragment : Fragment() {

    private var noteList: MutableList<Note>? = null
    private val db = context?.let { DBHelper(it, null) }
    private var recyclerViewAdapter: CustomAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteET: EditText = view.findViewById(R.id.fragmentNameNoteEditTextET)
        val saveBTN: Button = view.findViewById(R.id.fragmentSaveButtonBTN)
        var recyclerView: RecyclerView = view.findViewById(R.id.fragmentRecyclerView)

        saveBTN.setOnClickListener{

            if (noteET.text.isNotEmpty()){
                val note = noteET.text.toString()
                val id = noteList?.size?.plus(1)
                val date = Date().toString()
                val noteToDB = id?.let { it1 -> Note(it1,note,date) }

                if (noteToDB != null) {
                    noteList?.add(noteToDB)
                }

                recyclerView.adapter = recyclerViewAdapter
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerViewAdapter = noteList?.let { CustomAdapter(it) }



                if (noteToDB != null) {
                    db?.addNote(noteToDB)
                }

            } else {
                Toast.makeText(context, "Заполните поле", Toast.LENGTH_SHORT).show()
            }
        }
    }

}