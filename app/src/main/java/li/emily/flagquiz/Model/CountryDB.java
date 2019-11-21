package li.emily.flagquiz.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CountryDB {
    private static HashMap<String, Country> countryMap = new HashMap<String, Country>();
    private static HashMap<String, Country> hardCountryMap = new HashMap<String, Country>();
    private static HashMap<String, Country> easyCountryMap;
    public static List<Country> correctCountries;
    public static List<Country> incorrectCountries;

    private static final String MISC = "Misc";

    public static Country getCountry(String input){
        return countryMap.get(input.toLowerCase());
    }

    public static void setCountries(Country[] countries){
        for(Country c : countries){
            if(c.getSubregion() == null || c.getSubregion().isEmpty()) {
                c.setSubregion(MISC);
            }
            countryMap.put(c.getName().toLowerCase(), c);
        }
    }

    public static HashMap<String, Country> getEasyCountries(){
        return easyCountryMap;
    }

    public static HashMap<String, Country> getHardCountries(){
        return hardCountryMap;
    }

    public static void mimic(){
        hardCountryMap = new HashMap<String, Country>(countryMap);
    }

    public static void setEasyCountryMap(HashMap<String, Country> input){
        easyCountryMap = new HashMap<String, Country>(input);
    }

    public static int getSize(){
        return countryMap.size();
    }

    public static List<Country> getCountries(){
        return new ArrayList(countryMap.values());
    }

    // prepares the list of questions depending on the difficulty
    // there will 5 questions every time the quiz is taken
    public static void prepareQuestions(boolean easy){
        int size = 0;
        List<Integer> countryIndexList = new ArrayList<Integer>();
        List<Country> result = new ArrayList<Country>();
        List<Country> countriesList = null;
        if(easy){
            size = easyCountryMap.size();
            countriesList = new ArrayList<Country>(easyCountryMap.values());
            while(countryIndexList.size() != 5){
                int randomIndex = (int) (Math.random() * size);
                if(!countryIndexList.contains(randomIndex)) countryIndexList.add(randomIndex);
            }
            for(int i : countryIndexList){
                result.add(countriesList.get(i));
            }
        } else {
            size = hardCountryMap.size();
            countriesList = new ArrayList<Country>(hardCountryMap.values());
            while(countryIndexList.size() != 5) {
                int randomIndex = (int) (Math.random() * size);
                if (!countryIndexList.contains(randomIndex)) countryIndexList.add(randomIndex);
            }
            for(int i : countryIndexList){
                result.add(countriesList.get(i));
            }
        }


        List<Integer> incorrectCountryIndexList = new ArrayList<Integer>();
        List<Country> incorrectResult = new ArrayList<Country>();
        while(incorrectCountryIndexList.size() != 15){
            int randomIndex = (int) (Math.random() *  hardCountryMap.size());
            if(!incorrectCountryIndexList.contains(randomIndex) && !countryIndexList.contains(randomIndex)) {
                incorrectCountryIndexList.add(randomIndex);
            }
        }
        countriesList = new ArrayList<Country>(hardCountryMap.values());
        for(int i : incorrectCountryIndexList){
            incorrectResult.add(countriesList.get(i));
        }

        correctCountries = result;
        incorrectCountries = incorrectResult;
    }

}
