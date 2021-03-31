package com.djtoyoda.imc_calculator.Helpers

import android.content.Context
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.djtoyoda.imc_calculator.application.DataIMC

class HelperDB(
        context: Context
) : SQLiteOpenHelper(context, nameDB, null, version) {

    companion object {
        private val nameDB = "historicoIMC.db"
        private val version = 1
    }

    val TABLE_NAME = "historico"
    val COLUMNS_DATA = "Data"
    val COLUMNS_PESO = "Peso"
    val COLUMNS_IMC = "IMC"

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMNS_DATA DATE NOT NULL," +
                "$COLUMNS_PESO DOUBLE NOT NULL," +
                "$COLUMNS_IMC DOUBLE NOT NULL,"+
                " " +
                "PRIMARY KEY($COLUMNS_DATA)"+
                ")"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        if (oldVersion != newVersion) {
            db?.execSQL(DROP_TABLE)
            onCreate(db)
        }
    }

    fun adicionarIMC (dataHistorico : DataIMC) {
        val valores = ContentValues()
        valores.put(COLUMNS_DATA, dataHistorico.dataID)
        valores.put(COLUMNS_PESO, dataHistorico.pesoDB)
        valores.put(COLUMNS_IMC, dataHistorico.imcDB)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, valores)
        db.close()
    }

    fun lerHistorico(): MutableList<DataIMC> {
        val list: MutableList<DataIMC> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val dataID = result.getString(result.getColumnIndex(COLUMNS_DATA))
                val pesoDB = result.getString(result.getColumnIndex(COLUMNS_PESO))
                val imcDB = result.getString(result.getColumnIndex(COLUMNS_IMC))
                val data = DataIMC(dataID, pesoDB, imcDB)
                list.add(data)
                println("${data.dataID} ${data.imcDB} ${data.pesoDB}")
            }
            while (result.moveToNext())
        }
        return list
    }
}