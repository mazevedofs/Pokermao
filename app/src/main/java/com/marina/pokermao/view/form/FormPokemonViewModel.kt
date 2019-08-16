package com.marina.pokermao.view.form

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marina.pokermao.model.Pokemon
import com.marina.pokermao.repository.PokemonRepository

data class FormPokemonViewModel(val pokemonRepository: PokemonRepository) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()


    fun updatePokemon(pokemon: Pokemon) {
        isLoading.value = true

        pokemonRepository.updatePokemon(
            pokemon = pokemon,
            onComplete = {
                isLoading.value = false
                messageResponse.value = "Dados Salvos"

            }, onError = {
                isLoading.value = false
                messageResponse.value = it.message
            }
        )
    }

}
