package com.jessica.digitalhouse.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jessica.digitalhouse.recyclerview.adapter.RecyclerViewAdapter;
import com.jessica.digitalhouse.recyclerview.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getListPessoas());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private List<Pessoa> getListPessoas() {
        List<Pessoa> list = new ArrayList<>();

        list.add(new Pessoa("Jessica", "22"));
        list.add(new Pessoa("Jessica", "22"));
        list.add(new Pessoa("Jessica", "22"));
        list.add(new Pessoa("Jessica", "22"));
        list.add(new Pessoa("Jessica", "22"));
        list.add(new Pessoa("Jessica", "22"));
        list.add(new Pessoa("Jessica", "22"));
        list.add(new Pessoa("Jessica", "22"));
        list.add(new Pessoa("Jessica", "22"));
        list.add(new Pessoa("Jessica", "22"));
        list.add(new Pessoa("Jessica", "22"));

        return list;
    }
}
