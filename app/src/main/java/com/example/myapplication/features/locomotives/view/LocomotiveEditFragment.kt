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
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf


/**
 * A simple [Fragment] subclass.
 * Use the [TrainEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

const val LOCOMOTIVE_ID = "locomotive_id"

class LocomotiveEditFragment : Fragment() {
    private val vm = get<LocomotiveVM> { parametersOf("") }
    private var locomotiveId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            locomotiveId = it.getInt(LOCOMOTIVE_ID)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_locomotive_edit, container, false)
    }

    override fun onStart() {
        super.onStart()

        locomotiveId.let { vm.fetchLocomotive(it) }

        val locomotiveName = view?.findViewById<EditText>(R.id.locomotive_name)
        val locomotiveType = view?.findViewById<EditText>(R.id.locomotive_type)
        val imageView = view?.findViewById<ImageView>(R.id.image)
        val updateButton = view?.findViewById<Button>(R.id.buttonSave)
        val deleteButton = view?.findViewById<Button>(R.id.buttonDelete)

        vm.locomotive.observe(viewLifecycleOwner) {
            val train = Locomotive(it.id, it.name, it.loco_type)

            locomotiveName?.setText(train.name)
            locomotiveType?.setText(train.loco_type.toString())

            when(train.loco_type) {
                1, 2 -> imageView?.setImageResource(R.drawable.loco_old)
                9 -> imageView?.setImageResource(R.drawable.loco_cool)
                10 -> imageView?.setImageResource(R.drawable.loco_new)
                else -> imageView?.setImageResource(R.drawable.loco_rzd)
            }
        }

        updateButton?.setOnClickListener {
            val locomotive = Locomotive(locomotiveId, locomotiveName?.text.toString(), locomotiveType?.text.toString().toInt())
            vm.updateLocomotive(locomotiveId, locomotive)

            requireFragmentManager().beginTransaction().apply {
                replace(R.id.flFragment, LocomotiveFragment.newInstance())
                commit()
            }
        }

        deleteButton?.setOnClickListener{
            vm.deleteLocomotive(locomotiveId)

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
        fun newInstance(id: Int) = LocomotiveEditFragment().apply {
            arguments = Bundle().apply {
                putInt(LOCOMOTIVE_ID, id)
            }
        }
    }
}