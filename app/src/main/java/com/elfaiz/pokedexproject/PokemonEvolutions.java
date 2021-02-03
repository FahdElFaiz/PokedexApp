package com.elfaiz.pokedexproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elfaiz.pokedexproject.Common.Common;
import com.elfaiz.pokedexproject.Model.NextEvolution;
import com.elfaiz.pokedexproject.Model.Pokemon;
import com.elfaiz.pokedexproject.Model.PrevEvolution;

import java.util.List;


public class PokemonEvolutions extends Fragment {

    ImageView pokemon_img;
    TextView pokemon_name,pokemon_next_evolution,pokemon_prev_evolution;



    static PokemonEvolutions instance;

    public static PokemonEvolutions getInstance(){
        if(instance==null)
            instance=new PokemonEvolutions();
        return instance;
    }


    public PokemonEvolutions() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View itemView= inflater.inflate(R.layout.fragment_pokemon_evolutions, container, false);

        Pokemon pokemon;
        int position=0;
        if(getArguments().get("num")==null){
            position=getArguments().getInt("position");
            pokemon= Common.commonPokemonList.get(getArguments().getInt("position"));}
        else
            pokemon=null;

        pokemon_img=(ImageView) itemView.findViewById(R.id.pokemon_image);
        pokemon_name=(TextView) itemView.findViewById(R.id.name);
        pokemon_next_evolution=(TextView) itemView.findViewById(R.id.txt_next_evolution);
        pokemon_prev_evolution=(TextView) itemView.findViewById(R.id.txt_prev_evolution);
        Button btn_details=(Button) itemView.findViewById(R.id.btn_details);

        final int finalPosition = position;
        btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment evolutionFragment = new PokemonDetail().getInstance();
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


        List<PrevEvolution> prev = pokemon.getPrev_evolution();

        List<NextEvolution> next = pokemon.getNext_evolution();


        pokemon_name.setText(pokemon.getName());

        String prevv = "";
        String nextt="";

        if (prev==null) {

        } else {
            for (int i = 0; i < prev.size()-1; i++) {
                prevv += prev.get(i).getName() + ", ";
            }
            pokemon_prev_evolution.setText(prevv.concat(prev.get(prev.size()-1).getName()));
        }

        if (next==null) {

        } else {
            for (int i = 0; i < next.size()-1; i++) {
                nextt += next.get(i).getName() + ", ";
            }
            pokemon_next_evolution.setText(nextt.concat(next.get(next.size()-1).getName()));
        }


    }
}