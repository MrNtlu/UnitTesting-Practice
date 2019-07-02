package com.mrntlu.unittesting2;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.mrntlu.unittesting2.persistence.NoteDao;
import com.mrntlu.unittesting2.persistence.NoteDatabase;

import org.junit.After;
import org.junit.Before;

public abstract class NoteDatabaseTest {
    //System under test
    private NoteDatabase noteDatabase;

    public NoteDao getNoteDao(){
        return noteDatabase.getNoteDao();
    }

    @Before
    public void init(){
        noteDatabase= Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                NoteDatabase.class
        ).build();
    }

    @After
    public void finish(){
        noteDatabase.close();
    }
}
