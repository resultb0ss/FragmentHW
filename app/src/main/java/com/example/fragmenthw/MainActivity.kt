package com.example.fragmenthw

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), OnFragmentDataListener {
    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            val firstFragment = FirstFragment()
            supportFragmentManager.beginTransaction().replace(R.id.containerID, firstFragment).commit()
        }


    }

    override fun onData(note: Note) {
        val bundle = Bundle()
        bundle.putSerializable("note",note)

        val transaction = this.supportFragmentManager.beginTransaction()
        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        transaction.replace(R.id.containerID, detailFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}