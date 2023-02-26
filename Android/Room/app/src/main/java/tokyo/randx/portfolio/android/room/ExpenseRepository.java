package tokyo.randx.portfolio.android.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpenseRepository {

    private ExpenseDao expenseDao;
    private LiveData<List<Expense>> expenses;

    ExpenseRepository(@NonNull Application application) {
        ExpenseRoomDatabase expenseRoomDatabase = ExpenseRoomDatabase.getDatabase(application);
        this.expenseDao = expenseRoomDatabase.expenseDao();
        this.expenses = expenseDao.getAll();
    }

    LiveData<List<Expense>> getAll() {
        return expenses;
    }

    void insert(Expense expense) {
        ExpenseRoomDatabase.databaseWriteExecutor.execute(() ->{
            expenseDao.insert(expense);
        });
    }

    void delete(Expense expense) {
        ExpenseRoomDatabase.databaseWriteExecutor.execute(() ->{
            expenseDao.delete(expense);
        });
    }

    void deleteAll() {
        ExpenseRoomDatabase.databaseWriteExecutor.execute(() ->{
            expenseDao.deleteAll();
        });
    }
}
