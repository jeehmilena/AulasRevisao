package com.jessica.digitalhouse.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jessica.digitalhouse.model.Result;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Result result);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Result> results);

    @Update
    void update(Result result);

    @Delete
    void delete(Result result);

    @Query("Select * from movie limit 30")
    Flowable<List<Result>> getAll(); //Flowable é um observable que podemos usar com o RoomDataBase


}
