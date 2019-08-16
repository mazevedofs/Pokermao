package com.marina.pokermao.api

import com.marina.pokermao.model.HealthResponse
import com.marina.pokermao.model.Pokemon
import com.marina.pokermao.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.*

interface PokemonService {

    @GET("/api/pokemon/health")
    fun checkHealth() : Call<HealthResponse>

    @GET("/api/pokemon")
    fun getPokemons(
        @Query("sort") sort: String,
        @Query("size") size: Int
    ) : Call<PokemonResponse>

    @PUT("/api/pokemon")
    fun updatePokemon(
        @Body pokemon: Pokemon
    ) : Call<Pokemon>

    @GET("/api/pokemon")
    fun getPokemon(
        @Path("number") size: String
    ) : Call<Pokemon>

}
