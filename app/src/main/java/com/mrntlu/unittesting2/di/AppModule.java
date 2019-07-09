package com.mrntlu.unittesting2.di;

import android.app.Application;

import androidx.room.Room;

import com.mrntlu.unittesting2.persistence.NoteDao;
import com.mrntlu.unittesting2.persistence.NoteDatabase;
import com.mrntlu.unittesting2.repository.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.mrntlu.unittesting2.persistence.NoteDatabase.DATABASE_NAME;

@Module
public class AppModule {

    @Singleton
    @Provides
    static NoteDatabase provideNoteDatabase(Application application){
        return Room.databaseBuilder(
                application,
                NoteDatabase.class,
                DATABASE_NAME
        ).build();
    }

    @Singleton
    @Provides
    static NoteDao provideNoteDao(NoteDatabase noteDatabase){
        return noteDatabase.getNoteDao();
    }

    @Singleton
    @Provides
    static NoteRepository provideNoteRepository(NoteDao noteDao){
        return new NoteRepository(noteDao);
    }
}
