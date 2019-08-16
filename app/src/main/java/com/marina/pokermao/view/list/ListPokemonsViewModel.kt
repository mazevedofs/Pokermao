package com.marina.pokermao.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marina.pokermao.model.Pokemon
import com.marina.pokermao.repository.PokemonRepository

data class ListPokemonsViewModel(val pokemonRepository: PokemonRepository) : ViewModel() {


    val isLoading = MutableLiveData<Boolean>()
    val pokemons = MutableLiveData<List<Pokemon>>()
    val messageError = MutableLiveData<String>()


    fun getPokemons() {
        isLoading.value = true

        pokemonRepository.getPokemons(

            "number,asc", 150,
            onComplete = {
                isLoading.value = false
                pokemons.value = it
                messageError.value = ""

            }, onError = {
                isLoading.value = false
                pokemons.value = listOf()
                messageError.value = it.message
            }
        )
    }


}
