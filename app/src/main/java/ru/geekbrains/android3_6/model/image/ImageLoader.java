package ru.geekbrains.android3_6.model.image;

import android.support.annotation.Nullable;

import dagger.Module;
import dagger.Provides;

/**
 * Created by stanislav on 3/12/2018.
 */


public interface ImageLoader<T>
{
    void loadInto(@Nullable String url, T container);
}
