/**
 * Dana.id.
 * Copyright (c) 2018‐2021 All Rights Reserved.
 */
package TestCase.Invitation;

import TestLogic.InvitationTestLogic;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


/**
 * @author Riza Nansuri
 * @version $Id: InvitationTest.java, v 0.1 2021‐12‐27 11.54 Riza Nansuri Exp $$
 */
public class InvitationTest {

    InvitationTestLogic invitationTestLogic = new InvitationTestLogic();

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://rumahundangan.tunnelto.dev/apis";
    }

    @DisplayName("Verify submit wishes is working well")
    @ParameterizedTest
    @ValueSource(strings = { "ini satu", "ini dua"})
    public void submitWishesTestCase(String candidate){

        // 1. Do Submit new wishes
        Response response = invitationTestLogic.submitWishesTest("TEST0001", candidate);
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("true", response.jsonPath().getString("success"));
    }

    @DisplayName("Verify submit wishes then query the result")
    @Test
    public void submitQueryTestCase(){

        String invitationId = "TEST_FRAMEWORK";

        // 1. Do Submit new wishes
        Response response = invitationTestLogic.submitWishesTest(invitationId,"TEST");
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("true", response.jsonPath().getString("success"));

        // 2. Do Query the wishes
        Response queryResponse = invitationTestLogic.queryWishesTest(invitationId);
        Assertions.assertEquals(200, queryResponse.statusCode());
        Assertions.assertEquals("true", queryResponse.jsonPath().getString("success"));
    }
}