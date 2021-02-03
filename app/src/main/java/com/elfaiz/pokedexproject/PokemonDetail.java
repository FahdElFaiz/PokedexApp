package com.elfaiz.pokedexproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.elfaiz.pokedexproject.Common.Common;
import com.elfaiz.pokedexproject.Model.NextEvolution;
import com.elfaiz.pokedexproject.Model.Pokemon;
import com.elfaiz.pokedexproject.Model.PrevEvolution;

import java.util.ArrayList;
import java.util.List;


public class PokemonDetail extends Fragment {

    ImageView pokemon_img;
    TextView pokemon_name,pokemon_height,pokemon_weight,pokemon_type;



    static PokemonDetail instance;

    public static PokemonDetail getInstance(){
        if(instance==null)
            instance=new PokemonDetail();
        return instance;
    }


    public PokemonDetail() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View itemView= inflater.inflate(R.layout.fragment_pokemon_detail, container, false);

        Pokemon pokemon;
        int position=0;
        if(getArguments().get("num")==null){
              position=getArguments().getInt("position");
            pokemon= Common.commonPokemonList.get(getArguments().getInt("position"));}
        else
            pokemon=null;

        pokemon_img=(ImageView) itemView.findViewById(R.id.pokemon_image);
        pokemon_name=(TextView) itemView.findViewById(R.id.name);
        pokemon_height=(TextView) itemView.findViewById(R.id.height);
        pokemon_weight=(TextView) itemView.findViewById(R.id.weight);
        pokemon_type=(TextView) itemView.findViewById(R.id.txt_type);


        Button btn_evo=(Button) itemView.findViewById(R.id.btn_evolution);

        final int finalPosition = position;
        btn_evo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment evolutionFragment = new PokemonEvolutions().getInstance();
                Bundle bundle=new Bundle();
                bundle.putInt("position", finalPosition);
                evolutionFragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.list_pokemon_fragment, evolutionFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



                setDetailPokemon(pokemon);


        return itemView;
    }

    private void setDetailPokemon(Pokemon pokemon) {

        Glide.with(getActivity()).load(pokemon.getImg()).into(pokemon_img);


        pokemon_name.setText(pokemon.getName());
        pokemon_weight.setText(pokemon.getWeight());
        pokemon_height.setText(pokemon.getHeight());

        pokemon_type.setText(String.join(", ", pokemon.getType()));





    }
}