package tokyo.randx.portfolio.android.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {

    private ExpenseRepository expenseRepository;
    private final LiveData<List<Expense>> expenses;

    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        expenseRepository = new ExpenseRepository(application);
        expenses = expenseRepository.getAll();
    }

    LiveData<List<Expense>> getExpenses() {
        return expenses;
    }

    public void insert(Expense expense) {
        expenseRepository.insert(expense);
    }

    public void delete(Expense expense) {
        expenseRepository.delete(expense);
    }

    public void deleteAll() {
        expenseRepository.deleteAll();
    }
}
