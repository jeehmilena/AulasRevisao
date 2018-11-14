package com.jessica.digitalhouse.network;

import com.jessica.digitalhouse.model.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface API {

    //metodo que retorna uma lista de filmes dos ultimos lancamentos
    @GET("movie/now_playing")
    Observable<Movie> getMovies(
        @Query("api_key") String api_key,
        @Query("language") String language
    );
}
