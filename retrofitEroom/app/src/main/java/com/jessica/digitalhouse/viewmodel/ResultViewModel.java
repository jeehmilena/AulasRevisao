package com.jessica.digitalhouse.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jessica.digitalhouse.model.Movie;
import com.jessica.digitalhouse.model.Result;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.jessica.digitalhouse.database.Database.getDatabase;
import static com.jessica.digitalhouse.network.RetrofitService.getApiService;
import static com.jessica.digitalhouse.util.AppConectionUtil.isConnected;

public class ResultViewModel extends AndroidViewModel {
    public MutableLiveData<List<Result>> resultsLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> resultLiveDataError = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public ResultViewModel(@NonNull Application application) {
        super(application);
    }

    public void getMovies(String apikey, String language) {

        if (isConnected(getApplication())) {
            disposable.add(
                    getApiService().getMovies(apikey, language)
                            //faz a inserção do filme no banco
                            .map((Movie movies) -> {
                                return salveMovies(movies);
                             })
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
                                        Log.i("LOG", "Error: " + throwable.getMessage());
                                    })
            );
        } else {

            disposable.add(
                    getDatabase(getApplication()).movieDAO().getAll()
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe((Subscription disposable) -> {
                                isLoading.setValue(true);
                            })
                            .doOnTerminate(() -> {
                                isLoading.setValue(false);
                            })
                            .subscribe((List<Result> results) ->
                                            resultsLiveData.setValue(results)
                                    , (Throwable throwable) -> {
                                        Log.i("LOG", "Error: " + throwable.getMessage());
                                    })
            );

        }
    }

    //método que chama o método de de inserção no banco
    private Movie salveMovies(Movie movie){
        getDatabase(getApplication()).movieDAO().insert(movie.getResults());
        return movie;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
