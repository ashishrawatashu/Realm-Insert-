package Model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realmdatabase.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView textViewl, ageTV;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewl = itemView.findViewById(R.id.nameTV);
        ageTV = itemView.findViewById(R.id.ageTV);
    }
}
