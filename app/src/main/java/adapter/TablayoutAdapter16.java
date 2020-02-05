package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragment.Account;
import fragment.Calculator;
import fragment.Fund;
import fragment.Overview;
import fragment.Overview_16;
import fragment.Stock;

public class TablayoutAdapter16 extends FragmentStatePagerAdapter {

int mnooftabs;

    public TablayoutAdapter16(FragmentManager fm, int mnooftabs) {
        super(fm);
        this.mnooftabs = mnooftabs;
    }

    public TablayoutAdapter16(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Overview_16 tab1 = new Overview_16();
                return tab1;
            case 1:
                Account tab2 = new Account();
                return tab2;

            case 2:
                Calculator tab3 = new Calculator();
                return tab3;

            default:
                return null;


        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}


