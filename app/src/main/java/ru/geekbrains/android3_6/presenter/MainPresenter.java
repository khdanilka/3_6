package ru.geekbrains.android3_6.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ru.geekbrains.android3_6.model.entity.User;
import ru.geekbrains.android3_6.model.repo.UsersRepo;
import ru.geekbrains.android3_6.view.MainView;
import ru.geekbrains.android3_6.view.RepoRowView;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements IRepoListPresenter
{
    private Scheduler scheduler;
    @Inject UsersRepo usersRepo;

    private User user;

    public MainPresenter(Scheduler scheduler)
    {
        this.scheduler = scheduler;
    }

    @Override
    protected void onFirstViewAttach()
    {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public void loadInfo()
    {
        usersRepo.getUser("SupNacho")
                .subscribeOn(Schedulers.io())
                .subscribe(user ->
                {
                    this.user = user;
                    usersRepo.getUserRepos(user)
                            .subscribeOn(Schedulers.io())
                            .observeOn(scheduler)
                            .subscribe(userRepositories ->
                            {
                                this.user.setRepos(userRepositories);
                                getViewState().hideLoading();
                                getViewState().showAvatar(user.getAvatarUrl());
                                getViewState().setUsername(user.getLogin());
                                getViewState().updateRepoList();
                            }, throwable ->
                            {
                                Timber.e("Failed to get user repos", throwable);
                                getViewState().showError(throwable.getMessage());
                                getViewState().hideLoading();
                            });
                }, throwable ->
                {
                    Timber.e( "Failed to get user", throwable);
                    getViewState().showError(throwable.getMessage());
                    getViewState().hideLoading();
                });

    }

    public void onPermissionsGranted()
    {
        loadInfo();
    }

    @Override
    public void bindRepoListRow(int pos, RepoRowView rowView)
    {
        if (user != null)
        {
            rowView.setTitle(user.getRepos().get(pos).getName());
        }
    }

    @Override
    public int getRepoCount()
    {
        return user == null ? 0 : user.getRepos().size();
    }
}
