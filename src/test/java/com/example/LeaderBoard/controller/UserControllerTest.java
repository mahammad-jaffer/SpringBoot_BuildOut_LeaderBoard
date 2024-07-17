package com.example.LeaderBoard.controller;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.example.LeaderBoard.service.LeaderBoardService;


@SpringBootTest(classes = {UserController.class})
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@AutoConfigureMockMvc
@DirtiesContext
@ActiveProfiles("test")
public class UserControllerTest {
 
    private MockMvc mvc;

    @MockBean
    private LeaderBoardService leaderBoardService;

    @InjectMocks
    private UserController userController;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void NoUsersFoundTest() throws Exception{
        URI uri = UriComponentsBuilder
                    .fromPath("/users")
                    .build().toUri();

        assertEquals("/users", uri.toString());

        MockHttpServletResponse response = mvc.perform(
            get(uri.toString()))
            .andReturn().getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    public void UserNotFoundTest() throws Exception {
        URI uri = UriComponentsBuilder.fromPath("/user/123").build().toUri();
        MockHttpServletResponse response = mvc.perform(get(uri.toString())).andReturn().getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }



    
}
