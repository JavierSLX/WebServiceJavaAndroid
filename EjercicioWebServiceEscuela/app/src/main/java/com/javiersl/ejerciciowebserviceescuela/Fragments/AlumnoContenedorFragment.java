package com.javiersl.ejerciciowebserviceescuela.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.javiersl.ejerciciowebserviceescuela.Adapters.TabPagerAdapter;
import com.javiersl.ejerciciowebserviceescuela.R;

/**
 * Created by JavierSL on 10/04/2018.
 */

public class AlumnoContenedorFragment extends Fragment
{
    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        appBarLayout = ((View)container.getParent()).findViewById(R.id.appBar);
        return inflater.inflate(R.layout.fragment_contenedor, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = new TabLayout(getContext());
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBarLayout.addView(tabLayout);

        adapter = new TabPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager = view.findViewById(R.id.viewPager);

        adapter.addFragment(new AlumnoListarFragment(), "Listas");
        adapter.addFragment(new AlumnoInsertarFragment(), "Insertar");
        adapter.addFragment(new AlumnoBuscarFragment(), "Buscar");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        appBarLayout.removeView(tabLayout);
    }
}
