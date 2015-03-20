package it.inefficienza.mycv;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Christian Sarnataro
 */

public class MainActivity extends ActionBarActivity {


    private ArrayList<Map<String, Object>> data;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    static int[] pageColors;

    TextView pageIndicatorView;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FontsOverride.setDefaultFont(getApplicationContext(), "DEFAULT", "fonts/Sertig.ttf");
        FontsOverride.setDefaultFont(getApplicationContext(), "SERIF", "fonts/Sertig.ttf");
        FontsOverride.setDefaultFont(getApplicationContext(), "SANS_SERIF", "fonts/Sertig.ttf");
        FontsOverride.setDefaultFont(getApplicationContext(), "DEFAULT_BOLD", "fonts/Sertig.ttf");

        data = Data.getData();

        int initialHue = Data.getInitialHue();
        pageColors = new int[data.size()];

        String[] colorArray = getResources().getStringArray(R.array.bg_colors);
        for (int i=0,ii=data.size(); i<ii; i++) {
            if (i < colorArray.length) {
                int hexColor = Color.parseColor(colorArray[i]);
                pageColors[i] = hexColor;
            } else {
                pageColors[i] = generatePastelColor(initialHue, i, ii);
            }
        }

        pageIndicatorView = (TextView) findViewById(R.id.page_indicator);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ImageView sharingButton = (ImageView) this.findViewById(R.id.share_button);
        sharingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shareIt();
            }
        });


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        updateIndicators(0);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(final int position) {
                // Toast.makeText(MainActivity.this, "Page #" + position, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "############# position is[" + position + "]");

                MainActivity.this.updateIndicators(position);

                for (int i=0, ii=mViewPager.getChildCount(); i<ii; i++) {

                    final TextView title = (TextView) mViewPager.getChildAt(i).findViewById(R.id.section_label);
                    final ImageView searchBoxIcon = (ImageView) mViewPager.getChildAt(i).findViewById(R.id.search_box_icon);
                    final View searchBoxContainer = mViewPager.getChildAt(i).findViewById(R.id.search_box_container);
                    final TextView searchBox = (TextView) mViewPager.getChildAt(i).findViewById(R.id.search_box);

                    searchBox.setText("");
                    if (title.getVisibility() == View.GONE || title.getVisibility() == View.INVISIBLE ) {

                        Animation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                        fadeOut.setDuration(300);
                        fadeOut.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                searchBoxContainer.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });


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
                                searchBoxIcon.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                        searchBoxContainer.startAnimation(fadeOut);
                        title.startAnimation(titleFadeIn);
                    }


                }
            }

        });

    }

    public SectionsPagerAdapter getSectionsPagerAdapter() {
        return mSectionsPagerAdapter;
    }

    private void updateIndicators(int position) {
        Log.d("TAG", "$$$$$$$$$$ position is: " + position);
        pageIndicatorView.setText(getPageIndicator(position));
    }

    private String getPageIndicator(int position) {
        String thinDot = "·";
        String thickDot = "•";
        String pageIndicator = "";
        for (int i = 0, ii = data.size(); i<ii; i++) {
            if (i == position) {
                pageIndicator += thickDot;
            } else {
                pageIndicator += thinDot;
            }

            if (i < (ii-1)) {
                pageIndicator +=" ";
            }
        }
        return pageIndicator;
    }


    public static int generatePastelColor(int initialHue, int position, int pageNumber) {
        // Random random = new Random();

        float hue = (initialHue + ((1f*position)/(1f*pageNumber) * 360)) % 360;
        Log.i("HUE", "Calculated hue is[" + hue + "]");
        float saturation = 0.9f;
        float value = 0.7f;

        int randomColor = Color.HSVToColor(new float[]{
                hue,
                saturation,
                value
        });
        int red = Color.red(randomColor); // random.nextInt(256);
        int green = Color.green(randomColor); // random.nextInt(256);
        int blue = Color.blue(randomColor); // random.nextInt(256);

        int mix = Color.parseColor("#aaaaaa");
        // mix the color
            red = (red +  /*mix.getRed()*/ Color.red(mix)) / 2;
            green = (green + /*mix.getGreen()*/ Color.green(mix)) / 2;
            blue = (blue + /*mix.getBlue())*/ Color.blue(mix)) / 2;

        int color = Color.rgb(red, green, blue);
        return color;
    }

    public void goToPage(int page) {
        mViewPager.setCurrentItem(page);
    }


    private void shareIt() {
        //sharing implementation here
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.share_subject));

        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.share_body));
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_via)));

    }

}
