package dev.marcosfarias.pokedex.ui.pokedex.repository

import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.network.PokemonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokedexRepository(private val pokemonDAO: PokemonDAO, private val pokemonService: PokemonService) {

    suspend fun getRemotePokemonList() = pokemonService.getPokemonList()

    suspend fun getLocalPokemonList()  =
        withContext(Dispatchers.IO){
            return@withContext pokemonDAO.all()
        }

    suspend fun addPokemonDAO(data:List<Pokemon>) =
        withContext(Dispatchers.IO){
            pokemonDAO.add(data)
        }
}