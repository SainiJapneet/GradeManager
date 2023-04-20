package com.example.grademanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class GrademanagementCourses3 : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grademanagement_courses3)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.percentage -> Toast.makeText(this,"Percentage",Toast.LENGTH_SHORT).show()
            R.id.cgps -> Toast.makeText(this,"Cgpa",Toast.LENGTH_SHORT).show()
            R.id.grades -> Toast.makeText(this,"Grades",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }



}