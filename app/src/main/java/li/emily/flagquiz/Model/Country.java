package li.emily.flagquiz.Model;

import android.graphics.Bitmap;

public class Country {
    private String name;
    private String subregion;
    private String flag;

    public Bitmap getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(Bitmap flagImage) {
        this.flagImage = flagImage;
    }

    private Bitmap flagImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }
}
