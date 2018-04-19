package ru.geekbrains.android3_6.model.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.geekbrains.android3_6.model.entity.User;
import ru.geekbrains.android3_6.model.entity.Repository;

public interface ApiService
{
    @GET("users/{user}")
    Observable<User> getUser(@Path("user") String userName);

    @GET("users/{user}/repos")
    Observable<List<Repository>> getUserRepos(@Path("user") String userName);
}
