package li.emily.flagquiz.Model;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerDB {
    public static boolean latestAnswer;
    public static String choice;
    public static String correct;
    public static List<Country> correctChoices;
    public static List<Country> incorrectChoices;

    static{
        correctChoices = new ArrayList<>();
        incorrectChoices = new ArrayList<>();
    }
}
