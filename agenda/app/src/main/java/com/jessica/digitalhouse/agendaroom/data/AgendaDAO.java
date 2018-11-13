package com.jessica.digitalhouse.agendaroom.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jessica.digitalhouse.agendaroom.model.Agenda;

import java.util.List;

@Dao
public interface AgendaDAO {

    @Insert
    void inserirAgenda(Agenda agenda);

    @Update
    void atualizarAgenda(Agenda agenda);

    @Delete
    void deletarContato(Agenda agenda);

    @Query("SELECT * FROM agendaDeContato WHERE contato =:nome")
    public Agenda getNomeContato(String nome);

    @Query("SELECT * FROM agendaDeContato ORDER BY contato ASC")
    LiveData<List<Agenda>> todosContatos();
}
