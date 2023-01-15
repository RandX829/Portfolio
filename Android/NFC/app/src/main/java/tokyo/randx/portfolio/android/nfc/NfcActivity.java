package tokyo.randx.portfolio.android.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class NfcActivity extends AppCompatActivity {
    private static final String TAG = "NfcActivity";
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;

    /**
     * Result ENUM
     *
     */
    enum Result {
        READY,
        NOT_SUPPORTED,
        NOT_ENABLED
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
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
            tvTechnologies.setText(getResources().getString(R.string.nfc_tag_technologies_prefix, getTechnologies(techList)));

            //    Display Tag ID
            byte[] tagIdBytes = tag.getId();
            TextView tvTagId = findViewById(R.id.tv_tag_id);
            tvTagId.setText(getResources().getString(R.string.nfc_tag_id_prefix, byte2HexString(tagIdBytes)));
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
     * Check if NFC is ready
     *
     * @param nfcAdapter NFC Adapter
     * @return Result READY or NOT_SUPPORTED or NOT_ENABLED
     *
     */
    private Result isNfcAvailable(NfcAdapter nfcAdapter) {
        if (nfcAdapter == null) {
            Log.d(TAG, getResources().getString(R.string.nfc_not_supported));
            return Result.NOT_SUPPORTED;
        } else if (!nfcAdapter.isEnabled()) {
            Log.d(TAG, getResources().getString(R.string.nfc_not_enabled));
            return Result.NOT_ENABLED;
        } else {
            Log.d(TAG, getResources().getString(R.string.nfc_ready));
            return Result.READY;
        }
    }

    /**
     * initiate NFC
     *
     */
    private void init() {
        pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                new Intent(getApplicationContext(), NfcActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        nfcAdapter = NfcAdapter.getDefaultAdapter(getApplicationContext());

        switch (isNfcAvailable(nfcAdapter)) {
            case NOT_SUPPORTED:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.nfc_not_supported), Toast.LENGTH_SHORT).show();
                break;
            case NOT_ENABLED:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.nfc_not_enabled), Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.nfc_ready), Toast.LENGTH_SHORT).show();
        }
    }
}
