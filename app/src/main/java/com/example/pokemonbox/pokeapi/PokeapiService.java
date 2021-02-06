package com.example.pokemonbox.pokeapi;

import com.example.pokemonbox.models.Evolutions;
import com.example.pokemonbox.models.ListEvolutions;
import com.example.pokemonbox.models.PokemonDetails;
import com.example.pokemonbox.models.PokemonList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeapiService {

    //get pokemon generation by specify offset and limit
    @GET("pokemon")
    Call<PokemonList> getGeneration( @Query("offset") int offset , @Query("limit") int limit);

    //get pokemon details by specify pokemon id

    @GET("pokemon/{id}")
    Call<PokemonDetails> getPokemonDetails(@Path("id") int id);

    @GET("pokemon/{id}")
    Call<Evolutions> getPokemonEvolutionsDetails(@Path("id") int id);

}
