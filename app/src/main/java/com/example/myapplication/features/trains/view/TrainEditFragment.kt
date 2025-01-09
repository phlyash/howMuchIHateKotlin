package com.example.myapplication.features.trains.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.features.trains.models.Train
import com.example.myapplication.features.trains.models.TrainType
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


const val TRAIN_ID = "train_id"

class TrainEditFragment : Fragment() {
    private val trainVM = get<TrainVM> { parametersOf("")}
    private val ttvm: TrainTypesVM by viewModel()
    private var trainId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            trainId = it.getInt(TRAIN_ID)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_train_edit, container, false)
    }

    override fun onStart() {
        super.onStart()

        trainId.let { trainVM.fetchTrain(it) }
        ttvm.fetchTrainTypes()

        val trainName = view?.findViewById<EditText>(R.id.train_name)
        val locomotive = view?.findViewById<Spinner>(R.id.locomotive)
        val route = view?.findViewById<EditText>(R.id.route)
        val imageView = view?.findViewById<ImageView>(R.id.image)
        val updateButton = view?.findViewById<Button>(R.id.buttonSave)
        val deleteButton = view?.findViewById<Button>(R.id.buttonDelete)
        val trainType = view?.findViewById<EditText>(R.id.train_type)

        ttvm.train_types.observe(viewLifecycleOwner) {
            val strings = ArrayList<String>()
            for (trainType in it) {
                strings.add(trainType.name)
            }

            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, strings)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            locomotive?.setAdapter(adapter)
        }

        trainVM.train.observe(viewLifecycleOwner) {
            val train = Train(it.id, it.name, it.locomotive, it.route, it.train_type)

            trainName?.setText(it.locomotive_name)

            route?.setText(train.route.toString())
            trainType?.setText(train.train_type.toString())
            when (train.train_type) {
                1 -> imageView?.setImageResource(R.drawable.train_cargo)
                2 -> imageView?.setImageResource(R.drawable.train_people)
                3 -> imageView?.setImageResource(R.drawable.train_mail)
                else -> imageView?.setImageResource(R.drawable.rzd1)
            }
        }

        updateButton?.setOnClickListener {
            val train = Train(trainId, trainName?.text.toString(), locomotive?.selectedItem.toString().toInt(),
                route?.text.toString().toInt(), trainType?.text.toString().toInt())
            trainVM.updateTrain(trainId, train)

            requireFragmentManager().beginTransaction().apply {
                replace(R.id.flFragment, TrainsFragment.newInstance())
                commit()
            }
        }

        deleteButton?.setOnClickListener{
            trainVM.deleteTrain(trainId)

            requireFragmentManager().beginTransaction().apply {
                replace(R.id.flFragment, TrainsFragment.newInstance())
                commit()
            }
        }

        val toolbar = view?.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar?.setNavigationOnClickListener {
            Log.e("toolbar", "click")
            requireFragmentManager().beginTransaction().apply {
                replace(R.id.flFragment, TrainsFragment.newInstance())
                commit()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) = TrainEditFragment().apply {
            arguments = Bundle().apply {
                putInt(TRAIN_ID, id)
            }
        }
    }
}