package tokyo.randx.portfolio.android.nfc;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;

import java.util.Arrays;

public class ApduService extends HostApduService {
    private static final String TAG = "ApduService";

    private static final byte[] SELECT_APDU = NfcUtil.BuildSelectApdu(NfcUtil.AID);

    @Override
    public void onDeactivated(int reason) { }

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
        // If the APDU matches the SELECT AID command for this service,
        // send the loyalty card account number, followed by a SELECT_OK status trailer (0x9000).
        if (Arrays.equals(SELECT_APDU, commandApdu)) {
            String payload = "Portfolio NFC HCE";
            byte[] helloBytes = payload.getBytes();
            return NfcUtil.ConcatArrays(helloBytes, NfcUtil.SELECT_OK_SW);
        } else {
            return NfcUtil.UNKNOWN_CMD_SW;
        }
    }
}
