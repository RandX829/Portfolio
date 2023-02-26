package tokyo.randx.portfolio.android.nfc;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;


public class HceReaderActivity extends AppCompatActivity implements NfcAdapter.ReaderCallback {

    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hce_reader);
        init();
    }

    @Override
    public void onPause() {
        super.onPause();
        disableReaderMode();
    }

    @Override
    public void onResume() {
        super.onResume();
        enableReaderMode();
    }

    private void enableReaderMode() {
        if (nfcAdapter != null) {
            int flags = NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK;
            nfcAdapter.enableReaderMode(this, this, flags, null);
        }
    }

    private void disableReaderMode() {
        if (nfcAdapter != null) {
            nfcAdapter.disableReaderMode(this);
        }
    }

    private void init() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(getApplicationContext());

        switch (NfcUtil.isNfcAvailable(nfcAdapter)) {
            case NOT_SUPPORTED:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.nfc_not_supported), Toast.LENGTH_SHORT).show();
                break;
            case NOT_ENABLED:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.nfc_not_enabled), Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.nfc_ready), Toast.LENGTH_SHORT).show();
                enableReaderMode();
        }
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        IsoDep isoDep = IsoDep.get(tag);
        if (isoDep != null) {
            try {
                isoDep.connect();
                // This command tells the remote device which service we wish to communicate with.
                byte[] command = NfcUtil.BuildSelectApdu(NfcUtil.AID);
                // Send command to remote device
                byte[] result = isoDep.transceive(command);
                // If AID is successfully selected, 0x9000 is returned as the status word (last 2 bytes of the result) by convention.
                // Everything before the status word is optional payload.
                int resultLength = result.length;
                byte[] statusWord = {result[resultLength-2], result[resultLength-1]};
                byte[] payload = Arrays.copyOf(result, resultLength-2);
                if (Arrays.equals(NfcUtil.SELECT_OK_SW, statusWord)) {
                    String payloadStr = new String(payload, StandardCharsets.UTF_8);
                    Log.d("", payloadStr);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView tvAid = findViewById(R.id.textview_aid);
                            tvAid.setText(getResources().getString(R.string.nfc_hce_aid_label, getResources().getString(R.string.nfc_hce_aid)));
                            TextView tvPayload = findViewById(R.id.textview_payload);
                            tvPayload.setText(getResources().getString(R.string.nfc_hce_payload, payloadStr));
                            TextView tvTime = findViewById(R.id.textview_timestamp);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                final Date date = new Date(System.currentTimeMillis());
                                tvTime.setText(getResources().getString(R.string.nfc_hce_timestamp, df.format(date)));
                            }
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
