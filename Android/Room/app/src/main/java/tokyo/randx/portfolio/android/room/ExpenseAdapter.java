package tokyo.randx.portfolio.android.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;

public class ExpenseAdapter extends ListAdapter<Expense, ExpenseAdapter.ExpenseViewHolder> {
    public ExpenseAdapter(@NonNull DiffUtil.ItemCallback<Expense> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expense, viewGroup, false);
        return new ExpenseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder viewHolder, final int position) {
        Expense expense = getItem(position);
        viewHolder.categoryIcon.setImageResource(getImage(expense.getTag()));
        viewHolder.date.setText(expense.getDate());
        viewHolder.memo.setText(expense.getRemarks());
        viewHolder.paymentMethod.setImageResource(getImage(expense.getPaymentMethod()));
        viewHolder.amount.setText("￥" + NumberFormat.getInstance().format(expense.getAmount()));
    }

    static class ExpenseDiff extends DiffUtil.ItemCallback<Expense> {

        @Override
        public boolean areItemsTheSame(@NonNull Expense oldItem, @NonNull Expense newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Expense oldItem, @NonNull Expense newItem) {
            return oldItem.getRecordId().equals(newItem.getRecordId());
        }
    }

    private Integer getImage(String name) {
        Integer imageResId = -1;
        switch (name) {
            case "groceries":
                imageResId = R.drawable.groceries;
                break;
            case "visa":
                imageResId = R.drawable.visa;
                break;
            default:
                imageResId = R.mipmap.ic_launcher;
        }
        return imageResId;
    }

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIcon;
        TextView date;
        TextView memo;
        ImageView paymentMethod;
        TextView amount;

        public ExpenseViewHolder(View v) {
            super(v);
            categoryIcon = v.findViewById(R.id.image_category_icon);
            date = v.findViewById(R.id.text_date);
            memo = v.findViewById(R.id.text_memo);
            paymentMethod = v.findViewById(R.id.image_payment_method);
            amount = v.findViewById(R.id.text_amount);
        }
    }
}
