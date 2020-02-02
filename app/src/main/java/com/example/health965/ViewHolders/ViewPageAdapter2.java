package com.example.health965.ViewHolders;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter2 extends FragmentPagerAdapter {
    List<Fragment> fragmentList = new ArrayList<>();
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
    public void addFaragment(Fragment fragment){
        fragmentList.add(fragment);
    }
}
