package com.example.fragmenthw

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class FirstFragment : Fragment() {

    private var noteList: MutableList<Note> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteET: EditText = view.findViewById(R.id.fragmentNameNoteEditTextET)
        val saveBTN: Button = view.findViewById(R.id.fragmentSaveButtonBTN)
        val recyclerView: RecyclerView = view.findViewById(R.id.fragmentRecyclerView)

        val db = DBHelper(requireContext(),null)

        noteList = db.getNotes()

        var adapter = CustomAdapter(noteList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        saveBTN.setOnClickListener{

            if (noteET.text.isNotEmpty()){
                val note = noteET.text.toString()
                val id = noteList.size.plus(1)
                val date = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Date())
                val noteToDB = Note(id,note,date)
                db.addNote(noteToDB)

                noteList = db.getNotes()
                adapter = CustomAdapter(noteList)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                adapter.notifyDataSetChanged()
                noteET.text.clear()

            } else {
                Toast.makeText(context, "Заполните поле", Toast.LENGTH_SHORT).show()
            }
        }
    }

}