package com.example.myapplication.features.wagons.view

import android.os.Bundle
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
import com.example.myapplication.features.wagons.models.Wagon
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class WagonFragmentCreate : Fragment() {
    private val vm = get<WagonVM> { parametersOf("") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wagon_create, container, false)
    }

    override fun onStart() {
        super.onStart()

        val wagon_type = view?.findViewById<EditText>(R.id.wagon_type)
        val train = view?.findViewById<EditText>(R.id.wagon_train)
        val imageView = view?.findViewById<ImageView>(R.id.image)
        val createButton = view?.findViewById<Button>(R.id.buttonCreate)
        imageView?.setImageResource(R.drawable.rzd1)

        createButton?.setOnClickListener {
            val wagon = Wagon(0, wagon_type?.text.toString().toInt(), train?.text.toString().toInt())
            vm.createWagon(wagon)

            requireFragmentManager().beginTransaction().apply {
                replace(R.id.flFragment, WagonsFragment.newInstance())
                commit()
            }
        }

        val toolbar = view?.findViewById<Toolbar>(R.id.toolbar)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar?.setNavigationOnClickListener {
            requireFragmentManager().beginTransaction().apply {
                replace(R.id.flFragment, WagonsFragment.newInstance())
                commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = WagonFragmentCreate().apply {}
    }
}