package com.mrntlu.unittesting2.di;

import com.mrntlu.unittesting2.NotesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract NotesListActivity contributeNotesListActivity();
}
