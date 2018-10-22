package com.jessica.digitalhouse.fragments.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jessica.digitalhouse.fragments.R;
import com.jessica.digitalhouse.fragments.help.Comunicador;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoUmFragment extends Fragment {

    private Comunicador listener;

    public InfoUmFragment() {
        // Required empty public constructor
    }

    //Sobrescrevendo o método onAttach
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Comunicador) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_um, container, false);

        Button btnEnviar = view.findViewById(R.id.btn_enviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.receber("Passando informação do fragmento 1 para o 2");
            }
        });

        return view;
    }

}
