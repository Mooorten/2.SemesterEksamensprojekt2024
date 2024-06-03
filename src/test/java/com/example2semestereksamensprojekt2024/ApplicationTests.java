package com.example2semestereksamensprojekt2024;

import com.example2semestereksamensprojekt2024.model.User;
import com.example2semestereksamensprojekt2024.DBController.UserDbSql;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.mockito.Mockito.when;

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
        u.setGender("Mand");
        u.setGoals("Opbyg muskel");
        u.setActivitylevel("Atlet");
        u.setRole("user");
        userDbSql.createUser(u);
    }

    @Test
    void readUser() {
        Long userid = 1L;
        userDbSql.findUserByID(userid);
    }

    @Test
    void testUpdateUser() {
        Long id = 1L;
        User user = new User();
        user.setName("Old Name");
        user.setName("New Name");
        when(userDbSql.findUserByID(id)).thenReturn(Optional.of(user));
        userDbSql.updateUser(user, user);
        }

    @Test
    void deleteUser(){
        Long userid = 1L;
        userDbSql.deleteUser(userid);
    }
}