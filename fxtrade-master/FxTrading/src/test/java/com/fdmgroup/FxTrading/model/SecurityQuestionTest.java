package com.fdmgroup.FxTrading.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecurityQuestionTest {

    SecurityQuestion securityQuestion;

    @Before
    public void init(){


    }

    @Test
    public void getAnswerShouldReturnTheRightAnswerForTheQuestion(){
        assertEquals(securityQuestion.getAnswer(), "John");
    }

    @Test
    public void setAnswerShouldBeAbleToChangeTheAnswerToTheSecurityQuestion(){
        securityQuestion.setAnswer("Jonathon");
        assertEquals(securityQuestion.getAnswer(), "Jonathon");

    }


}
