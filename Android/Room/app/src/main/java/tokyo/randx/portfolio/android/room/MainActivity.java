package tokyo.randx.portfolio.android.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpenseViewModel expenseViewModel;
    private List<Expense> currentItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_expense_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        ExpenseAdapter expenseAdapter = new ExpenseAdapter(new ExpenseAdapter.ExpenseDiff());
        recyclerView.setAdapter(expenseAdapter);

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
        expenseViewModel.getExpenses().observe(this, e -> {
            expenseAdapter.submitList(e);
            currentItems = e;
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_create);
        floatingActionButton.setOnClickListener( view -> {
            long timestamp = System.currentTimeMillis();
            Calendar calendar = Calendar.getInstance();
            String datetime = calendar.get(Calendar.YEAR) + "-" +
                    calendar.get(Calendar.MONTH) + "-" +
                    calendar.get(Calendar.DAY_OF_MONTH) + " " +
                    calendar.get(Calendar.HOUR) + ":" +
                    calendar.get(Calendar.MINUTE) + ":" +
                    calendar.get(Calendar.SECOND);
            Expense e = new Expense(String.valueOf(timestamp), "visa", datetime, "groceries", 3000, "イーオン");
            expenseViewModel.insert(e);
        });

        Button btnDeleteAll = findViewById(R.id.btn_delete_all);
        btnDeleteAll.setOnClickListener( v -> {
            expenseViewModel.deleteAll();
        });

        Button btnDeleteLatest = findViewById(R.id.btn_delete_latest);
        btnDeleteLatest.setOnClickListener( v -> {
            if (currentItems != null && !currentItems.isEmpty()) {
                expenseViewModel.delete(currentItems.get(currentItems.size() - 1));
            }
        });
    }
}