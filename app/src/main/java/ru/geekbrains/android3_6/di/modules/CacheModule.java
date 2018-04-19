package ru.geekbrains.android3_6.di.modules;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3_6.model.cache.AACache;
import ru.geekbrains.android3_6.model.cache.ICache;
import ru.geekbrains.android3_6.model.cache.RealmCache;

@Singleton
@Module
public class CacheModule
{
    @Provides
    @Named("realm")
    public ICache cache()
    {
        return new RealmCache();
    }

    @Provides
    @Named("aa")
    public ICache cacheAa() {return new AACache();}
}
