package com.javiersl.ejerciciowebserviceescuela.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JavierSL on 10/04/2018.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter
{
    private List<Fragment> fragments;
    private List<String> titulos;
    private FragmentManager fragmentManager;

    public TabPagerAdapter(FragmentManager fm)
    {
        super(fm);
        fragmentManager = fm;
        fragments = new ArrayList<>();
        titulos = new ArrayList<>();
    }

    public void agregarFragmentos(List<Fragment> fragments, List<String> titulos)
    {
        this.fragments = fragments;
        this.titulos = titulos;
    }

    public void addFragment(Fragment fragment, String titulo)
    {
        this.fragments.add(fragment);
        this.titulos.add(titulo);
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titulos.get(position);
    }
}
