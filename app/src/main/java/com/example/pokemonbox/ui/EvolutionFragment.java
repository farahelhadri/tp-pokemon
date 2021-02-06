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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonbox.EvolutionPokemonAdapter;
import com.example.pokemonbox.ListPokemonAdapter;
import com.example.pokemonbox.MainActivity;
import com.example.pokemonbox.R;
import com.example.pokemonbox.models.Evolutions;
import com.example.pokemonbox.models.ListEvolutions;
import com.example.pokemonbox.models.PokemonDetails;
import com.example.pokemonbox.models.TypesItem;
import com.example.pokemonbox.pokeapi.PokeapiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.pokemonbox.pokeapi.Utils.getBase;


public class EvolutionFragment extends Fragment {

    private ImageView back,pokemonPic;
    private TextView poke_number,name;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_evolutions, container, false);
        back =   root.findViewById(R.id.back);
        pokemonPic =  root.findViewById(R.id.image_pokemon);
        name = root.findViewById(R.id.pokemon_name);
        poke_number = root.findViewById(R.id.pokemon_nbr);
        recyclerView = root.findViewById(R.id.recyclerView);
        EvolutionPokemonAdapter evolutionPokemonAdapter = new EvolutionPokemonAdapter(getContext());
        recyclerView.setAdapter(evolutionPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            String name_poke = extras.getString("name");
            int id = extras.getInt("id");
            name.setText(name_poke);
            poke_number.setText("##"+id);
            Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/"+id+".png").into(pokemonPic);
            Retrofit retrofit = getBase();
            PokeapiService service = retrofit.create(PokeapiService.class);
            Call<Evolutions> listEvolutionsCall = service.getPokemonEvolutionsDetails(id);
            listEvolutionsCall.enqueue(new Callback<Evolutions>() {
                @Override
                public void onResponse(Call<Evolutions> call, Response<Evolutions> response) {
                    if (response.isSuccessful()) {
                        ArrayList<String> evols = new ArrayList<>();
                        evols.add(response.body().getSprites().getBack_female());
                        System.out.println(response.body().getSprites().getBack_default());
                        evols.add(response.body().getSprites().getFront_female());
                        evols.add(response.body().getSprites().getBack_shiny_female());
                        evols.add(response.body().getSprites().getFront_shiny_female());
                        evols.add(response.body().getSprites().getBack_default());
                        evols.add(response.body().getSprites().getFront_default());
                        evols.add(response.body().getSprites().getFront_shiny());
                        evols.add(response.body().getSprites().getBack_shiny());
                        evols.removeAll(Collections.singletonList(null));
                        evolutionPokemonAdapter.changeLisPokemon(evols);
                    } else {
                        Log.e("PokemonEVDetails", " onResponse: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<Evolutions> call, Throwable t) {
                    Log.e("PokemonEVDetails", " onFailure: " + t.getMessage());
                }
            });

        }
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        return root;

}
}
