package com.asyfdzaky.pppb_crud.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DataPemilihDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(datapemilih: DataPemilih)

    @Update
    fun update(datapemilih: DataPemilih)

    @Delete
    fun delete(datapemilih: DataPemilih)

    @Query("SELECT * from dataPemilih_table ORDER BY id ASC")
    fun getAllDataPemilih(): LiveData<List<DataPemilih>>
}
