package tokyo.randx.portfolio.android.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense_table")
public class Expense {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "record_id")
    private final String recordId;
    @ColumnInfo(name = "payment_method")
    private final String paymentMethod;
    @ColumnInfo(name = "date")
    private final String date;
    @ColumnInfo(name = "tag")
    private final String tag;
    @ColumnInfo(name = "amount")
    private final int amount;
    @ColumnInfo(name = "remarks")
    private final String remarks;

    public Expense(@NonNull String recordId, String paymentMethod, String date, String tag, int amount, String remarks) {
        this.recordId = recordId;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.tag = tag;
        this.amount = amount;
        this.remarks = remarks;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getDate() {
        return date;
    }

    public String getTag() {
        return tag;
    }

    public int getAmount() {
        return amount;
    }

    public String getRemarks() {
        return remarks;
    }
}
