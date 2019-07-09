package com.mrntlu.unittesting2.di;

import androidx.lifecycle.ViewModelProvider;

import com.mrntlu.unittesting2.ui.note.NoteViewModel;
import com.mrntlu.unittesting2.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel.class)
    public abstract NoteViewModel bindNoteViewModel(NoteViewModel noteViewModel);

}
