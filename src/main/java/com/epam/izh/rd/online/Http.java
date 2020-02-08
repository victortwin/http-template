package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.implementation.PokemonFetchingServiceImplementation;
import com.epam.izh.rd.online.service.implementation.PokemonFightingClubServiceImplementation;

public class Http {
    public static void main(String[] args) {
        PokemonFetchingServiceImplementation pokemonFetchingService = new PokemonFetchingServiceImplementation();
        PokemonFightingClubServiceImplementation pokemonFightingClubService =
                new PokemonFightingClubServiceImplementation();
        Pokemon firstPokemon = pokemonFetchingService.fetchByName("pikachu");
        Pokemon secondPokemon = pokemonFetchingService.fetchByName("slowpoke");
        Pokemon winner = pokemonFightingClubService.doBattle(firstPokemon, secondPokemon);
        pokemonFightingClubService.showWinner(winner);
    }
}
