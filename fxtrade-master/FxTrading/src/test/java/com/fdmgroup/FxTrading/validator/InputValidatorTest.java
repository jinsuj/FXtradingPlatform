package com.fdmgroup.FxTrading.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputValidatorTest {

    InputValidator inputValidator;

    @Before
    public void init(){
        inputValidator = new InputValidator();
    }

    @Test
    public void testUsernameValidationWithValidUsernames(){
        assertTrue(inputValidator.validateEmail("username@what.com"));
        assertTrue(inputValidator.validateEmail("somename123@google.gov"));
        assertTrue(inputValidator.validateEmail("test!?@test.org"));
    }

    @Test
    public void testUsernameValidationWithInvalidUsernames(){
        assertFalse(inputValidator.validateEmail("usernasadfsafjwlejglbiewoaoifnwoifnwafbawrme"));
        assertFalse(inputValidator.validateEmail("12"));
        assertFalse(inputValidator.validateEmail("no@way@this@works##%%&1"));
        assertFalse(inputValidator.validateEmail(null));
        assertFalse(inputValidator.validateEmail("bad@@@t.gov"));
        assertFalse(inputValidator.validateEmail("no@.gov"));
        assertFalse(inputValidator.validateEmail("@fbi.gov"));
    }

    @Test
    public void testNameValidationWithValidNames(){
        assertTrue(inputValidator.validateName("William"));
        assertTrue(inputValidator.validateName("O'Connor"));
        assertTrue(inputValidator.validateName("Name-WithHyphen"));
        assertTrue(inputValidator.validateName("Henry VII"));
        assertTrue(inputValidator.validateName("James James Jr."));
    }

    @Test
    public void testNameValidationWithInvalidNames(){
        assertFalse(inputValidator.validateName("Wi11iam"));
        assertFalse(inputValidator.validateName(null));
        assertFalse(inputValidator.validateName("TooMany  Spaces"));
        assertFalse(inputValidator.validateName("Random $ymbol"));
        assertFalse(inputValidator.validateName("WaytoolongWaytoolongWaytoolongWaytoolongWaytoolongWaytoolongWaytoolong"));
    }

}
