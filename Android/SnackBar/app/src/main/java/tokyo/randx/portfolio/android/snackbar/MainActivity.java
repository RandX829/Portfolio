package tokyo.randx.portfolio.android.snackbar;

import android.os.Bundle;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import tokyo.randx.portfolio.android.snackbar.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayoutContainer = findViewById(R.id.constraintLayout_container);
        Button btnShow = findViewById(R.id.btn_show);
        btnShow.setOnClickListener(view -> showSnackBar(constraintLayoutContainer));
    }

    private void showSnackBar(View view) {
        Snackbar.make(view, "This is a SnackBar", BaseTransientBottomBar.LENGTH_LONG)
                .setAction("DISMISS", dismiss -> { })
                .setActionTextColor(getResources().getColor(com.google.android.material.R.color.design_default_color_on_primary, null))
                .setTextColor(getResources().getColor(com.google.android.material.R.color.design_default_color_secondary, null))
                .show();
    }
}