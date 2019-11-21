package li.emily.flagquiz.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import li.emily.flagquiz.R;

public class FlagInformationActivity extends AppCompatActivity {

    private TextView countryName;
    private ImageView flag;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flag_information_layout);

        countryName = findViewById(R.id.id_flagName);
        flag = findViewById(R.id.id_flag_image);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String url = intent.getStringExtra("flag");

        countryName.setText(name);

        Uri uri = Uri.parse(url);

        // gets the flag image and sets it in the imageview
        GlideToVectorYou.justLoadImage(this, uri, flag);
    }
}
