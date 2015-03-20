package it.inefficienza.mycv;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by <a href="mailto:christian.sarnataro@vimnmix.com">Christian Sarnataro</a> on 16/03/15.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    SectionsPagerAdapter adapter = null;

    ImageView searchBoxIcon;
    ImageView closeSearchBoxIcon;

    TextView title;

    View searchBoxContainer;

    EditText searchBox;


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    private void showSearchBoxContainer() {
        searchBoxContainer.setVisibility(View.VISIBLE);
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.9f,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0);

        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                searchBox.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(searchBox, InputMethodManager.SHOW_IMPLICIT);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        searchBoxContainer.startAnimation(animation);


//        ResizeAnimation ra = new ResizeAnimation(searchBoxContainer, 240);
//        ra.setDuration(1000);
//        searchBoxContainer.startAnimation(ra);
    }

    private void hideSearchBoxContainer() {

        AnimationSet set = new AnimationSet(true);

        // searchBoxContainer.setVisibility(View.VISIBLE);
        Animation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0.89f,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0);

        translateAnimation.setDuration(500);

        Animation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(200);
        fadeOut.setStartOffset(400);

        set.addAnimation(translateAnimation);
        set.addAnimation(fadeOut);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                searchBoxContainer.setVisibility(View.GONE);
                searchBox.setText("");

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        searchBoxContainer.startAnimation(set);

        Animation titleFadeIn = new AlphaAnimation(0.0f, 1f);
        titleFadeIn.setFillBefore(true);
        titleFadeIn.setFillAfter(true);
        titleFadeIn.setDuration(500);
        titleFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                title.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        title.startAnimation(titleFadeIn);


        // searchBoxContainer.startAnimation(fadeOut);


        // searchBoxContainer.setLayoutParams(new RelativeLayout.LayoutParams(0, RelativeLayout.LayoutParams.WRAP_CONTENT));
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Bundle args = getArguments();
        int position = getArguments().getInt(ARG_SECTION_NUMBER) -1;

        if (adapter == null) {
            adapter = ((MainActivity) getActivity()).getSectionsPagerAdapter();
        }
        String pageTitle = adapter.getPageTitle(position) ;
        // int bgColor = adapter.getBgColor(position);
        String text = adapter.getText(position);
        List<ScrollableItem> items = adapter.getWorkItems(position);
        // Spanned scrollableText = adapter.getScrollableText(position);

        // TITLE
        title = ((TextView) rootView.findViewById(R.id.section_label));

        title.setText(pageTitle);

        // rootView.setTag(position);

        searchBoxContainer = rootView.findViewById(R.id.search_box_container);
        searchBox = (EditText) rootView.findViewById(R.id.search_box);

        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(searchBox.getText().toString());
                    return true;
                }
                return false;
            }

        });


        searchBoxContainer.setVisibility(View.GONE);


        searchBoxIcon = (ImageView) rootView.findViewById(R.id.search_box_icon);
        searchBoxIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setVisibility(View.GONE);
                searchBoxIcon.setVisibility(View.GONE);
                showSearchBoxContainer();

                Animation fade = new AlphaAnimation(1f, 0f);
                fade.setDuration(700);
                fade.setFillBefore(true);
                fade.setFillAfter(false);

                fade.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        title.setVisibility(View.GONE);
                        searchBoxIcon.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                searchBoxIcon.startAnimation(fade);
                title.startAnimation(fade);

            }
        });

        closeSearchBoxIcon = (ImageView) rootView.findViewById(R.id.close_search_box_icon);
        closeSearchBoxIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // title.setVisibility(View.VISIBLE);
                searchBoxIcon.setVisibility(View.VISIBLE);
                hideSearchBoxContainer();

            }
        });


        // ICON
        Integer icon = adapter.getIcon(position);
        if (icon != null) {
            ((ImageView) rootView.findViewById(R.id.section_icon)).setImageResource(icon);
        } else {
            ((ImageView) rootView.findViewById(R.id.section_icon)).setImageResource(adapter.getIcon(0));
        }

        // BG COLOR

        rootView.findViewById(R.id.main_container).setBackgroundColor(
                MainActivity.pageColors[position]
                // Color.rgb(rgb[0], rgb[1], rgb[2])
        );

        if (text != null && !text.trim().equals("")) {
            // TEXT
            rootView.findViewById(R.id.text).setVisibility(View.VISIBLE);
            ((TextView) rootView.findViewById(R.id.text)).setText(Html.fromHtml(text));
            ((TextView) rootView.findViewById(R.id.text)).setMovementMethod(LinkMovementMethod.getInstance());
        }

        if (items != null) {
            rootView.findViewById(R.id.text).setVisibility(View.GONE);
            rootView.findViewById(R.id.scrollable_text_container).setVisibility(View.VISIBLE);
            final ListView listView = ((ListView) rootView.findViewById(R.id.scrollable_text));
            listView.setAdapter(new ScrollableItemAdapter(this.getActivity(), items));

            final ImageView arrow = (ImageView) rootView.findViewById(R.id.arrow_scrolling_indicator);


            listView.setOnScrollListener(new AbsListView.OnScrollListener()
            {

                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {

                }


                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                    if (listView.getLastVisiblePosition() == listView.getAdapter().getCount() - 1
                            && listView.getChildAt(listView.getChildCount() - 1).getBottom() <= listView.getHeight()) {

                        Animation hideArrowAnimation = new AlphaAnimation(1, 0);
                        hideArrowAnimation.setFillBefore(false);
                        hideArrowAnimation.setFillAfter(true);
                        hideArrowAnimation.setDuration(300);

                        hideArrowAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                arrow.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        arrow.startAnimation(hideArrowAnimation);

                    } else {
                        if (arrow.getVisibility() != View.VISIBLE) {
                            Animation showArrowAnimation = new AlphaAnimation(0, 1);
                            showArrowAnimation.setFillBefore(true);
                            showArrowAnimation.setFillAfter(true);
                            showArrowAnimation.setDuration(300);

                            showArrowAnimation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    arrow.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            arrow.startAnimation(showArrowAnimation);
                        }

                    }
                }


            });

        }

        return rootView;
    }


    private void performSearch(String query) {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                getActivity());
        builderSingle.setIcon(R.drawable.magnifier_blue);
        builderSingle.setTitle(getActivity().getResources().getString(R.string.result_title));


        List<SearchResult> searchResults = new ArrayList<SearchResult>();
        /* Search results are Spanned in the form pageNumber|matchingText...
         * the pageNumber is used to navigate to the right page
         */
        String[] fieldsToSearch = new String[]{Data.SCROLLABLE_TEXT, Data.TEXT, Data.TITLE};

        if (query == null || query.length() < 2) {
            searchResults.add(new SearchResult(-1, Html.fromHtml("<i>Inserire almeno 2 caratteri</i>")));
        } else {
            for (String field : fieldsToSearch) {
                for (int pageNumber = 0, ii = Data.getData().size(); pageNumber<ii; pageNumber++) { // Map<String, Object> page : Data.getData()) {
                    Map<String, Object> page = Data.getData().get(pageNumber);
                    if (page.get(field) != null) {
                        try {

                            if (page.get(field) instanceof List) {
                                // it's a scrollable item list
                                List<ScrollableItem> items = (List<ScrollableItem>) page.get(field);

                                for (ScrollableItem item : items) {
                                    Spanned result = searchInSpanned(item.getRole(), query);
                                    if (result != null) {
                                        searchResults.add(new SearchResult(pageNumber, result));
                                    }

                                    result = searchInSpanned(item.getCompany(), query);
                                    if (result != null) {
                                        searchResults.add(new SearchResult(pageNumber, result));
                                    }
                                }

                            } else {
                                Spanned result = searchInSpanned(page.get(field), query);
                                if (result != null) {
                                    searchResults.add(new SearchResult(pageNumber, result));
                                }
                            }

                        } catch (Exception e) {
                            // do nothing, simple continue...
                        }
                    }
                }
            }
        }


        if (searchResults.size() == 0) {
            searchResults.add(new SearchResult(-1, Html.fromHtml("<i>Nessun risultato trovato!</i>")));
        }

        final SearchResultAdapter searchResultAdapter = new SearchResultAdapter(
                getActivity(),
                R.layout.search_result,
                searchResults);



        builderSingle.setNegativeButton(getActivity().getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(searchResultAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);
                        Spanned strName = searchResultAdapter.getItem(which).getMatchingText();
                        ((MainActivity) getActivity()).goToPage(searchResultAdapter.getItem(which).getPage());

                        dialog.dismiss();
                    }
                });
        builderSingle.show();

    }

    private Spanned searchInSpanned(Object spanned, String query) {
        String result = null;
        try {
            int searchContextWidth = 10;
            String content = Html.fromHtml(spanned.toString()).toString();
            content = content.replaceAll("\n", " ");
            int pos = content.toLowerCase().indexOf(query.toLowerCase());
            if (pos != -1) {
                String head = content.substring(0, pos);
                String originalHead = head;
                head = new StringBuilder(head).reverse().toString();
                head = trimString(head, searchContextWidth);
                head = new StringBuilder(head).reverse().toString();

                String preEllipsis = "";
                if (!originalHead.toLowerCase().startsWith(head.toLowerCase())) {
                    preEllipsis = "... ";
                }

                String tail = content.substring(pos + query.length());
                String originalTail = tail;
                tail = trimString(tail, searchContextWidth);

                String postEllipsis = "";
                if (!originalTail.toLowerCase().endsWith(tail.toLowerCase())) {
                    postEllipsis = "...";
                }

                result = preEllipsis + head + "<b>" + content.substring(pos, pos + query.length()) + "</b>" +tail + postEllipsis;
            }

            if (result != null) return Html.fromHtml(result);
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static String trimString(String string, int length) {

        BreakIterator bi = BreakIterator.getWordInstance();
        bi.setText(string);

        if (length < string.length()) {
            int first_after = bi.following(length);
            // to truncate:
            string = string.substring(0, first_after);
        }
        return string;

//        if(string == null || string.trim().isEmpty()){
//            return string;
//        }
//
//        StringBuffer sb = new StringBuffer(string);
//        int actualLength = length - 3;
//        if(sb.length() > actualLength){
//            // -3 because we add 3 dots at the end. Returned string length has to be length including the dots.
//            if(!soft)
//                return sb.insert(actualLength, "...".substring(0, actualLength+3)).toString();
//            else {
//                int endIndex = sb.indexOf(" ",actualLength);
//                return sb.insert(endIndex,"...".substring(0, endIndex+3)).toString();
//            }
//        }
//        return string;
    }
}
