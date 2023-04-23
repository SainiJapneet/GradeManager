package com.example.grademanagement


import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.cardview.widget.CardView
import java.util.*

class Grademanagement : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grademanagement)



        var btnSyllabus = findViewById<Button>(R.id.btnResult_4)
        btnSyllabus.setOnClickListener {
            var intent= Intent(this, SyllabusActivity::class.java)
            startActivity(intent)
        }



        var dateselected = findViewById<ImageButton>(R.id.calenderbut)
        dateselected.setOnClickListener(){
            val c = Calendar.getInstance()
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(

                this,
                { view, year, monthOfYear, dayOfMonth ->

                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)

                },

                year,
                month,
                day
            )
            datePickerDialog.datePicker.minDate = c.timeInMillis
            datePickerDialog.show()
        }

        val  result = findViewById<Button>(R.id.btnResult_2)
        result.setOnClickListener(){
            val intentresult =Intent(this, GradeManagement_Result1::class.java)
            startActivity(intentresult)
        }

        val esign = findViewById<Button>(R.id.esign)
        esign.setOnClickListener(){
            val intentesign = Intent(this , Drawing::class.java)
            startActivity(intentesign)
        }

        val searching = findViewById<EditText>(R.id.search1)
        val searchbutton = findViewById<ImageButton>(R.id.searchbtn)

        searchbutton.setOnClickListener(){
            var searchtext = searching.text.toString().trim()

            val coursecard = findViewById<CardView>(R.id.coursecardview)
            val resultcard = findViewById<CardView>(R.id.resultcardview)
            val syllabuscard =findViewById<CardView>(R.id.syllabuscardview)
            val timetablecard = findViewById<CardView>(R.id.timetablecardview)
            val examcard = findViewById<CardView>(R.id.examcardview)

            if(searchtext.isEmpty()){
                Toast.makeText(this, "Nothing written" ,Toast.LENGTH_LONG).show()
            }
            else if (searchtext == "course"){
                resultcard.visibility= View.INVISIBLE
                syllabuscard.visibility= View.INVISIBLE
                timetablecard.visibility= View.INVISIBLE
                examcard.visibility= View.INVISIBLE

            }
            else if (searchtext == "timetable"){
                resultcard.visibility= View.INVISIBLE
                syllabuscard.visibility= View.INVISIBLE

                examcard.visibility= View.INVISIBLE
                coursecard.visibility= View.INVISIBLE
            }
            else if(searchtext == "results"){

                syllabuscard.visibility= View.INVISIBLE
                timetablecard.visibility= View.INVISIBLE
                examcard.visibility= View.INVISIBLE
                coursecard.visibility= View.INVISIBLE
            }
            else if(searchtext == "exams"){
                resultcard.visibility= View.INVISIBLE
                syllabuscard.visibility= View.INVISIBLE
                timetablecard.visibility= View.INVISIBLE

                coursecard.visibility= View.INVISIBLE
            }
            else if(searchtext == "syllabus"){
                resultcard.visibility= View.INVISIBLE

                timetablecard.visibility= View.INVISIBLE
                examcard.visibility= View.INVISIBLE
                coursecard.visibility= View.INVISIBLE
            }

            else {
                Toast.makeText(this , "Enter something genuine" , Toast.LENGTH_SHORT).show()
            }

        }


    }
}