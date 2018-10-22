package com.jessica.digitalhouse.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.jessica.digitalhouse.fragments.fragments.InfoDoisFragment;
import com.jessica.digitalhouse.fragments.fragments.InfoUmFragment;
import com.jessica.digitalhouse.fragments.help.Comunicador;

public class MainActivity extends AppCompatActivity implements Comunicador{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(new InfoUmFragment());
    }

    //implementar o método de replaceFragment
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack("fragmento");
        transaction.commit();
    }

    @Override
    public void receber(String mensagem) {

        Fragment fragment = InfoDoisFragment.newInstance(mensagem);

        replaceFragment(fragment);

    }
}
