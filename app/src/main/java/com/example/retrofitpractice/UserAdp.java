package com.example.retrofitpractice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.transition.Hold;

import java.util.List;

public class UserAdp extends RecyclerView.Adapter<UserAdp.Holder> {

    Context context;
    List<User> users;

    public UserAdp(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_design, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        User user = users.get(position);

        holder.name.setText(user.getName());

        holder.infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,InfoActivity.class);
                intent.putExtra("user",user);
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView infoBtn;

        public Holder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.recycler_name);
            infoBtn = itemView.findViewById(R.id.info_btn);
        }
    }
}
