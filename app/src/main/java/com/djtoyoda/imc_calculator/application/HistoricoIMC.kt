package com.djtoyoda.imc_calculator.application

class DataIMC {
    var dataID: String? = null
    var pesoDB: String? = null
    var imcDB: String? = null

    constructor(dataID: String, pesoDB: String, imcDB: String) {
        this.dataID = dataID
        this.pesoDB = pesoDB
        this.imcDB = imcDB
    }
}