package com.mrntlu.unittesting2.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {

    public static final String TIME_STAMP_1="05-2019";
    public static final String TIME_STAMP_2="04-2019";

    /*
    Compare two equal Notes
     */
    @Test
    void isNotesEqual_identicalProperties_returnTrue() throws Exception{
        Note note1=new Note("Note #1","This is note #1",TIME_STAMP_1);
        note1.setId(1);
        Note note2=new Note("Note #1","This is note #1",TIME_STAMP_1);
        note2.setId(1);

        assertEquals(note1,note2);
        System.out.println("The notes are equal");
    }

    /*
    Compare notes with 2 different ids
     */
    @Test
    void isNotesEqual_differentIds_returnFalse() throws Exception {
        //Arrange
        Note note1=new Note("Note #1","This is note #1",TIME_STAMP_1);
        note1.setId(1);
        Note note2=new Note("Note #1","This is note #1",TIME_STAMP_1);
        note2.setId(2);

        //Act

        //Assert
        assertNotEquals(note1,note2);
        System.out.println("The notes are not equal, id's are not the same");
    }

    /*
    Compare two notes with different timestamps
     */

    @Test
    void isNotesEqual_differentTimestamps_returnTrue() throws Exception {
        //Arrange
        Note note1=new Note("Note #1","This is note #1",TIME_STAMP_1);
        note1.setId(1);
        Note note2=new Note("Note #1","This is note #1",TIME_STAMP_2);
        note2.setId(2);

        //Act

        //Assert
        assertNotEquals(note1,note2);
        System.out.println("The notes are equal");
    }

    /*
    Compare two notes with different titles
     */
    @Test
    void isNotesEqual_differentTitle_returnFalse() throws Exception {
        //Arrange
        Note note1=new Note("Note #1","This is note #1",TIME_STAMP_1);
        note1.setId(1);
        Note note2=new Note("Note #2","This is note #1",TIME_STAMP_2);
        note2.setId(2);

        //Act

        //Assert
        assertNotEquals(note1,note2);
        System.out.println("The notes are not equal, titles are different");
    }

    /*
    Compare two notes with different content
     */
    @Test
    void isNotesEqual_differentContent_returnFalse() throws Exception {
        //Arrange
        Note note1=new Note("Note #1","This is note #1",TIME_STAMP_1);
        note1.setId(1);
        Note note2=new Note("Note #1","This is note #2",TIME_STAMP_2);
        note2.setId(2);

        //Act

        //Assert
        assertNotEquals(note1,note2);
        System.out.println("The notes are not equal, contents are different");
    }
}
