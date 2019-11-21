package li.emily.flagquiz.Model;

public class SubregionRow {

    public String subregion;
    public boolean attempted;
    public boolean correct;
    public int count;

    public SubregionRow(String subregion) {
        this.subregion = subregion;
        this.attempted = false;
        this.correct = false;
        this.count = 0;
    }
}


/*
performance will have:
ratings for each region
correct / incorrect / attempted
 */