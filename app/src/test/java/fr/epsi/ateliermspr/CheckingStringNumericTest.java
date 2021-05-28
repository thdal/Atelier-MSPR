package fr.epsi.ateliermspr;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckingStringNumericTest {
    @Test
    public void is_string_numeric(){
        String actuel = "123";
        assertTrue(CheckingStringNumeric.isNumenic(actuel));
    }

    @Test
    public void is_not_string_numeric(){
        String actuel = "123a";
        assertFalse(CheckingStringNumeric.isNumenic(actuel));
    }
}
