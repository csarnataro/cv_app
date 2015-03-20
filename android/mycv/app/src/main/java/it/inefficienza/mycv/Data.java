package it.inefficienza.mycv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Christian Sarnataro
 */
public class Data {

    private static ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

    private static int initialHue = 187;

    public static final String TITLE = "title";
    public static final String ICON= "icon";
    public static final String TEXT = "text";
    public static final String SCROLLABLE_TEXT = "scrollableText";

    private static Map<String, Object> pag1 = new HashMap<String, Object>() {{
        put(TITLE, "CHRISTIAN SARNATARO");
        put(ICON, R.drawable.avatar);
        put(TEXT, "Java, Android & iOS Developer<br><a href=\"mailto:christian.sarnataro@outlook.com?subject=CV Christian Sarnataro&body=Ho visto l'applicazione Android con il tuo CV ... \">christian.sarnataro@outlook.com</a>");
    }};

    private static Map<String, Object> pag2 = new HashMap<String, Object>() {{
        put(TITLE, "STUDI");
        put(ICON, R.drawable.education);
        put(TEXT, "Laurea in Informatica<br>Università degli Studi di Milano<br>110/110 e lode");
    }};


    private static Map<String, Object> pag3 = new HashMap<String, Object>() {{
        put(TITLE, "CONOSCENZE INFORMATICHE");
        put(ICON, R.drawable.code);

        put(SCROLLABLE_TEXT, new ArrayList(){{
            add(new ScrollableItem(null, "Programmazione e Framework",
                    "Sviluppo iOS e Android, Java/J2EE, Spring, PaaS, " +
                    "Ruby on Rails, Adobe Flex, Javascript, jQuery, AngularJS"));
            add(new ScrollableItem(null, "Database", "Oracle 10/11, mySQL, PL-SQL, MongoDB, Google BigTable, ORM (Hibernate, myBatis, ActiveRecord)"));
            add(new ScrollableItem(null, "Tool di sviluppo", "Android Studio, Xcode, Eclipse, Flash Builder 4.0, Maven, Git, Svn"));
            add(new ScrollableItem(null, "Multimedia", "Adobe Flash, Adobe Photoshop, Gimp, Inkscape"));

        }});

    }};

    private static Map<String, Object> pag4 = new HashMap<String, Object>() {{
        put(TITLE, "ESPERIENZE LAVORATIVE");
        put(ICON, R.drawable.work);

        put(SCROLLABLE_TEXT, new ArrayList(){{
            add(new ScrollableItem(
                    "2015",
                    "MTV Italia",
                    "Sviluppo applicazioni Android\nVideo API REST in Java"));
            add(new ScrollableItem(
                    "2014",
                    "Ericsson S.p.A.",
                    "Formazione JEE6\nMaven, Arquillian"));
            add(new ScrollableItem(
                    "2014",
                    "SII Group - France",
                    "Sviluppo applicazioni di telefonia cloud based."));
            add(new ScrollableItem(
                    "2013",
                    "Veolia France",
                    "Sviluppo in Java di componenti Oracle Utilities MDM"));

            add(new ScrollableItem(
                    "2013",
                    "SEEG Gabon",
                    "Sviluppo applicazione web Java per la gestione della topologia di reti elettriche"));

            add(new ScrollableItem(
                    "2012",
                    "Vodafone Ingegneria Italia",
                    "Sviluppo applicazioni Flex/Java per design di reti di telecomunicazioni."));

            add(new ScrollableItem(
                    "2011",
                    "Buongiorno S.p.A.",
                    "Sviluppo applicazione Social Betting su Facebook."));

            add(new ScrollableItem(
                    "2011",
                    "Altran Italia S.p.A.",
                        "Applicazioni web J2EE per Armani S.p.A.\n(pianificazione collezioni, order entry)"
                        ));

            add(new ScrollableItem(
                    "  · · ·  ",
                    "Altran Italia S.p.A.",
                    "Gateway di pagamenti elettronici per Vodafone Italia (J2EE)"
                    ));

            add(new ScrollableItem(
                    "2006",
                    "Altran Italia S.p.A.",
                    "Applicazioni di backoffice per Commissioning & Billing Vodafone"

            ));

            add(new ScrollableItem(
                    "  · · ·  ",
                    "Dylogic S.r.l.",
                    "Applicazioni web Java e PHP per Fastweb, Dalmine Energie, Zero9"
            ));
            add(new ScrollableItem(
                    "2001",
                    "Dylogic S.r.l.",
                    "Applicazioni Java per servizi mobile a valore aggiunto"
            ));

        }});

    }};

    private static Map<String, Object> pag5 = new HashMap<String, Object>() {{
        put(TITLE, "LINGUE");

        put(ICON, R.drawable.globe);


        put(TEXT, "Inglese: buono (Cambridge BEC Higher)<br>Francese: buono");


    }};


    static {
        data.add(pag1);
        data.add(pag2);
        data.add(pag3);
        data.add(pag4);
        data.add(pag5);
    }

    public static ArrayList<Map<String, Object>> getData() {
        return data;
    }

    public static int getInitialHue() {
        return initialHue;
    }
}
