package com.example.room_with_corotines_simplife_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room_with_corotines_simplife_task.databinding.ActivityUpdateBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    lateinit var currentName: String
    lateinit var currentPhone: String
    lateinit var db:ContactDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db=ContactDataBase.getInstance(this)
        var position = intent.getIntExtra(MainActivity.positionKey, 0)
        runBlocking(Dispatchers.IO) {
            currentName = db.contactDao.getContacts()[position].name
            currentPhone = db.contactDao.getContacts()[position].phone
            with(Dispatchers.Main) {
                binding.nameEditText.setText(currentName)
                binding.phoneEditText.setText(currentPhone)
            }

        }
        binding.updateBtn.setOnClickListener {
            runBlocking {
                db.contactDao.update(ContactDataModel(position, binding.nameEditText.text.toString(),binding.phoneEditText.text.toString()))

            }
            finish()
        }
        binding.deleteBtn.setOnClickListener {
            runBlocking {
               db.contactDao.delete(ContactDataModel(position, currentName,currentPhone))

            }
            finish()
        }


    }
}