package it.inefficienza.interactivebusinesscard.chs;

/**
 * @author Christian Sarnataro
 */
public class ScrollableItem {
    private String year;
    private String company;
    private String role;

    public ScrollableItem(String year, String company, String role) {
        this.year = year;
        this.company = company;
        this.role = role;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
