package com.github.randomtext.text;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Alexey Koptenkov on 16/06/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultRandomTextServiceTest {

    @Mock
    private APIService service;

    @InjectMocks
    private DefaultRandomTextService victim;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldCompute() throws Exception {

        victim.compute(0, 0, 0, 0);
    }

}