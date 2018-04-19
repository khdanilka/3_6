package ru.geekbrains.android3_6.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.geekbrains.android3_6.di.modules.AppModule;
import ru.geekbrains.android3_6.di.modules.RepoModule;
import ru.geekbrains.android3_6.model.image.android.ImageLoaderGlide;
import ru.geekbrains.android3_6.presenter.MainPresenter;
import ru.geekbrains.android3_6.view.MainActivity;

@Singleton
@Component(modules = {AppModule.class, RepoModule.class})
public interface AppComponent
{
    void inject(MainActivity activity);
    void inject(MainPresenter presenter);
}
