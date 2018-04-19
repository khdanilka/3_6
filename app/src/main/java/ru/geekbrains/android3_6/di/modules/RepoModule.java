package ru.geekbrains.android3_6.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3_6.model.api.ApiService;
import ru.geekbrains.android3_6.model.cache.ICache;
import ru.geekbrains.android3_6.model.repo.UsersRepo;

@Singleton
@Module(includes = {ApiModule.class, CacheModule.class})
public class RepoModule
{
    @Singleton
    @Provides
    public UsersRepo usersRepo(ApiService apiService, ICache cache)
    {
        return new UsersRepo(apiService, cache);
    }
}
