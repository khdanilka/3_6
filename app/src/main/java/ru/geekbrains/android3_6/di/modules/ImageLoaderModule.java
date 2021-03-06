package ru.geekbrains.android3_6.di.modules;


import android.widget.ImageView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3_6.model.cache.ImageRealmCache;
import ru.geekbrains.android3_6.model.image.ImageLoader;
import ru.geekbrains.android3_6.model.image.android.ImageLoaderGlide;

@Singleton
@Module(includes = {ImageRealmCacheModule.class})
public class ImageLoaderModule {

    @Singleton
    @Provides
    public ImageLoader<ImageView> returnImageLoader(){
        return new ImageLoaderGlide(new ImageRealmCache());
    }
}
