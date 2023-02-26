package tokyo.randx.portfolio.android.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Expense expense);

    @Query("SELECT * FROM expense_table")
    LiveData<List<Expense>> getAll();

    @Delete
    void delete(Expense expense);

    @Query("DELETE FROM expense_table")
    void deleteAll();
}
