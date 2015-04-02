package it.inefficienza.interactivebusinesscard.chs;

import android.text.Html;
import android.text.Spanned;

/**
 * @author Christian Sarnataro
 */
public class SearchResult {

    private int page;
    private Spanned matchingText;

    public SearchResult(int page, String matchingText) {
        this.page = page;
        this.matchingText = Html.fromHtml(matchingText);
    }

    public SearchResult(int page, Spanned matchingText) {
        this.page = page;
        this.matchingText = matchingText;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Spanned getMatchingText() {
        return matchingText;
    }

    public void setMatchingText(Spanned matchingText) {
        this.matchingText = matchingText;
    }
}
