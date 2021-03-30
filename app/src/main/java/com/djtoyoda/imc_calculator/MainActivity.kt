package com.djtoyoda.imc_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity {

    btCalcular.setOnClickListener(new View.OnClickListener() {
        var peso = etPeso.getText().toString()
        var altura= etAltura.getText().toString()
        var imc = peso / altura / altura

    }
}