package com.example.fragmenthw

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import java.io.Serializable


class DetailFragment : Fragment(), OnFragmentDataListener {

    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private lateinit var editText: EditText
    private lateinit var saveBTN: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onFragmentDataListener = requireActivity() as OnFragmentDataListener
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val note = arguments?.getSerializable("note") as Note
        editText = view.findViewById(R.id.detailFragmentNoteEditText)
        saveBTN = view.findViewById(R.id.detailFragmentSaveButtonBTN)
        saveBTN.setOnClickListener{
            note.name = editText.text.toString()
            onData(note)
        }
        return view
    }

    override fun onData(note: Note) {
        val bundle = Bundle()
        bundle.putSerializable("note", note)
        val transaction = this.fragmentManager?.beginTransaction()
        val firstFragment = FirstFragment()
        firstFragment.arguments = bundle

        transaction?.replace(R.id.containerID,firstFragment)
        transaction?.addToBackStack(null)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }

}