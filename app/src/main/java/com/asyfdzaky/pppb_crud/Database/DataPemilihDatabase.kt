package com.asyfdzaky.pppb_crud.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataPemilih::class], version = 1, exportSchema = false)
abstract class DataPemilihDatabase : RoomDatabase() {
    abstract fun dataPemilihDao(): DataPemilihDao

    companion object {
        @Volatile
        private var INSTANCE: DataPemilihDatabase? = null
        fun getDatabase(context: Context): DataPemilihDatabase? {
            if (INSTANCE == null) {
                synchronized(DataPemilihDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataPemilihDatabase::class.java,
                        "data_pemilih_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }


}