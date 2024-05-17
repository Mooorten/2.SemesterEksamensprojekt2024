package com.example2semestereksamensprojekt2024;

import com.example2semestereksamensprojekt2024.model.User;
import com.example2semestereksamensprojekt2024.DBController.UserDbSql;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Mock
    UserDbSql userDbSql;

    @Test
    void createUser() {
        User u = new User();
        u.setUserid(1L);
        u.setName("test");
        u.setSurname("test");
        u.setEmail("test@test.com");
        u.setPassword("1234");
        u.setPhone("12345678");
        u.setWeight("70");
        u.setHeight("170");
        u.setAge(22);
        u.setGender("Male");
        u.setGoals("Gain weight");
        u.setActivitylevel("3 times a day");
        userDbSql.createUser(u);
    }
}