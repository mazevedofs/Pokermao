package com.marina.pokermao.repository

import com.marina.pokermao.model.Pokemon

interface PokemonRepository {

    fun checkHealth(
        onComplete:() -> Unit,
        onError: (t: Throwable) -> Unit
    )

    fun getPokemons(
        sort: String,
        size: Int,
        onComplete:(pokemons: List<Pokemon>) -> Unit,
        onError: (t: Throwable) -> Unit
    )

    fun updatePokemon(
        pokemon: Pokemon,
        onComplete:(Pokemon?) -> Unit,
        onError: (t: Throwable) -> Unit
    )
}
