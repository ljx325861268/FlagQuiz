package li.emily.navbar.Model;

import android.graphics.Bitmap;

public class Country {
    private String name;
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
}
