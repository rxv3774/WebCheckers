package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AITest {

    private AI ai;
    private Match match;

    @BeforeEach
    void setup() {
        ai = mock(AI.class);
        match = mock(Match.class);
    }

}