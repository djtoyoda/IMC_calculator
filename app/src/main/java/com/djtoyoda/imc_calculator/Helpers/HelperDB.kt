package com.djtoyoda.imc_calculator.Helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.djtoyoda.imc_calculator.application.DataIMC

class HelperDB(context: Context) : SQLiteOpenHelper(context, nameDB, null, version) {

    companion object {
        private val nameDB = "historicoIMC.db"
        private val version = 1
    }

    val TABLE_NAME = "historico"
    val COLUMNS_ID = "ID"
    val COLUMNS_DATA = "Data"
    val COLUMNS_PESO = "Peso"
    val COLUMNS_IMC = "IMC"

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMNS_ID INTEGER NOT NULL," +
                "$COLUMNS_DATA TEXT NOT NULL," +
                "$COLUMNS_PESO TEXT NOT NULL," +
                "$COLUMNS_IMC TEXT NOT NULL,"+
                " " +
                "PRIMARY KEY ($COLUMNS_ID AUTOINCREMENT)" +
                ")"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        if (oldVersion != newVersion) {
            db?.execSQL(DROP_TABLE)
        }
        onCreate(db)
    }

    //inserir dados à tabela
    fun adicionarIMC (dataHistorico : DataIMC) {
        val db = this.writableDatabase ?: return
        val INSERT_IMC = "INSERT INTO $TABLE_NAME ($COLUMNS_DATA, $COLUMNS_PESO, $COLUMNS_IMC) VALUES(?, ?, ?)"
        val array = arrayOf(dataHistorico.dataDB, dataHistorico.pesoDB, dataHistorico.imcDB)
        db.execSQL(INSERT_IMC, array)
        db.close()
    }

    //outra forma de inserir dados
        /*
    fun adicionarIMC (dataHistorico : DataIMC) {
        val db = this.writableDatabase
        //db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
       // onCreate(db)
        val valores = ContentValues()
        valores.put(COLUMNS_DATA, dataHistorico.dataID)
        valores.put(COLUMNS_PESO, dataHistorico.pesoDB)
        valores.put(COLUMNS_IMC, dataHistorico.imcDB)

        db.insert(TABLE_NAME, null, valores)
        db.close()
    }*/

    //apagar dado da tabela
    fun apagarLinhaHistorico (id: Int) {
        val db = this.writableDatabase ?: return
        db.execSQL("DELETE FROM $TABLE_NAME WHERE $COLUMNS_ID = $id")
        db.close()
    }

    fun lerHistorico(): ArrayList<DataIMC> {
        val listaIMC = ArrayList<DataIMC>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val dataIMC = DataIMC (
                    result.getInt(result.getColumnIndex(COLUMNS_ID)),
                    result.getString(result.getColumnIndex(COLUMNS_DATA)),
                    result.getString(result.getColumnIndex(COLUMNS_PESO)),
                    result.getString(result.getColumnIndex(COLUMNS_IMC)))
                listaIMC.add(dataIMC)
            }
            while (result.moveToNext())
        }
        db.close()
        return listaIMC
    }
}