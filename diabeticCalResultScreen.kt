package www.fitpatients.com.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_diabetic_indexes_result.*

class DiabeticIndexesResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diabetic_indexes_result)


        // get objects by Intent from DiabeticIndexes activity
        val totalBolus = intent.getDoubleExtra("totalBolus", 0.0)
        val totalBasal = intent.getDoubleExtra("totalBasal", 0.0)
        val homaIr = intent.getDoubleExtra("homaIr", 0.0)
        val carbCover = intent.getDoubleExtra("carbCover", 0.0)
        val correctionVal = intent.getDoubleExtra("correctionDose", 0.0)

        // whrite all variables over textViews
        bolusText.text = totalBolus.toString()
        basalText.text = totalBasal.toString()
        homaIrText.text = homaIr.toString()
        carbCoverText.text = carbCover.toString()
        correctionValText.text = correctionVal.toString()


    }

    fun goHomeDiabetic(view: View){
        val goHomeDiabetics = Intent(applicationContext, MainActivity::class.java)
        startActivity(goHomeDiabetics)
    }




}