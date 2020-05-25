package Model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realmdatabase.R;

import java.util.ArrayList;


public class ListAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    ArrayList<Student> studentArrayList;

    public ListAdapter(Context context, ArrayList<Student> studentArrayList) {
        this.context = context;
        this.studentArrayList = studentArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View  view = LayoutInflater.from(context).inflate(R.layout.list_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Student student = studentArrayList.get(position);
        holder.textViewl.setText(student.getName());

        Log.e("name",student.getName());

    }

    @Override
    public int getItemCount() {
        Log.e("size",studentArrayList.size()+"");
        return studentArrayList.size();
    }
}
