package it.inefficienza.interactivebusinesscard.chs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author Christian Sarnataro
 */
public class SearchResultAdapter extends ArrayAdapter<SearchResult> {

    Context context;
    List<SearchResult> searchResults = null;
    int layoutResourceId;

    public SearchResultAdapter(Context context, int layoutResourceId, List<SearchResult> searchResults) {
        super(context, layoutResourceId, searchResults);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.searchResults = searchResults;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SearchResultHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new SearchResultHolder();
            holder.matchingTextView = (TextView)row.findViewById(R.id.matching_text);
            holder.gotoArrow = (ImageView) row.findViewById(R.id.goto_arrow);
            row.setTag(holder);
        }
        else
        {
            holder = (SearchResultHolder)row.getTag();
        }

        SearchResult searchResult = searchResults.get(position);
        holder.matchingTextView.setText(searchResult.getMatchingText());

        if (searchResult.getPage() == -1) {
            holder.gotoArrow.setVisibility(View.INVISIBLE);
        } else {
            holder.gotoArrow.setVisibility(View.VISIBLE);
        }

        return row;
    }

    static class SearchResultHolder
    {
        TextView matchingTextView;
        ImageView gotoArrow;
    }
}
