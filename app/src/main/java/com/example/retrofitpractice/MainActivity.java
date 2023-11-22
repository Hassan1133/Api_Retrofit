package com.example.retrofitpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button addBtn;
    private EditText name, father_name, department, email;

    private RecyclerView recyclerView;
    private UserAdp adp;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        fetchData();
    }

    void initialize() {
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);

        recyclerView = findViewById(R.id.recycler); // initialize recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBtn:
                createDialog();
                break;
        }
    }

    private boolean isValid() {
        boolean valid = true;
        if (name.getText().length() < 3) {
            name.setError("Enter valid name");
            valid = false;
        }
        if (father_name.getText().length() < 3) {
            father_name.setError("Enter valid father name");
            valid = false;
        }
        if (department.getText().length() < 2) {
            department.setError("Enter valid department");
            valid = false;
        }
        if (email.getText().length() < 13) {
            email.setError("Enter valid email");
            valid = false;
        }
        return valid;
    }

    private void createDialog() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.show();
        dialog.setCancelable(false);

        name = dialog.findViewById(R.id.student_name);
        father_name = dialog.findViewById(R.id.student_father_name);
        department = dialog.findViewById(R.id.student_department);
        email = dialog.findViewById(R.id.student_email);

        Button cancelBtn = dialog.findViewById(R.id.cancelBtn);
        Button okBtn = dialog.findViewById(R.id.okBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValid()) {
                    insert(name.getText().toString(), father_name.getText().toString(), department.getText().toString(), email.getText().toString());
                    fetchData();
                    dialog.dismiss();
                }

            }
        });
    }

    void insert(String name, String father_name, String department, String email) {
        Call<GetResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .insertData(name, father_name, department, email);

        call.enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {

                if (response.isSuccessful()) {
                    GetResponse getResponse = response.body();
                    Log.d(TAG, "onResponse: " + getResponse.getResponse());
                }

            }

            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    void fetchData() {
        Call<List<User>> call = RetrofitClient.getInstance().getApi().fetchData();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {


                if (response.isSuccessful()) {
                    users = response.body();
                    setDataToRecycler(users);
                    Toast.makeText(MainActivity.this, "added" , Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(MainActivity.this, "not added" , Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setDataToRecycler(List<User> users) {
        adp = new UserAdp(MainActivity.this,users);
        recyclerView.setAdapter(adp);
    }
}