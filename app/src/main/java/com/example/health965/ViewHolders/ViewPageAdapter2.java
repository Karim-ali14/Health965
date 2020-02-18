package com.example.health965.ViewHolders;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

    public class ViewPageAdapter2 extends FragmentPagerAdapter {
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> fragmentTitle= new ArrayList<>();
    public ViewPageAdapter2(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public void addFaragment(Fragment fragment,String Title){
        fragmentList.add(fragment);
        fragmentTitle.add(Title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       return fragmentTitle.get(position);
    }
}
