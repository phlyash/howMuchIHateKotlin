package com.example.myapplication.features.wagons.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.features.pdfUtils.createAndOpenPdf
import com.example.myapplication.features.wagons.models.Wagon
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

const val WAGON_ID = "wagon_id"

class WagonEditFragment : Fragment() {
    private val vm = get<WagonVM> { parametersOf("") }
    private val wtvm: WagonTypeVM by viewModel()
    private var wagonId: Int = 0
    private lateinit var wagon: Wagon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            wagonId = it.getInt(WAGON_ID)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wagon_edit, container, false)
    }

    override fun onStart() {
        super.onStart()

        wagonId.let { vm.fetchWagon(it) }
        wtvm.fetchTrainTypes()

        val wagonTrain = view?.findViewById<EditText>(R.id.wagon_train)
        val wagonType = view?.findViewById<Spinner>(R.id.wagon_type)
        val imageView = view?.findViewById<ImageView>(R.id.image)
        val updateButton = view?.findViewById<Button>(R.id.buttonSave)
        val deleteButton = view?.findViewById<Button>(R.id.buttonDelete)
        val qrButton = view?.findViewById<Button>(R.id.buttonQr)

        wtvm.wagon_types.observe(viewLifecycleOwner) {
            val strings = ArrayList<String>()
            for (wT in it) {
                strings.add(wT.name)
            }

            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, strings)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            wagonType?.setAdapter(adapter)
        }

        vm.wagon.observe(viewLifecycleOwner) {
            wagon = Wagon(it.id, it.wagon_type, it.train_id)

            wagonTrain?.setText(wagon.train_id.toString())
//            wagonType?.setText(wagon.wagon_type.toString())

            if (wagon.wagon_type == 5) {
                qrButton?.visibility = View.VISIBLE
                qrButton?.setOnClickListener{
                    createAndOpenPdf(requireContext(), "Wagon: ${wagon.id}, Train: ${wagon.train_id}",
                        "ticket_wagon${wagon.id}_train${wagon.train_id}")
                }
            }

            when (wagon.wagon_type) {
                1 -> imageView?.setImageResource(R.drawable.wagon_grain)
                5 -> imageView?.setImageResource(R.drawable.wagon_people)
                6 -> imageView?.setImageResource(R.drawable.wagon_cattle)
                7, 8 -> imageView?.setImageResource(R.drawable.wagon_oil)
                else -> imageView?.setImageResource(R.drawable.wagon_rzd)
            }
        }

        updateButton?.setOnClickListener {
            val wagon = Wagon(wagonId, wagonType?.selectedItem.toString().length, wagonTrain?.text.toString().toInt())
            vm.updateWagon(wagonId, wagon)

            requireFragmentManager().beginTransaction().apply {
                replace(R.id.flFragment, WagonsFragment.newInstance())
                commit()
            }
        }

        deleteButton?.setOnClickListener{
            vm.deleteWagon(wagonId)

            requireFragmentManager().beginTransaction().apply {
                replace(R.id.flFragment, WagonsFragment.newInstance())
                commit()
            }
        }

        val toolbar = view?.findViewById<Toolbar>(R.id.toolbar)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar?.setNavigationOnClickListener {
            Log.e("toolbar", "click")
            requireFragmentManager().beginTransaction().apply {
                replace(R.id.flFragment, WagonsFragment.newInstance())
                commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) = WagonEditFragment().apply {
            arguments = Bundle().apply {
                putInt(WAGON_ID, id)
            }
        }
    }
}