package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.IUserService;
import com.example.demo.vo.ResultObject;
import com.example.demo.vo.user.UserRequestVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private IUserService mockUserService;

    @InjectMocks
    private UserController userControllerUnderTest;

    @Test
    void testGetUserList() {
        // Setup
        // Configure IUserService.getUserList(...).
        final User user = new User();
        user.setUserId("userId");
        user.setUserName("userName");
        user.setPassword("password");
        user.setStatus(0);
        user.setDelFlag(0);
        user.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setCreateId("createId");
        user.setUpdateId("updateId");
        final List<User> users = Arrays.asList(user);
        when(mockUserService.getUserList()).thenReturn(users);

        // Run the test
        final ResultObject<List<User>> result = userControllerUnderTest.getUserList();
        System.out.println(result);

        // Verify the results
    }

    @Test
    void testGetUserList_IUserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getUserList()).thenReturn(Collections.emptyList());

        // Run the test
        final ResultObject<List<User>> result = userControllerUnderTest.getUserList();

        // Verify the results
    }

    @Test
    void testGetUserById() {
        // Setup
        // Configure IUserService.getUserById(...).
        final User user = new User();
        user.setUserId("userId");
        user.setUserName("userName");
        user.setPassword("password");
        user.setStatus(0);
        user.setDelFlag(0);
        user.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setCreateId("createId");
        user.setUpdateId("updateId");
        when(mockUserService.getUserById("id")).thenReturn(user);

        // Run the test
        final ResultObject<User> result = userControllerUnderTest.getUserById("id");
        System.out.println(result);

        // Verify the results
    }

    @Test
    void testInsertUser() {
        // Setup
        final UserRequestVo user = new UserRequestVo();
        user.setUserName("userName");
        user.setPassword("password");
        user.setCreateId("createId");
        user.setUpdateId("updateId");

        // Run the test
        final ResultObject result = userControllerUnderTest.insertUser(user);
        System.out.println(result);

        // Verify the results
        verify(mockUserService).insertUser(any(UserRequestVo.class));
    }
}
