package tokyo.randx.portfolio.android.viewpager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Adapter.ViewPager2ViewHolder> {

    List<String> dataSet;
    public ViewPager2Adapter(List<String> dataSet) {
        this.dataSet = dataSet;
    }

    static class ViewPager2ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public ViewPager2ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }

    @NonNull
    @Override
    public ViewPager2ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.page, viewGroup, false);
        return new ViewPager2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPager2ViewHolder holder, int position) {
        holder.textView.setText(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}