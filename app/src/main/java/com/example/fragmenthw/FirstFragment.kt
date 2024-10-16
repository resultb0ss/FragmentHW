package com.example.fragmenthw

import android.annotation.SuppressLint
import android.content.Intent
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
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmenthw.databinding.FragmentFirstBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class FirstFragment : Fragment(){

    private var noteList: MutableList<Note> = mutableListOf()
    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private lateinit var binding: FragmentFirstBinding


    @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstBinding.inflate(inflater,container,false)
        onFragmentDataListener = requireActivity() as OnFragmentDataListener

        val db = DBHelper(requireContext(),null)
        noteList = db.getNotes()

        var adapter = CustomAdapter(noteList)
        binding.fragmentRecyclerView.adapter = adapter
        binding.fragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        binding.fragmentSaveButtonBTN.setOnClickListener{

            if (binding.fragmentNameNoteEditTextET.text.isNotEmpty()){
                val note = binding.fragmentNameNoteEditTextET.text.toString()
                val id = noteList.size.plus(1)
                val date = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Date())
                val noteToDB = Note(id,note,date)
                db.addNote(noteToDB)

                noteList = db.getNotes()
                adapter = CustomAdapter(noteList)
                binding.fragmentRecyclerView.adapter = adapter
                binding.fragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                adapter.notifyDataSetChanged()
                binding.fragmentNameNoteEditTextET.text.clear()

            } else {
                Toast.makeText(context, "Заполните поле", Toast.LENGTH_SHORT).show()
            }
        }

//        adapter.setOnNoteClickListener(object : CustomAdapter.OnNoteClickListener {
//            override fun onNoteClick(note: Note, position: Int) {
//
//                onFragmentDataListener.onData(note)
//            }
//        })

        return binding.root
    }

    //переопредели на обновление списка
    override fun onResume() {
        super.onResume()
    }
}