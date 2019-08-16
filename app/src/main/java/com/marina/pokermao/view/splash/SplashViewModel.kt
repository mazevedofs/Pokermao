package com.marina.pokermao.view.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marina.pokermao.repository.PokemonRepository


class SplashViewModel(val pokemonRepository: PokemonRepository) : ViewModel() {

    val messageError = MutableLiveData<String>()

    fun checkHealth() {

        pokemonRepository.checkHealth({ //onComplete
            messageError.value = "" //msg vazia == deu certo
        }, {
            //onError
            messageError.value = it.message
        })

    }
}
