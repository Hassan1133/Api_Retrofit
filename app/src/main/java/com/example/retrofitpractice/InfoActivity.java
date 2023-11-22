package com.example.retrofitpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name, father_name, department, email;
    private ImageView editBtn, deleteBtn, doneBtn;
    User user;
    private static final String TAG = "InfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        init();
        getIntentData();
    }

    void init() {
        name = findViewById(R.id.name);
        father_name = findViewById(R.id.father_name);
        department = findViewById(R.id.department);
        email = findViewById(R.id.email);
        editBtn = findViewById(R.id.edit_btn);
        deleteBtn = findViewById(R.id.delete_btn);
        doneBtn = findViewById(R.id.done_btn);
        editBtn.setOnClickListener(this);
        doneBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    void getIntentData() {

        user = (User) getIntent().getSerializableExtra("user");
        name.setText(user.getName());
        father_name.setText(user.getFather_name());
        department.setText(user.getDepartment());
        email.setText(user.getEmail());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_btn:
                editBtn.setImageResource(R.drawable.baseline_done_24);
                setFieldEnable();
                editBtn.setVisibility(View.INVISIBLE);
                doneBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.done_btn:
                if (isValid()) {
                    update(user.getId(), name.getText().toString(), father_name.getText().toString(), department.getText().toString(), email.getText().toString());
                    Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.delete_btn:
                deleteData(user.getId());
                Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void setFieldEnable() {
        name.setEnabled(true);
        father_name.setEnabled(true);
        department.setEnabled(true);
        email.setEnabled(true);
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

    private void update(int id, String name, String father_name, String department, String email) {
        Call<GetResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .updateData(id, name, father_name, department, email);
        call.enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
                if (response.isSuccessful()) {
                    GetResponse getResponse = response.body();
                    Toast.makeText(InfoActivity.this, "" + getResponse.getResponse(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {
                Toast.makeText(InfoActivity.this, "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteData(int id) {
        Call<GetResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .deleteData(id);
        call.enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
                if (response.isSuccessful()) {
                    GetResponse getResponse = response.body();
                    Toast.makeText(InfoActivity.this, "" + getResponse.getResponse(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {
                Toast.makeText(InfoActivity.this, "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}