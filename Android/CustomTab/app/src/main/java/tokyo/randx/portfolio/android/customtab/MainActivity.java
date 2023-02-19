package tokyo.randx.portfolio.android.customtab;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> load());
    }

    private void load() {
        String url = "https://github.com/RandX829/Portfolio";
        CustomTabColorSchemeParams color = new CustomTabColorSchemeParams.Builder()
                .setToolbarColor(getColor(R.color.purple_500))
                .build();
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setDefaultColorSchemeParams(color);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }
}