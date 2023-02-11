package tokyo.randx.portfolio.android.bottomsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button btnShow = findViewById(R.id.btn_show);
        btnShow.setOnClickListener(view -> {
            ModalBottomSheet modalBottomSheet = new ModalBottomSheet();
            modalBottomSheet.show(getSupportFragmentManager(), ModalBottomSheet.TAG);
        });
    }
}