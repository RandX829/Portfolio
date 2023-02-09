package tokyo.randx.portfolio.android.recyclerview.grid;

import java.util.ArrayList;
import java.util.List;

public class Category {
    String category;
    String displayName;

    Category(String category, String displayName) {
        this.category = category;
        this.displayName = displayName;
    }
    public static List<Category> createDummy() {
        List<Category> categories = new ArrayList<>();
        Category c1 = new Category("groceries", "Groceries");
        Category c2 = new Category("transportation", "Transportation");
        Category c4 = new Category("cellphone", "Cellphone");
        Category c5 = new Category("clothing", "Clothing");
        Category c6 = new Category("eating_out", "Eating_out");
        Category c7 = new Category("electricity", "Electricity");
        Category c8 = new Category("gas", "Gas");
        Category c9 = new Category("internet", "Internet");
        Category c10 = new Category("water", "Water");
        Category c11 = new Category("automotive", "Automotive");

        categories.add(c1);
        categories.add(c2);
        categories.add(c4);
        categories.add(c5);
        categories.add(c6);
        categories.add(c7);
        categories.add(c8);
        categories.add(c9);
        categories.add(c10);
        categories.add(c11);

        return categories;
    }
}
