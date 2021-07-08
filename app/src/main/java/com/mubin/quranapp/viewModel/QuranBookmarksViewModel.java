package com.mubin.quranapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.mubin.quranapp.room.QuranDatabase;

public class QuranBookmarksViewModel extends AndroidViewModel {

    private QuranDatabase quranDatabase;

    public QuranBookmarksViewModel(@NonNull Application application) {
        super(application);
//        quranDatabase = QuranDatabase.getQuranDatabase(application);
    }

//    public Flowable<List<Result>> loadWatchlist() {
//        return quranDatabase.quranDao().getBookmarks();
//    }
//
//    public Completable removeTvShowFromWatchlist(Result result) {
//        return quranDatabase.quranDao().removeFromBookmarks(result);
//    }

}
