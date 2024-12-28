package com.karan.apiintegration

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karan.apiintegration.databinding.ActivityMainBinding
import com.karan.apiintegration.databinding.CustomDialogboxBinding

class MainActivity : AppCompatActivity(), Recycler_btn {
    lateinit var binding: ActivityMainBinding
    lateinit var vMclass: VMclass
    var array = ArrayList<ResponseModel.Data>()
    var recyclerAdapter = recycler_Adapter(array, this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
        vMclass.getData()
        var linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerList.layoutManager = linearLayoutManager
        binding.recyclerList.adapter = recyclerAdapter
        binding.recyclerList.setHasFixedSize(true)

        recyclerAdapter.notifyDataSetChanged()
//        binding.btnFab.setOnClickListener {
//            var dialogboxBinding = CustomDialogboxBinding.inflate(layoutInflater)
//            Dialog(this).apply {
//                setContentView(dialogboxBinding.root)
//                window?.setLayout(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//                )
//
//
//                    if (dialogboxBinding.etEmail.text.toString().trim().isNullOrEmpty()) {
//                        dialogboxBinding.etEmail.error = "Enter the Email"
//                    } else if (dialogboxBinding.etFirstName.text.toString().trim().isNullOrEmpty()
//                    ) {
//                        dialogboxBinding.etFirstName.error = "Enter the First name"
//                    } else if (dialogboxBinding.etLastName.text.toString().trim().isNullOrEmpty()) {
//                        dialogboxBinding.etLastName.error = "Enter the Last name"
//                    } else {
//                        var responseModel = ResponseModel.Data(
//
//                            dialogboxBinding.etEmail.text.toString(),
//                            dialogboxBinding.etFirstName.text.toString(),
//                            dialogboxBinding.etLastName.text.toString()
//
//                        )
//                        array.add(responseModel)
//                        recyclerAdapter.notifyDataSetChanged()
//                        dismiss()
//                    }
//
//                }
//                show()
//            }
//        }
    }

    private fun initViews() {
        vMclass = ViewModelProvider(this)[VMclass::class.java]
        vMclass.getRes.observe(this)
        {
            when (it) {
                is Sealedclass.Error -> {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }

                is Sealedclass.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }

                is Sealedclass.Success -> {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    array.clear()
                    it.data?.data?.forEach {
                        array.add(it!!)
                    }
                    recyclerAdapter.notifyDataSetChanged()
                }

                null -> {
                    Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun btn_del(position: Int) {
        var alertDialog = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Delete Item")
        alertDialog.setMessage("Do you want to delete the item?")
        alertDialog.setCancelable(false)
        alertDialog.setNegativeButton("No") { _, _ ->
            alertDialog.setCancelable(true)
        }
        alertDialog.setPositiveButton("Yes") { _, _ ->

            array.removeAt(position)
            recyclerAdapter.notifyDataSetChanged()
        }

        alertDialog.show()
    }

    override fun btn_update(position: Int) {
        val dialogboxBinding = CustomDialogboxBinding.inflate(layoutInflater)
        val update_dialog = Dialog(this).apply {
            setContentView(dialogboxBinding.root)
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            show()
        }
        val old_name: String = array[position].emailRM.toString()
        val old_class: String = array[position].first_name.toString()
        val old_number: String = array[position].last_name.toString()
        var dialog = dialogboxBinding
        dialog.etEmail.setText(old_name)
        dialog.etFirstName.setText(old_class)
        dialog.etLastName.setText(old_number)

        dialog.btnSave.setOnClickListener {
            val updated_itesms = ResponseModel.Data(

                dialogboxBinding.etEmail.text.toString(),
                dialogboxBinding.etFirstName.text.toString(),
                dialogboxBinding.etLastName.text.toString()
            )
            array.add(updated_itesms)
            recyclerAdapter.notifyDataSetChanged()
            update_dialog.dismiss()
        }
    }
}