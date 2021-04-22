package com.djtoyoda.imc_calculator.application

import java.io.Serializable

class DataIMC : Serializable {
    var idDB: Int = 0
    var dataDB: String = "0"
    var pesoDB: String = "0"
    var imcDB: String = "0"

    constructor(idDB: Int, dataDB: String, pesoDB: String, imcDB: String) {
        this.idDB = idDB
        this.dataDB = dataDB
        this.pesoDB = pesoDB
        this.imcDB = imcDB
    }
}