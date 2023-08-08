package com.example.room_with_corotines_simplife_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.room_with_corotines_simplife_task.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.FieldPosition

class MainActivity : AppCompatActivity(), ContactAdapter.OnViewClick {
    lateinit var binding: ActivityMainBinding
    lateinit var list: List<ContactDataModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.v("MainTest","OnCreat")
        db=ContactDataBase.getInstance(this)

        runBlocking(Dispatchers.IO) {
            list=db.contactDao.getContacts()
            binding.contactRecyclerView.adapter = ContactAdapter(list, this@MainActivity)
        }
        binding.addContactBtn.setOnClickListener {
            var intent = Intent(this, AddContactActivity::class.java)
            intent.putExtra(theLatestIdKey, list.size)
            startActivity(intent)
        }





    }

    override fun onRestart() {
       super.onRestart()
        Log.v("MainTest","OnRestart")
        runBlocking {
            list= db.contactDao.getContacts()
        }
        binding.contactRecyclerView.adapter = ContactAdapter(list, this@MainActivity)

    }
    override fun onViewClick(position: Int) {
        var intent = Intent(this, UpdateActivity::class.java)
        intent.putExtra(positionKey, position)
        startActivity(intent)

    }

    companion object {
        const val positionKey = "positionKey"
        const val theLatestIdKey="theLatestIdKey"
        lateinit var db:ContactDataBase
//        const val currentNameKey="currentNameKey"
//        const val currentPhoneKey="currentPhoneKey"
    }
}
