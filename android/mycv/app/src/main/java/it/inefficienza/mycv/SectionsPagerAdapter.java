package it.inefficienza.mycv;

/**
 * @author Christian Sarnataro
 */

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static it.inefficienza.mycv.Data.*;

/**
 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Map<String, Object>> data;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
        data = Data.getData();
    }


    @Override
    public PlaceholderFragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        PlaceholderFragment pf = null;
        if (createdFragments.get(position) == null) {
            pf = PlaceholderFragment.newInstance(position + 1);
            createdFragments.put(position, pf);
        }
        return createdFragments.get(position);

    }

    public SparseArray<PlaceholderFragment> getCreatedFragments() {
        return createdFragments;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return data.size();
    }

    @Override
    public String getPageTitle(int position) {
        return (String) data.get(position).get(TITLE);
    }

    public Integer getIcon(int position) {
        return (Integer) data.get(position).get(ICON);
    }

    public String getText(int position) {
        return (String) data.get(position).get(TEXT);
    }

//        public Spanned getScrollableText(int position) {
//            return (Spanned) data.get(position).get(SCROLLABLE_TEXT);
//        }

    public List<ScrollableItem> getWorkItems(int position) {
        return (List<ScrollableItem>) data.get(position).get(SCROLLABLE_TEXT);
    }


    SparseArray<PlaceholderFragment> createdFragments = new SparseArray<PlaceholderFragment>();

}

