package it.inefficienza.interactivebusinesscard.chs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author Christian Sarnataro
 */
public class ScrollableItemAdapter extends BaseAdapter {

    private Context context;
    private List<ScrollableItem> items;

    public ScrollableItemAdapter(Context context, List<ScrollableItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ScrollableItem getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).hashCode();
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        if (view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.work_list_row, null);
        }

        TextView year = (TextView) view.findViewById(R.id.year);
        TextView company = (TextView) view.findViewById(R.id.company);
        TextView role = (TextView) view.findViewById(R.id.role);

        final ScrollableItem scrollableItem = items.get(position);

        if (scrollableItem.getYear() == null) {
            year.setVisibility(View.GONE);
        } else {
            year.setVisibility(View.VISIBLE);
            year.setText(scrollableItem.getYear());
            // year.setTextColor(yearTextColor);
        }
        company.setText(scrollableItem.getCompany());
        role.setText(scrollableItem.getRole());

        /*
        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, scrollableItem.getYear(), Toast.LENGTH_SHORT).show();
            }
        });
        */

        return view;
    }
}
