package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragment.Fragment_Login;
import fragment.Viewpager4;
import fragment.Viewpager5;
import fragment.Viewpager6;
import fragment.Viewpager7;

public class View_Pager_Adapter_gift extends FragmentStatePagerAdapter {
    public View_Pager_Adapter_gift(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                Viewpager4 tab1 = new Viewpager4();
                return tab1;
            case 1:
                Viewpager5 tab2 = new Viewpager5();
                return  tab2;
            case 2:
                Viewpager6 tab3 = new Viewpager6();
                return  tab3;
            case 3:
                Viewpager7 tab4 = new Viewpager7();
                return  tab4;





            default:
                    return null;

        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
