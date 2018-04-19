package ru.geekbrains.android3_6.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3_6.model.cache.ImageRealmCache;

@Module
public class ImageRealmCacheModule {

    @Singleton
    @Provides
    public ImageRealmCache getRealImageCache(){
        return new ImageRealmCache();
    }

}
