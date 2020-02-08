package com.epam.izh.rd.online.service.implementation;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.implementation.ObjectMapperFactoryImplementation;
import com.epam.izh.rd.online.service.PokemonFetchingService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class PokemonFetchingServiceImplementation implements PokemonFetchingService {
    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {

        Pokemon pokemon;
        URL url;
        try {
            url = new URL("https://pokeapi.co/api/v2/pokemon/" + name);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent","");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
                        while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            ObjectMapper objectMapper = new ObjectMapperFactoryImplementation().getObjectMapper();
            pokemon = objectMapper.readValue(content.toString(), Pokemon.class);

        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Pokemon " + name + " does not exist.");
        }
        return pokemon;

    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {

        URL url;
        byte [] pokemonImage = null;
        try {
            url = new URL(fetchByName(name).getImageUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            pokemonImage = new byte[connection.getContentLength()];
            connection.getInputStream().read(pokemonImage);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Pokemon " + name + " does not exist.");
        }
        return pokemonImage;
    }
}
