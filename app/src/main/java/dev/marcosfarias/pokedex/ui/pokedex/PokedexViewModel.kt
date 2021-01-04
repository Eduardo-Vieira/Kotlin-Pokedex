package dev.marcosfarias.pokedex.ui.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.ui.pokedex.repository.PokedexRepository
import kotlinx.coroutines.launch
import java.io.IOException

class PokedexViewModel(private val repository: PokedexRepository) : ViewModel() {

    private val mListPokemon = MutableLiveData<List<Pokemon>>()
    var listPokemon:LiveData<List<Pokemon>> = mListPokemon

    private suspend fun getLocalData() {
        mListPokemon.value = repository.getLocalPokemonList()
    }

    fun getPokemonList() =
        viewModelScope.launch {
            try {
                val result = repository.getRemotePokemonList()
                if (result.isSuccessful) {
                    result.body()?.let {
                       if(it.isNotEmpty()) {
                           repository.addPokemonDAO(it)
                           mListPokemon.value = result.body()
                       } else
                           getLocalData()
                    }
                } else
                    getLocalData()

            } catch (exception: IOException){
                getLocalData()
            }
        }
}
