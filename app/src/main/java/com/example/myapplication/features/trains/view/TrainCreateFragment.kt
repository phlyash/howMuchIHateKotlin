package com.example.myapplication.features.trains.view

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
import com.example.myapplication.features.trains.models.Train
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf


class TrainCreateFragment : Fragment() {
    private val vm = get<TrainVM> { parametersOf("") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_train_create, container, false)
    }

    override fun onStart() {
        super.onStart()

        val trainName = view?.findViewById<EditText>(R.id.train_name)
        val locomotive = view?.findViewById<EditText>(R.id.locomotive)
        val route = view?.findViewById<EditText>(R.id.route)
        val train_type = view?.findViewById<EditText>(R.id.train_type)
        val imageView = view?.findViewById<ImageView>(R.id.image)
        val createButton = view?.findViewById<Button>(R.id.buttonCreate)
        imageView?.setImageResource(R.drawable.rzd1)

        createButton?.setOnClickListener {
            val train = Train(0, trainName?.text.toString(), locomotive?.text.toString().toInt(),
                route?.text.toString().toInt(), train_type?.text.toString().toInt())
            vm.createTrain(train)

            requireFragmentManager().beginTransaction().apply {
                replace(R.id.flFragment, TrainsFragment.newInstance())
                commit()
            }
        }

        val toolbar = view?.findViewById<Toolbar>(R.id.toolbar)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar?.setNavigationOnClickListener {
            requireFragmentManager().beginTransaction().apply {
                replace(R.id.flFragment, TrainsFragment.newInstance())
                commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TrainCreateFragment().apply {}
    }
}