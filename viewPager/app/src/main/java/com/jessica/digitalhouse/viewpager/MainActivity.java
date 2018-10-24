package com.jessica.digitalhouse.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jessica.digitalhouse.viewpager.adapter.FrasesAdapter;
import com.jessica.digitalhouse.viewpager.fragment.FrasesFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);

        FrasesAdapter adapter = new FrasesAdapter(getSupportFragmentManager(), getListFragments());
        viewPager.setAdapter(adapter);
    }

    private List<Fragment> getListFragments() {
        List<Fragment> list = new ArrayList<>();

        list.add(FrasesFragment.newInstance("Lorem ipsum dolor sit amet"));
        list.add(FrasesFragment.newInstance("consectetur adipiscing elit"));
        list.add(FrasesFragment.newInstance("Cras eget nisl eget enim aliquam vestibulum at in nisl"));
        list.add(FrasesFragment.newInstance("Vivamus tempus leo vel ante fringilla"));
        list.add(FrasesFragment.newInstance("sit amet aliquet massa eleifend"));
        list.add(FrasesFragment.newInstance("Suspendisse eu pharetra sapien"));
        list.add(FrasesFragment.newInstance("Phasellus tincidunt ante ac nisl congue"));

        return list;
    }
}
