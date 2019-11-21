package li.emily.flagquiz.Model;

import java.util.ArrayList;
import java.util.List;

// a default list of countries deemed to have highly noticeable flags
public enum EasyCountry {
    CHINA("china"),
    JAPAN("japan"),
    AUSTRALIA("australia"),
    MEXICO("mexico"),
    UNITED_STATES_OF_AMERICA("united states of america"),
    VIETNAM("viet nam"),
    CANADA("canada"),
    GREECE("greece"),
    SOUTH_KOREA("korea (republic of)"),
    SINGAPORE("singapore"),
    MALAYSIA("malaysia"),
    INDONESIA("indonesia"),
    UNITED_KINGDOM("united kingdom of great britain and northern ireland"),
    IRELAND("ireland"),
    FRANCE("france"),
    SWITZERLAND("switzerland"),
    GERMANY("germany"),
    ISRAEL("israel"),
    TURKEY("turkey"),
    LEBANON("lebanon"),
    HONG_KONG("hong kong"),
    TAIWAN("taiwan"),
    NEW_ZEALAND("new zealand"),
    RUSSIA("russian federation"),
    EGYPT("egypt");

    private final String key;

    EasyCountry(String key){
        this.key = key;
    }

    public static List<String> getEasyCountries(){
        ArrayList<String> result = new ArrayList();
        for(EasyCountry e : EasyCountry.values()){
            result.add(e.key);
        }
        return result;
    }
}
