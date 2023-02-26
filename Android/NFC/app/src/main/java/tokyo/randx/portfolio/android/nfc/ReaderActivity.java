package tokyo.randx.portfolio.android.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.Objects;


public class ReaderActivity extends AppCompatActivity {
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  Enable Foreground Dispatch
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //  Disable Foreground Dispatch
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Objects.equals(intent.getAction(), NfcAdapter.ACTION_TAG_DISCOVERED)) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (tag == null) return;

            //    Display Technologies
            String[] techList = tag.getTechList();
            TextView tvTechnologies = findViewById(R.id.tv_tag_technologies);
            tvTechnologies.setText(getResources().getString(R.string.nfc_tag_technologies, getTechnologies(techList)));

            //    Display Tag ID
            byte[] tagIdBytes = tag.getId();
            TextView tvTagId = findViewById(R.id.tv_tag_id);
            tvTagId.setText(getResources().getString(R.string.nfc_tag_id_prefix, byte2HexString(tagIdBytes)));

            TextView tvTime = findViewById(R.id.textview_timestamp);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                final Date date = new Date(System.currentTimeMillis());
                tvTime.setText(getResources().getString(R.string.nfc_hce_timestamp, df.format(date)));
            }
        }
    }

    /**
     * convert bytes to formatted Hex String
     * eg. 01:23:45:9A:AB:CD:EF
     *
     * @param bytes bytes of Tag ID
     * @return formatted Hex String of Tag ID
     *
     */
    private String byte2HexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            result.append(String.format("%02X", bytes[i] & 0xFF));
            if (i < bytes.length - 1) result.append(":");

        }

        return result.toString();
    }

    /**
     * get formatted technologies of the Tag
     * eg. NfcA,NfcF
     *
     * @param technologies Array of technologies
     * @return formatted technologies string of the Tag
     *
     */
    private String getTechnologies(String[] technologies) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < technologies.length; i++) {
            result.append(technologies[i].substring(17));
            if (i < technologies.length - 1) result.append(",");
        }

        return result.toString();
    }

    /**
     * initiate NFC
     *
     */
    private void init() {
        pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                new Intent(getApplicationContext(), ReaderActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        nfcAdapter = NfcAdapter.getDefaultAdapter(getApplicationContext());
    }
}
