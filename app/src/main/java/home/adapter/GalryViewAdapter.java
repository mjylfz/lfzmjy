package home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by LFZ on 2017/11/6.
 * viewPagerAdapteralbum
 */

public class GalryViewAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    public GalryViewAdapter(FragmentManager fm) {
        super(fm);
    }

    public GalryViewAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        this(fm);
        this.fragmentList = fragmentList;
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
