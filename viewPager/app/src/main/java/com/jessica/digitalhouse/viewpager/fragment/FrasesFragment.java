package com.jessica.digitalhouse.viewpager.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jessica.digitalhouse.viewpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrasesFragment extends Fragment {


    public FrasesFragment() {
        // Required empty public constructor
    }

    public static FrasesFragment newInstance(String frase){
        FrasesFragment fragment = new FrasesFragment();

        Bundle bundle = new Bundle();
        bundle.putString("FRASE", frase);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frases, container, false);

        TextView txtFrases = view.findViewById(R.id.txt_frases);

        txtFrases.setText(getArguments().getString("FRASE"));

        return view;
    }

}
