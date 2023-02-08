package tokyo.randx.portfolio.android.recyclerview;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Expense {
    String category;
    String date;
    String memo;
    String paymentMethod;
    Integer amount;

    Expense(String category, String date, String memo, String paymentMethod, Integer amount) {
        this.category = category;
        this.date = date;
        this.memo = memo;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }
    public static List<Expense> createDummy() {
        List<Expense> expenses = new ArrayList<>();
        Expense e1 = new Expense("groceries", "2023-01-01 08:00", "イーオン", "visa", 2500);
        Expense e2 = new Expense("automotive", "2023-01-02 08:30", "車検", "jcb", 15000);
        Expense e3 = new Expense("beauty", "2023-01-03 09:30", "美容院", "cash", 8000);
        Expense e4 = new Expense("cellphone", "2023-01-04 11:30", "携帯料金", "visa", 3000);
        Expense e5 = new Expense("clothing", "2023-01-05 15:00", "UNIQLO", "visa", 5600);
        Expense e6 = new Expense("eating_out", "2023-01-06 14:30", "外食", "visa", 4356);
        Expense e7 = new Expense("electricity", "2023-01-07 10:35", "電気代", "cash", 5690);
        Expense e8 = new Expense("gas", "2023-01-08 16:30", "ガス代", "cash", 3680);
        Expense e9 = new Expense("internet", "2023-01-09 17:30", "光回線", "visa", 4800);
        Expense e10 = new Expense("medical", "2023-01-10 19:30", "歯医者", "visa", 1200);
        Expense e11 = new Expense("transportation", "2023-01-11 09:36", "定期代", "visa", 22780);
        Expense e12 = new Expense("water", "2023-01-12 20:15", "水道料金", "cash", 6800);

        expenses.add(e1);
        expenses.add(e2);
        expenses.add(e3);
        expenses.add(e4);
        expenses.add(e5);
        expenses.add(e6);
        expenses.add(e7);
        expenses.add(e8);
        expenses.add(e9);
        expenses.add(e10);
        expenses.add(e11);
        expenses.add(e12);

        return expenses;
    }
}
