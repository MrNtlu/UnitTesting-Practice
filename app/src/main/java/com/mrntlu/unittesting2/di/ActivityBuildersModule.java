package com.mrntlu.unittesting2.di;

import com.mrntlu.unittesting2.ui.note.NoteActivity;
import com.mrntlu.unittesting2.ui.noteslist.NotesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract NotesListActivity contributeNotesListActivity();

    @ContributesAndroidInjector
    abstract NoteActivity contributeNoteActivity();
}
