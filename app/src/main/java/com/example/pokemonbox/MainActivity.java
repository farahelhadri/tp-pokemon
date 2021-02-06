package com.example.pokemonbox;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pokemonbox.models.Pokemon;
import com.example.pokemonbox.models.PokemonList;
import com.example.pokemonbox.pokeapi.PokeapiService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import static com.example.pokemonbox.pokeapi.Utils.getBase;

public class MainActivity extends AppCompatActivity {


    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListPokemonAdapter listPokemonAdapter;
    private ImageView gen1,gen2,gen3,gen4,gen5,gen6,gen7,gen8;
    List<Integer> cles;
    List<ImageView> generations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gen1= (ImageView) findViewById(R.id.gen1);
        gen2= (ImageView) findViewById(R.id.gen2);
        gen3= (ImageView) findViewById(R.id.gen3);
        gen4= (ImageView) findViewById(R.id.gen4);
        gen5= (ImageView) findViewById(R.id.gen5);
        gen6= (ImageView) findViewById(R.id.gen6);
        gen7= (ImageView) findViewById(R.id.gen7);
        gen8= (ImageView) findViewById(R.id.gen8);

        generations=new ArrayList<ImageView>();
        cles=new ArrayList<Integer>();

        Collections.addAll(generations, gen1, gen2, gen3, gen4, gen5, gen6, gen7, gen8);
        Collections.addAll(cles, 1, 0, 0, 0, 0, 0, 0, 0);

        listPokemonAdapter = new ListPokemonAdapter(this);
        recyclerView.setAdapter(listPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        retrofit = getBase();
        getGeneration(0,151);


        gen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
                gen1.setImageResource(R.drawable.blue);
                getGeneration(0,151);
                cles.set(0,1);

            }
        });


        gen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
                gen2.setImageResource(R.drawable.blue);
                getGeneration(151,251);
                cles.set(1,1);
            }
        });


        gen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
                gen3.setImageResource(R.drawable.blue);
                getGeneration(251,386);
                cles.set(2,1);
            }
        });

        gen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
                gen4.setImageResource(R.drawable.blue);
                getGeneration(386,493);
                cles.set(3,1);
            }
        });

        gen5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
                gen5.setImageResource(R.drawable.blue);
                getGeneration(493,649);
                cles.set(4,1);
            }
        });

        gen6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
                gen6.setImageResource(R.drawable.blue);
                getGeneration(649,721);
                cles.set(5,1);
            }
        });

        gen7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
                gen7.setImageResource(R.drawable.blue);
                getGeneration(721,809);
                cles.set(6,1);
            }
        });

        gen8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
                gen8.setImageResource(R.drawable.blue);
                getGeneration(809,898);
                cles.set(7,1);
            }
        });


    }

    private void changeImage(){

        for(int i=0;i<8;i++){
            if(cles.get(i)==1) {
                generations.get(i).setImageResource(R.drawable.red);
                cles.set(i,0);
            }
        }
    }
    private void getGeneration(int offset,int limit) {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonList> pokemonRespuestaCall = service.getGeneration(offset, limit);

        pokemonRespuestaCall.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {

                if (response.isSuccessful()) {

                    PokemonList pokemonList = response.body();
                    ArrayList<Pokemon> listPokemon = pokemonList.getResults();
                    listPokemonAdapter.changeLisPokemon(listPokemon);

                } else {
                    Log.e("Pokemon", " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                Log.e("Pokemon", " onFailure: " + t.getMessage());
            }
        });
    }

}

