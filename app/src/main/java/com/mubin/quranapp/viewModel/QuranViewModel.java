package com.mubin.quranapp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mubin.quranapp.repository.QuranRepository;
import com.mubin.quranapp.response.QuranResponse;

public class QuranViewModel extends ViewModel {

    private QuranRepository quranRepository;

    public QuranViewModel(){
        quranRepository = new QuranRepository();
    }

    public LiveData<QuranResponse> getQuran(String q, int page){
        return quranRepository.getQuran(q,page);
    }

}
