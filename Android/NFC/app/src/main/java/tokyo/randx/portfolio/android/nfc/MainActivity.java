package tokyo.randx.portfolio.android.nfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final List<String> list = new ArrayList<>(Arrays.asList("NFC Card Reader", "NFC Card Reader(HCE)"));
    private boolean isNfcAvailable = false;

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
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(getApplicationContext());
        switch (NfcUtil.isNfcAvailable(nfcAdapter)) {
            case NOT_SUPPORTED:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.nfc_not_supported), Toast.LENGTH_SHORT).show();
                break;
            case NOT_ENABLED:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.nfc_not_enabled), Toast.LENGTH_SHORT).show();
                break;
            default:
                isNfcAvailable = true;
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.nfc_ready), Toast.LENGTH_SHORT).show();
        }
    }

    private void setListener(ListView listView) {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (!isNfcAvailable) return;
            Intent intent;
            if (position == 0) {
                intent = new Intent(getApplicationContext(), ReaderActivity.class);
            } else {
                intent = new Intent(getApplicationContext(), HceReaderActivity.class);
            }
            startActivity(intent);
        });
    }
}