package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragment.Fragment_Login;
import fragment.Viewpager1;

public class View_Pager_Adapter_charity extends FragmentStatePagerAdapter {
    public View_Pager_Adapter_charity(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                Viewpager1 tab1 = new Viewpager1();
                return tab1;
            case 1:
                Viewpager1 tab2 = new Viewpager1();
                return  tab2;
            case 2:
                Viewpager1 tab3 = new Viewpager1();
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
