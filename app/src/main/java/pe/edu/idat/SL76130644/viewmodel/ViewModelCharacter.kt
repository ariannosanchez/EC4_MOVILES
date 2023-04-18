package pe.edu.idat.SL76130644.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.edu.idat.SL76130644.model.Character
import pe.edu.idat.SL76130644.retrofit.RetrofitBuilder

class ViewModelCharacter : ViewModel() {
    val characters : MutableLiveData<List<Character>> = MutableLiveData()
    val isError: MutableLiveData<Boolean> = MutableLiveData(false)
    fun getCharacters(){
        isError.value = false

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitBuilder.apiService.getCharacters()
                withContext(Dispatchers.Main){
                    characters.postValue(response)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    isError.value = true
                }
            }
        }
    }
}