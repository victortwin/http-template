package com.epam.izh.rd.online.service.implementation;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFightingClubService;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PokemonFightingClubServiceImplementation implements PokemonFightingClubService {
    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {

        Pokemon from = p1.getPokemonId() > p2.getPokemonId() ? p1 : p2;
        Pokemon to = p1.getPokemonId() < p2.getPokemonId() ? p1 : p2;
        Pokemon pokemonBuffer;
        while (true) {
            doDamage(from, to);
            if (to.getHp() <= 0) {
                return from;
            } else {
                pokemonBuffer = from;
                from = to;
                to = pokemonBuffer;
            }
        }
    }

    @Override
    public void showWinner(Pokemon winner) {
        PokemonFetchingServiceImplementation pokemonFetchingService = new PokemonFetchingServiceImplementation();
        byte[] pokemonImage = pokemonFetchingService.getPokemonImage(winner.getPokemonName());
        String path = "src/winner.png";
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path))) {
            out.write(pokemonImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {

        short attack = from.getAttack();
        short defense = to.getDefense();
        short hp = to.getHp();
        short damage = attack > defense ? (short) (attack - defense) : 0;

        to.setHp((short) (hp - damage));
    }
}
