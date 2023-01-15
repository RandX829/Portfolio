package tokyo.randx.portfolio.android.nfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final List<String> list = new ArrayList<>(Arrays.asList("NFC Card Reader", "Host-based Card Emulation(HCE)"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        ListView listView = findViewById(R.id.lv_list);
        listView.setAdapter(arrayAdapter);
        setListener(listView);
    }

    private void setListener(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), NfcActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "COMING SOON...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}