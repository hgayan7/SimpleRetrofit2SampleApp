package com.example.him.retrofit2_pet_project.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.him.retrofit2_pet_project.R;
import com.example.him.retrofit2_pet_project.model.Example;
import com.example.him.retrofit2_pet_project.model.Results;
import com.example.him.retrofit2_pet_project.rest.ApiClient;
import com.example.him.retrofit2_pet_project.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();
    Button button;
    TextView rise,set;
    EditText lat,lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
    }

    public void setUp(){
        final ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        button=findViewById(R.id.button);
        lat=findViewById(R.id.lat_text);
        lng=findViewById(R.id.lng_text);
        rise=findViewById(R.id.sunrise);
        set=findViewById(R.id.sunset);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Example> call=apiService.getSunrise(Float.parseFloat(lat.getText().toString()),Float.parseFloat(lng.getText().toString()));
                call.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        Results results=response.body().getResults();
                        rise.setText("Sunrise : "+results.getSunrise());
                        set.setText("Sunset : "+results.getSunset());
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
