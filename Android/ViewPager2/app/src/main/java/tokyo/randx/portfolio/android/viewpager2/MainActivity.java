package tokyo.randx.portfolio.android.viewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind(createDummy());
    }

    private void bind(List<String> dataSet) {
        ViewPager2 viewPager = findViewById(R.id.viewpager2);
        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager.setAdapter(new ViewPager2Adapter(dataSet));
    }

    private List<String> createDummy() {
        List<String> dummy = new ArrayList<>();
        dummy.add("Page 1");
        dummy.add("Page 2");
        dummy.add("Page 3");
        return dummy;
    }
}