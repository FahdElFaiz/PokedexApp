package com.elfaiz.pokedexproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.elfaiz.pokedexproject.Adapter.PokemonListAdapter;
import com.elfaiz.pokedexproject.Common.Common;
import com.elfaiz.pokedexproject.Common.ItemOffsetDecoration;
import com.elfaiz.pokedexproject.Model.Pokedex;
import com.elfaiz.pokedexproject.Retrofit.IPokemonDex;
import com.elfaiz.pokedexproject.Retrofit.RetrofitClient;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class PokemonList extends Fragment {

    IPokemonDex iPokemonDex;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    RecyclerView pokemon_list_recyclerview;
    LottieAnimationView animation;

    static PokemonList instance;

    public PokemonList() {
        Retrofit retrofit= RetrofitClient.getInstace();
        iPokemonDex=retrofit.create(IPokemonDex.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_pokemon__list, container, false);

        animation=(LottieAnimationView) view.findViewById(R.id.anim);
        pokemon_list_recyclerview=(RecyclerView) view.findViewById(R.id.pokemon_list_recyclerview);
        pokemon_list_recyclerview.setHasFixedSize(true);
        pokemon_list_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        ItemOffsetDecoration itemOffsetDecoration=new ItemOffsetDecoration(getActivity(),R.dimen.spacing);
        pokemon_list_recyclerview.addItemDecoration(itemOffsetDecoration);
        fetchData(animation);
        return view;
    }

    private void fetchData(final LottieAnimationView anim) {
        compositeDisposable.add(iPokemonDex.getListPokemon()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Pokedex>() {
            @Override
            public void accept(Pokedex pokedex) throws Exception {
                Common.commonPokemonList=pokedex.getPokemon();
                PokemonListAdapter adapter=new PokemonListAdapter(getActivity(),Common.commonPokemonList);

                pokemon_list_recyclerview.setAdapter(adapter);

         anim.setVisibility(View.GONE);
            }
        }));
    }
}