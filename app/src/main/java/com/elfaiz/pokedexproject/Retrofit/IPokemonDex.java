package com.elfaiz.pokedexproject.Retrofit;

import com.elfaiz.pokedexproject.Model.Pokedex;

import io.reactivex.Observable;

import retrofit2.http.GET;

public interface IPokemonDex {
    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();
}
