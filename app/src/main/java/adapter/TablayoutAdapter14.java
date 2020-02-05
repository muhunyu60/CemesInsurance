package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragment.Calculator;
import fragment.Calculator_14;
import fragment.Fund;
import fragment.Overview;
import fragment.Overview_14;
import fragment.Stock;

public class TablayoutAdapter14 extends FragmentStatePagerAdapter {

int mnooftabs;

    public TablayoutAdapter14(FragmentManager fm, int mnooftabs) {
        super(fm);
        this.mnooftabs = mnooftabs;
    }

    public TablayoutAdapter14(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Overview_14 tab1 = new Overview_14();
                return tab1;
            case 1:
                Calculator_14 tab2 = new Calculator_14();
                return tab2;


            default:
                return null;





        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}


