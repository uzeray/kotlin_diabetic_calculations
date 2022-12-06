package www.fitpatients.com.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_diabetic_indexes.*

class DiabeticIndexes() : AppCompatActivity() {

    private var gender = 0
    // gender of user will start wiht 0 and when clicked an gender value will change


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diabetic_indexes)

    }

    fun getGender(view: View){ // will check to gender preference

        if (view is RadioButton){ // if radioButoon clicked now

            var genderVal = view.isChecked // gender will assign in this variable

            when(view.getId()){ // will check variable value

                R.id.female -> // if variable femle cliced gender value will be 1
                    if (genderVal){
                        gender = 1
                        println(gender)
                    }

                R.id.male -> // if gender male has clicked gender value will be 2
                    if (genderVal){
                        gender = 2
                        println(gender)
                    }


            }

        }

    }


    fun calculateDiabetic (view: View){

        var ageVal : Int? = ageTextDiabetic.text.toString().toIntOrNull()
            // age value will scan from textView it name is ageTextDiabetic
        var weightVal : Int? = weightTextDiabetic.text.toString().toIntOrNull()
            //weight variable will scan from weightTextDiabetic textView
        var glucoseVal : Double? = glucoseValText.text.toString().toDoubleOrNull()
            // glucose value is fasting glucose value of user, will scan from textView glucoseValText
        var insulinVal : Double? = insulinValText.text.toString().toDoubleOrNull()
            // insulinVal is will scan from textView insulinValText, that is fasting insulin value

        if (ageVal == null || weightVal == null || glucoseVal == null || insulinVal == null){
           // if any of field left empty by user that block will work
           Toast.makeText(applicationContext, "All Fields Must Fill", Toast.LENGTH_LONG).show()
       }

        else if (gender == 0 || ageVal < 10 || weightVal < 30 || glucoseVal < 2.5 || glucoseVal > 20.0 || insulinVal > 20.0){
         // if any value is entered under or upper than real values by user this block will work
            Toast.makeText(applicationContext,
             "Click an Gender, Age >10, Weight >30, Fasting Glucose >2.5 - <20," +
                     " Fasting Insulin <20 !!!", Toast.LENGTH_LONG).show()
        }


        else {  // if every field has filled correctly that block will work

            val totalBolus = 0.55 * weightVal!!
                // assigment for daily total bolus/rapid insulin dose calculation
            val totalBasal = totalBolus / 2.0
                // assigment for daily basal/long acting insulin dose calculation

           val correctionDose = (1800 / totalBolus) / 18.02
                // assigment for 1 UI/dl insulin how much mmol/dl glucose will effect
           val carbCover = 500 / totalBolus
                // assigment for 1 UI/dl insulin how many of carbohydirate will cover

           val glucoseValMg = glucoseVal!! * 18.02
                // fasting glucose has transform as mg/dl for homaIr calculation
           val homaIr = glucoseValMg * insulinVal!! / 405.0
                // assigment is calculate to Insulin resistance


            // all variables will transform 2 desimal point double variables
           val homaIr2 = Math.round(homaIr * 100.0) / 100.0
           val carbCover2 = Math.round(carbCover * 100.0) / 100.0
           val correctionDose2 = Math.round(correctionDose * 100.0) / 100.0
           val totalBolus2 = Math.round(totalBolus * 100.0) / 100.0
           val totalBasal2 = Math.round(totalBasal * 100.0) / 100.0


            // all variables has recharged an INTENT object
           val pick_upDiabetic = Intent(applicationContext, DiabeticIndexesResult::class.java)
           pick_upDiabetic.putExtra("homaIr", homaIr2)
           pick_upDiabetic.putExtra("carbCover", carbCover2)
           pick_upDiabetic.putExtra("correctionDose", correctionDose2)
           pick_upDiabetic.putExtra("totalBolus", totalBolus2)
           pick_upDiabetic.putExtra("totalBasal", totalBasal2)
           startActivity(pick_upDiabetic)


        }



    }


}