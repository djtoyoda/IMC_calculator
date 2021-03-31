package com.djtoyoda.imc_calculator.application

class DataIMC {
    var dataID: String = "0"
    var pesoDB: String = "0"
    var imcDB: String = "0"

    constructor(dataID: String, pesoDB: String, imcDB: String) {
        this.dataID = dataID
        this.pesoDB = pesoDB
        this.imcDB = imcDB
    }
}