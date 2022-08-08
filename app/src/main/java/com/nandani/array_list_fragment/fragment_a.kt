package com.nandani.array_list_fragment

import android.R
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.nandani.array_list_fragment.databinding.CustomLayout2Binding
import com.nandani.array_list_fragment.databinding.FragmentABinding
import com.nandani.array_list_fragment.databinding.CustomLayoutBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_a.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_a : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentABinding
    var arrayList: ArrayList<String> = ArrayList()
    lateinit var listActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        listActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentABinding.inflate(layoutInflater)
        var adapter = ArrayAdapter(listActivity, R.layout.simple_list_item_1, arrayList)
        arrayList.add("qwerty")
        arrayList.add("qwerty 1")
        arrayList.add("qwerty 2")
        arrayList.add("qwerty 3")
        arrayList.add("qwerty 4")
        binding.listview.adapter = adapter

        binding.fabbtn.setOnClickListener {

            var dialogBinding = CustomLayout2Binding.inflate(layoutInflater)
            var dialog = Dialog(listActivity)
            dialog.setCancelable(false)
            dialog.setContentView(dialogBinding.root)
            val layout = dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialogBinding.ok.setOnClickListener {
                if (dialogBinding.etadditem.text.toString().isNullOrEmpty()) {
                    dialogBinding.etadditem.setError("Please Add Item")
                } else {
                    arrayList.add(dialogBinding.etadditem.text.toString())
                    dialog.dismiss()
                }
            }
            dialog.show()
        }

        binding.listview.setOnItemClickListener { adapterView, view, i, l ->

            var dialogBinding = CustomLayoutBinding.inflate(layoutInflater)
            var dialog = Dialog(listActivity)
            dialog.setCancelable(false)
            dialog.setContentView(dialogBinding.root)
            val layout = dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialogBinding.updateitem.setText(arrayList[i])

            dialogBinding.update.setOnClickListener {
                if (dialogBinding.updateitem.text.toString().isNullOrEmpty()) {
                    dialogBinding.updateitem.setError("Please Update Item")
                }
                else {
                    arrayList.set(i ,dialogBinding.updateitem.text.toString())
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ArrayListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_a().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}