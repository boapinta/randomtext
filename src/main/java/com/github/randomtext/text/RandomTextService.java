package com.github.randomtext.text;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by alexey on 6/15/17.
 */
public interface RandomTextService {

    @GET("/api/giberish/p-{id}/{min}-{max}")
    Observable<RandomTextResponse> getText(@Path("id") int id, @Path("min") int min, @Path("max") int max);
}
