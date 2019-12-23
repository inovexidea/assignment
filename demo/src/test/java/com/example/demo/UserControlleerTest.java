package com.example.demo;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.demo.controller.UserController;
import com.example.demo.service.user.RoleService;
import com.example.demo.service.user.UserService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControlleerTest {

	@Mock
	private UserService userService;
	
	@Mock
	private RoleService roleService;
	private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void testHelloWorldJson() throws Exception {
        mockMvc.perform(get("/api/user-login")
                .accept(MediaType.APPLICATION_JSON)
                .header("authorization" , "Basic ZGVtbzpkZW1vQDEyMw==")
                .param("username", "faruk")
                .param("password", "1234576") )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("RESPONSE DATA NOT FOUND")))
                .andExpect(jsonPath("$.statusCode", Matchers.is(404)));
        
        verify(userService).searchUserByNameAndPassword("faruk","1b8418990a7b43826067912651b7c71d");
        verify(roleService).findRoleById("faruk");
    }

}
