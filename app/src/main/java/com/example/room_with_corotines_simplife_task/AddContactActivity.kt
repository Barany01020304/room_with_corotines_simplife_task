package com.example.room_with_corotines_simplife_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room_with_corotines_simplife_task.databinding.ActivityAddContactBinding
import kotlinx.coroutines.runBlocking

class AddContactActivity : AppCompatActivity() {
    lateinit var binding:ActivityAddContactBinding
    lateinit var db:ContactDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db=ContactDataBase.getInstance(this)
        var position=intent.getIntExtra(MainActivity.theLatestIdKey,0)
        var name=binding.nameEditText.text.toString()
        var phone=binding.phoneEditText.text.toString()
        binding.addBtn.setOnClickListener {

            runBlocking {
                db.contactDao.insertContact(ContactDataModel(position,binding.nameEditText.text.toString(),binding.phoneEditText.text.toString()))
            }
            finish()
        }
    }
}