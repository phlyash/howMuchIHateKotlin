package com.example.myapplication.features.locomotives.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.features.locomotives.models.Locomotive
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [TrainEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class LocomotiveCreateFragment : Fragment() {
    private val vm: LocomotiveVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_locomotive_create, container, false)
    }

    override fun onStart() {
        super.onStart()

        val locomotiveName = view?.findViewById<EditText>(R.id.locomotive_name)
        val locomotiveType = view?.findViewById<EditText>(R.id.locomotive_type)
        val imageView = view?.findViewById<ImageView>(R.id.image)
        val createButton = view?.findViewById<Button>(R.id.buttonCreate)
        imageView?.setImageResource(R.drawable.loco_rzd)

        createButton?.setOnClickListener {
            val locomotive = Locomotive(0, locomotiveName?.text.toString(), locomotiveType?.text.toString().toInt())
            vm.createLocomotive(locomotive)

            requireFragmentManager().beginTransaction().apply {
                replace(R.id.flFragment, LocomotiveFragment.newInstance())
                commit()
            }
        }

        val toolbar = view?.findViewById<Toolbar>(R.id.toolbar)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar?.setNavigationOnClickListener {
            Log.e("toolbar", "click")
            requireFragmentManager().beginTransaction().apply {
                replace(R.id.flFragment, LocomotiveFragment.newInstance())
                commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LocomotiveCreateFragment().apply {
        }
    }
}