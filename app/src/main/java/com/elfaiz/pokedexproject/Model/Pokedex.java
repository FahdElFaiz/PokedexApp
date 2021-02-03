package com.elfaiz.pokedexproject.Model;
import java.util.*;


public class Pokedex {
    public List<Pokemon> pokemon;

    public Pokedex(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public Pokedex() {
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }
}
