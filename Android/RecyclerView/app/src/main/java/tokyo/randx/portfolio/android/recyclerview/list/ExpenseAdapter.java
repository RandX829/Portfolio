package tokyo.randx.portfolio.android.recyclerview.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;

import tokyo.randx.portfolio.android.recyclerview.R;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private List<Expense> expenses;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIcon;
        TextView date;
        TextView memo;
        ImageView paymentMethod;
        TextView amount;

        public ViewHolder(View v) {
            super(v);
            categoryIcon = v.findViewById(R.id.image_category_icon);
            date = v.findViewById(R.id.text_date);
            memo = v.findViewById(R.id.text_memo);
            paymentMethod = v.findViewById(R.id.image_payment_method);
            amount = v.findViewById(R.id.text_amount);
        }
    }

    public ExpenseAdapter(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expense, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Expense expense = expenses.get(position);
        viewHolder.categoryIcon.setImageResource(getImage(expense.category));
        viewHolder.date.setText(expense.date);
        viewHolder.memo.setText(expense.memo);
        viewHolder.paymentMethod.setImageResource(getImage(expense.paymentMethod));
        viewHolder.amount.setText("￥" + NumberFormat.getInstance().format(expense.amount));
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    private Integer getImage(String name) {
        Integer imageResId = -1;
        switch (name) {
            case "automotive":
                imageResId = R.drawable.automotive;
                break;
            case "beauty":
                imageResId = R.drawable.beauty;
                break;
            case "cash":
                imageResId = R.drawable.cash;
                break;
            case "cellphone":
                imageResId = R.drawable.cellphone;
                break;
            case "clothing":
                imageResId = R.drawable.clothing;
                break;
            case "eating_out":
                imageResId = R.drawable.eating_out;
                break;
            case "electricity":
                imageResId = R.drawable.electricity;
                break;
            case "gas":
                imageResId = R.drawable.gas;
                break;
            case "groceries":
                imageResId = R.drawable.groceries;
                break;
            case "internet":
                imageResId = R.drawable.internet;
                break;
            case "jcb":
                imageResId = R.drawable.jcb;
                break;
            case "medical":
                imageResId = R.drawable.medical;
                break;
            case "transportation":
                imageResId = R.drawable.transportation;
                break;
            case "visa":
                imageResId = R.drawable.visa;
                break;
            case "water":
                imageResId = R.drawable.water;
                break;
            default:
                imageResId = R.mipmap.ic_launcher;
        }
        return imageResId;
    }

}
