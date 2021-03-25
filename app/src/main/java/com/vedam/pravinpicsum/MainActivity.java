package com.vedam.pravinpicsum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.vedam.pravinpicsum.API.APIClient;
import com.vedam.pravinpicsum.API.APIInterface;
import com.vedam.pravinpicsum.API.Models.Pics;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://picsum.photos/";
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    List<Pics> picsList = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        restoreInstance(savedInstanceState);

    }



    void restoreInstance(Bundle savedInstance){

        if(savedInstance!=null){

            Toast.makeText(this, "Loading savedInstance", Toast.LENGTH_SHORT).show();
            
            int orientation = this.getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
             //   Toast.makeText(this, "Portrait", Toast.LENGTH_SHORT).show();
                recyclerView.setLayoutManager(new GridLayoutManager(this,2));

            } else {
              //  Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
                recyclerView.setLayoutManager(new GridLayoutManager(this,4));
            }

            picsList = savedInstance.getParcelableArrayList("list");
            recyclerAdapter = new RecyclerAdapter(MainActivity.this,picsList);
            recyclerView.setAdapter(recyclerAdapter);
            recyclerAdapter.notifyDataSetChanged();
        }else {
            loadDataRetroFit();
        }


    }


    void loadDataRetroFit(){

        Call<List<Pics>> call =  APIClient.getApiClient().getPosts();

        call.enqueue(new Callback<List<Pics>>() {
            @Override
            public void onResponse(Call<List<Pics>> call, retrofit2.Response<List<Pics>> response) {

                picsList = response.body();

                if(response.body().size()!=0){

                    recyclerAdapter = new RecyclerAdapter(MainActivity.this,picsList);
                    recyclerView.setAdapter(recyclerAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pics>> call, Throwable t) {

            }
        });
        
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list",(ArrayList<? extends Parcelable>) picsList);
    }

}