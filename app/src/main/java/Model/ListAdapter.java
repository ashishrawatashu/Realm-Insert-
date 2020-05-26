package Model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.realmdatabase.R;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class ListAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    ArrayList<Student> studentArrayList;
    AlertDialog.Builder builder;
    Realm realm;
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        Student student = studentArrayList.get(position);
        holder.textViewl.setText(student.getName());
        holder.ageTV.setText(student.getAge());

        Log.e("name",student.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Selected", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to remove this Item ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        realm = Realm.getDefaultInstance();
                        final RealmResults<Student> rows = realm.where(Student.class).equalTo("student_id",studentArrayList.get(position).getId()).findAll();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                            rows.deleteAllFromRealm();
                            }
                        });

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });

                AlertDialog alert = builder.create();
                alert.setTitle("Remove Item.");
                alert.show();


            }
        });

    }


    @Override
    public int getItemCount() {
        Log.e("size",studentArrayList.size()+"");
        return studentArrayList.size();
    }
}
