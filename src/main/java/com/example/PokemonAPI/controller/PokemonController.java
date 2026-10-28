package com.example.PokemonAPI.controller;

import com.example.PokemonAPI.model.Pokemon;
import com.example.PokemonAPI.service.PokemonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokemones")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public List<Pokemon> listar() {
        return pokemonService.obtenerTodos();
    }

    @PostMapping
    public Pokemon crear(@Valid @RequestBody Pokemon pokemon) {
        return pokemonService.crear(pokemon);
    }

    @GetMapping("/{id}")
    public Pokemon obtenerPorId(@PathVariable Long id) {
        return pokemonService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Optional<Pokemon> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Pokemon pokemon) {
        return pokemonService.actualizar(id, pokemon);
    }

    @PatchMapping("/{id}")
    public Optional<Pokemon> actualizarParcialmente(
            @PathVariable Long id,
            @RequestBody Pokemon pokemon) {
        return pokemonService.actualizarParcialmente(id, pokemon);
    }

    @DeleteMapping("/{id}")
    public void eliminarPokemon(@PathVariable Long id) {
        pokemonService.eliminarPokemon(id);
    }
}