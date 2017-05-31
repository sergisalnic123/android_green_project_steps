package com.example.sergisalnic.green_way_01;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class b2_Fragment_rutes extends Fragment {

    private AppBarLayout appBar;
    private TabLayout tabs;
    private ViewPager viewPager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.b2_fragment_rutes, container, false);
        View contenedor= (View)container.getParent();
        appBar=(AppBarLayout)contenedor.findViewById(R.id.appbar);
        tabs = new TabLayout(getActivity());
        tabs.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#FFFFFF"));
        appBar.addView(tabs);

        viewPager=(ViewPager)view.findViewById(R.id.pager);
        ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(viewPager);

        return view;
    }

    public void onDestroyView(){
        super.onDestroyView();;
        appBar.removeView(tabs);
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter{
        public ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        String[] tituloTabs={"RUTES", "INTERESTING SITES", "FOOD"};


        public Fragment getItem(int position){
            switch(position){
                case 0: return new b2_01Fragment_tab_rutas();
                case 1: return new b2_02_Fragment_tab_puntosi();
                case 2: return new b2_03_Fragment_tab_food();
            }
            return  null;
        }

        public int getCount(){
            return 3;
        }

        public CharSequence getPageTitle(int position){
            return tituloTabs[position];
        }
    }
}
