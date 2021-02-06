package com.example.pokemonbox;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonbox.models.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class EvolutionPokemonAdapter extends RecyclerView.Adapter<EvolutionPokemonAdapter.ViewHolder> {

    private ArrayList<String> dataset;
    private Context context;

    public EvolutionPokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evolution_item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
           Picasso.get().load(dataset.get(position)).into(holder.imagePokemon);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void changeLisPokemon(ArrayList<String> evolutions) {
        dataset= evolutions;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagePokemon;

        public ViewHolder(View itemView) {
            super(itemView);

            imagePokemon = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
