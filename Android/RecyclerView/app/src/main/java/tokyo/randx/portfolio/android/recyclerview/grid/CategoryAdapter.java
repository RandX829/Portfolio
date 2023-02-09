package tokyo.randx.portfolio.android.recyclerview.grid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tokyo.randx.portfolio.android.recyclerview.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> categories;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIcon;
        TextView displayName;

        public ViewHolder(View v) {
            super(v);
            categoryIcon = v.findViewById(R.id.image_category_icon);
            displayName = v.findViewById(R.id.text_category);
        }
    }

    public CategoryAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Category category = categories.get(position);
        viewHolder.categoryIcon.setImageResource(getImage(category.category));
        viewHolder.displayName.setText(category.displayName);
    }

    @Override
    public int getItemCount() {
        return categories.size();
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
            case "medical":
                imageResId = R.drawable.medical;
                break;
            case "transportation":
                imageResId = R.drawable.transportation;
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
