package com.mrntlu.unittesting2.repository;

import com.mrntlu.unittesting2.models.Note;
import com.mrntlu.unittesting2.persistence.NoteDao;
import com.mrntlu.unittesting2.ui.Resource;
import com.mrntlu.unittesting2.util.TestUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Not;

import io.reactivex.Single;

import static com.mrntlu.unittesting2.repository.NoteRepository.INSERT_FAILURE;
import static com.mrntlu.unittesting2.repository.NoteRepository.INSERT_SUCCESS;
import static com.mrntlu.unittesting2.repository.NoteRepository.NOTE_TITLE_NULL;
import static com.mrntlu.unittesting2.repository.NoteRepository.UPDATE_FAILURE;
import static com.mrntlu.unittesting2.repository.NoteRepository.UPDATE_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class NoteRepositoryTest {

    private static final Note NOTE1=new Note(TestUtil.TEST_NOTE_1);

    //system under test
    private NoteRepository noteRepository;

    //One way of mocking it
    /*
    @Mock
    private NoteDao noteDao;

    @BeforeEach
    public void initEach(){
        MockitoAnnotations.initMocks(this);
    }
    */
    //Second way of mocking it
    private NoteDao noteDao;

    @BeforeEach
    public void initEach(){
        noteDao= Mockito.mock(NoteDao.class);
        noteRepository=new NoteRepository(noteDao);
    }

    /*
    Insert note
    verify the correct method is called
    Confirm observer is triggered
    Confirm new rows are inserted
     */
    @Test
    void insertNote_returnRow() throws Exception {
        //Arrange
        final Long insertedRow=1L;
        final Single<Long> returnedData=Single.just(insertedRow);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);

        //Act
        final Resource<Integer> returnValue=noteRepository.insertNote(NOTE1).blockingFirst();

        //Assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        System.out.println("Returned value: "+returnValue.data);
        assertEquals(Resource.success(1,INSERT_SUCCESS), returnValue);

        //or test using RxJava
        /*noteRepository.insertNote(NOTE1)
                .test()
                .await()
                .assertValue(Resource.success(1,INSERT_SUCCESS));
        */
    }

    /*
    Insert note
    Failure(return -1)
     */
    @Test
    void insertNote_returnFailure() throws Exception {
        //Arrange
        final Long failedInsert=-1L;
        final Single<Long> returnedData=Single.just(failedInsert);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);

        //Act
        final Resource<Integer> returnValue=noteRepository.insertNote(NOTE1).blockingFirst();

        //Assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.error(null,INSERT_FAILURE), returnValue);
    }

    /*
    Insert note
    Null title
    Confirm throw exception
     */
    @Test
    void insertNote_nullTitle_throwException() throws Exception {
        Exception exception=assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final Note note=new Note(TestUtil.TEST_NOTE_1);
                note.setTitle(null);
                noteRepository.insertNote(note);
            }
        });
        assertEquals(NOTE_TITLE_NULL,exception.getMessage());
    }

    /*
    update note
    verify correct method is called
    confirm observer is trigger
    confirm number of rows updated
     */
    @Test
    void updateNote_returnNumRowsUpdated() throws Exception {
        //Arrange
        final int updatedRow=1;
        when(noteDao.updateNote(any(Note.class))).thenReturn(Single.just(updatedRow));

        //Act
        final Resource<Integer> returnedValue=noteRepository.updateNote(NOTE1).blockingFirst();

        //Assert
        verify(noteDao).updateNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.success(updatedRow,UPDATE_SUCCESS),returnedValue);
    }

    /*
    update note
    failure(-1)
     */
    @Test
    void updateNote_returnFailure() throws Exception {
        //Arrange
        final int failedInsert=-1;
        final Single<Integer> returnedData=Single.just(failedInsert);
        when(noteDao.updateNote(any(Note.class))).thenReturn(returnedData);

        //Act
        final Resource<Integer> returnedValue=noteRepository.updateNote(NOTE1).blockingFirst();

        //Assert
        verify(noteDao).updateNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.error(null,UPDATE_FAILURE),returnedValue);
    }

    /*
        update note
        null title
        throw exception
     */
    @Test
    void updateNote_nullTitle_throwException() throws Exception {
        Exception exception=assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final Note note=new Note(TestUtil.TEST_NOTE_1);
                note.setTitle(null);
                noteRepository.updateNote(note);
            }
        });
        assertEquals(NOTE_TITLE_NULL,exception.getMessage());
    }
}
