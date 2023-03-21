package com.example.aplicacioncodigos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView vcodigo = findViewById(R.id.codigoo);
        Button verificar = findViewById(R.id.button);
        EditText ingresa = findViewById(R.id.ingresaCodigo);

        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicio(vcodigo,ingresa);
            }
        });
    }
    public void servicio(TextView vcodigo,EditText ingresa){
        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        String codeApp = ingresa.getText().toString().trim();

        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient= new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://fermurillo.online/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        ApiCodigo validarcodigo=retrofit.create(ApiCodigo.class);
        Call<codigo> call = validarcodigo.CODIGO_CALL(codeApp);
        call.enqueue(new Callback<codigo>() {
            @Override
            public void onResponse(Call<codigo> call,
                                   Response<codigo> response) {
                if (response.body()!=null){
//                    Toast.makeText(MainActivity.this, response.body().getCodigo(), Toast.LENGTH_SHORT).show();
                    vcodigo.setText(response.body().getCodigo());
                }
                else {
                    Toast.makeText(MainActivity.this, "CODIGO EQUIVOCADO", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<codigo> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });

    }
}