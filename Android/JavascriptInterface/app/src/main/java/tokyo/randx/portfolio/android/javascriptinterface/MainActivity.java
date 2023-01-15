package tokyo.randx.portfolio.android.javascriptinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static final String TYPE = "type";
    public static final String PAYLOAD = "payload";
    public static final String WEB_TO_NATIVE = "web_to_native";
    public static final String URL = "file:///android_asset/index.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        Button btnHello = findViewById(R.id.button_hello);
        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView webView = findViewById(R.id.web_view);
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.evaluateJavascript("window.postMessage({ type: \"native_to_web\", payload: \"Hello from Native\" }, \"*\");", null);
                    }
        });
            }
        });

        WebView webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new CustomWebViewClient());
        webView.addJavascriptInterface(new CustomJavascriptInterface(), "CustomJavascriptInterface");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(URL);
    }

    class CustomJavascriptInterface {

        @JavascriptInterface
        public boolean onMessage(String data) throws JSONException {
            JSONObject parcel = new JSONObject(data);
            String type = parcel.optString(TYPE);
            String payload = parcel.optString(PAYLOAD);
            if (Objects.equals(type, WEB_TO_NATIVE) && !payload.isEmpty()) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String timeString = sdf.format(date);
                TextView textView = findViewById(R.id.textview);
                textView.append(timeString + "\n" + payload + "\n");
                return true;
            }

            return false;
        }
    }


    static class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.evaluateJavascript(
                    "window.addEventListener ('message', event => {" +
                            "CustomJavascriptInterface.onMessage(JSON.stringify(event.data));" +
                            "});",
                    null
            );
        }
    }

}