package com.example.pokemonbox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pokemonbox.MainActivity;
import com.example.pokemonbox.R;
import com.example.pokemonbox.models.Pokemon;
import com.example.pokemonbox.models.PokemonDetails;
import com.example.pokemonbox.models.PokemonList;
import com.example.pokemonbox.models.TypesItem;
import com.example.pokemonbox.pokeapi.PokeapiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.pokemonbox.pokeapi.Utils.getBase;


public class DescriptionFragment extends Fragment {

    private ImageView back,pokemonPic;
    private TextView poke_number,name,height,weight,types;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_description, container, false);
         back =   root.findViewById(R.id.back);
         pokemonPic =  root.findViewById(R.id.image_pokemon);
         name = root.findViewById(R.id.pokemon_name);
         poke_number = root.findViewById(R.id.pokemon_nbr);
         height = root.findViewById(R.id.height);
         weight = root.findViewById(R.id.weight);
         types = root.findViewById(R.id.types);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            int id = extras.getInt("id");
            Retrofit retrofit = getBase();
            PokeapiService service = retrofit.create(PokeapiService.class);
            Call<PokemonDetails> pokemonDetails = service.getPokemonDetails(id);
            pokemonDetails.enqueue(new Callback<PokemonDetails>() {
                @Override
                public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {
                    if (response.isSuccessful()) {
                        String all_types="";
                        name.setText(response.body().getName());
                        poke_number.setText("### "+id);
                        height.setText(String.valueOf(response.body().getHeight()));
                        weight.setText(String.valueOf(response.body().getWeight()));
                        for (TypesItem t : response.body().getTypes()) { all_types=all_types+ "-"+ t.getType().getName();}
                        types.setText(all_types);
                        Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/"+id+".png").into(pokemonPic);
                    } else {
                        Log.e("PokemonDetails", " onResponse: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<PokemonDetails> call, Throwable t) {
                    Log.e("PokemonDetails", " onFailure: " + t.getMessage());
                }
            });
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }


}