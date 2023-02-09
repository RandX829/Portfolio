package tokyo.randx.portfolio.android.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import tokyo.randx.portfolio.android.recyclerview.grid.GridActivity;
import tokyo.randx.portfolio.android.recyclerview.list.ListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button btnList = findViewById(R.id.button_list);
        btnList.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            startAct(intent);
        });

        Button btnGrid = findViewById(R.id.button_grid);
        btnGrid.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GridActivity.class);
            startAct(intent);
        });
    }

    public void startAct(Intent intent) {
        startActivity(intent);
    }
}