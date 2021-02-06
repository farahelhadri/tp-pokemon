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


public class ListPokemonAdapter extends RecyclerView.Adapter<ListPokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;
    private Context context;

    public ListPokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.namePokemon.setText(p.getName());
        Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/"+p.getNumber()+".png").into(holder.imagePokemon);
        holder.imagePokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Details.class);
                intent.putExtra("id",p.getNumber());
                intent.putExtra("name",p.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void changeLisPokemon(ArrayList<Pokemon> listPokemon) {
        dataset= listPokemon;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagePokemon;
        private TextView namePokemon;

        public ViewHolder(View itemView) {
            super(itemView);

            imagePokemon = (ImageView) itemView.findViewById(R.id.image);
            namePokemon = (TextView) itemView.findViewById(R.id.pokemon_name);
        }
    }
}
