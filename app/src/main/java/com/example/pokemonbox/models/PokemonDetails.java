package com.example.pokemonbox.models;

import java.util.List;

public class PokemonDetails {

    private int height;
    private int weight;
    private List<TypesItem> types;
    private String name;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<TypesItem> getTypes() {
        return types;
    }

    public void setTypes(List<TypesItem> types) {
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
