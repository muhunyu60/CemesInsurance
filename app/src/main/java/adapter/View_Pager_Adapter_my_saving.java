package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragment.Viewpager3;

public class View_Pager_Adapter_my_saving extends FragmentStatePagerAdapter {
    public View_Pager_Adapter_my_saving(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                Viewpager3 tab1 = new Viewpager3();
                return tab1;
            case 1:
                Viewpager3 tab2 = new Viewpager3();
                return  tab2;
            case 2:
                Viewpager3 tab3 = new Viewpager3();
                return tab3;
            case 3:
                Viewpager3 tab4 = new Viewpager3();
                return tab4;


                default:
                    return null;

        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
