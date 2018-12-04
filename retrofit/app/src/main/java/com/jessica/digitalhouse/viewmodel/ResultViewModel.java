package com.jessica.digitalhouse.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jessica.digitalhouse.model.Movie;
import com.jessica.digitalhouse.model.Result;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.jessica.digitalhouse.network.RetrofitService.getApiService;

public class ResultViewModel extends AndroidViewModel {
    public MutableLiveData<List<Resu
    lt>> resultsLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> resultLiveDataError = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public ResultViewModel(@NonNull Application application) {
        super(application);
    }

    public void getMovies(String apikey, String language){

        disposable.add(
                getApiService().getMovies(apikey, language)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((Disposable disposable) -> {
                    isLoading.setValue(true);
                })
                .doOnTerminate(() -> {
                    isLoading.setValue(false);
                })
                .subscribe((Movie movieResponse) ->
                {
                    resultsLiveData.setValue(movieResponse.getResults());
                }
                , throwable -> {
                    Log.i("LOG", "Error: "+ throwable.getMessage());
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
