package com.example.PokemonAPI.service;

import com.example.PokemonAPI.model.Pokemon;
import com.example.PokemonAPI.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;
    public List<Pokemon> obtenerTodos(){
        return pokemonRepository.findAll();
    }

    public Pokemon obtenerPorId(Long id){
        return pokemonRepository.findById(id).orElse(null);
    }

    public Pokemon crear(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    public Optional<Pokemon> actualizar(Long id, Pokemon pokemon){
        return pokemonRepository.findById(id).map(recursoexist -> {
            recursoexist.setNombre(pokemon.getNombre());
            recursoexist.setTipo(pokemon.getTipo());
            recursoexist.setNivel(pokemon.getNivel());
            recursoexist.setAtaque(pokemon.getAtaque());
            recursoexist.setDefensa(pokemon.getDefensa());
            recursoexist.setDescripcion(pokemon.getDescripcion());
            recursoexist.setImagenUrl(pokemon.getImagenUrl());
            return pokemonRepository.save(recursoexist);
        });
    }
    public Optional<Pokemon> actualizarParcialmente(Long id, Pokemon pokemon){
        return pokemonRepository.findById(id).map(recursoexist -> {
            if(pokemon.getNombre() != null)recursoexist.setNombre(pokemon.getNombre());
            if(pokemon.getTipo() != null)recursoexist.setTipo(pokemon.getTipo());
            if(pokemon.getNivel() != null)recursoexist.setNivel(pokemon.getNivel());
            if(pokemon.getAtaque() != null) recursoexist.setAtaque(pokemon.getAtaque());
            if(pokemon.getDefensa() != null)recursoexist.setDefensa(pokemon.getDefensa());
            if(pokemon.getDescripcion() != null) recursoexist.setDescripcion(pokemon.getDescripcion());
            if(pokemon.getImagenUrl() != null) recursoexist.setImagenUrl(pokemon.getImagenUrl());
            return pokemonRepository.save(recursoexist);

        });
    }
    public void eliminarPokemon(Long id) {
        pokemonRepository.deleteById(id);
    }

}
