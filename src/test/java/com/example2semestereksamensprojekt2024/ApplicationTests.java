package com.example2semestereksamensprojekt2024;

import com.example2semestereksamensprojekt2024.model.User;
import com.example2semestereksamensprojekt2024.DBController.UserDbSql;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void updateUser() {
        User userToUpdate = new User();
        User currentUser = new User();
        userDbSql.updateUser(userToUpdate,currentUser);
    }

    @Test
    void deleteUser(){
        Long userid = 1L;
        userDbSql.deleteUser(userid);
    }
    @Test
    void testCalculateBMRForMale() {
        Long userId = 1L;
        User user = new User();
        user.setUserid(userId);
        user.setGender("Mand");
        user.setWeight("70");
        user.setHeight("175");
        user.setAge(30);
        user.setActivitylevel("Moderat aktiv");
        user.setGoals("Hold vægten");

        when(userDbSql.findUserByID(userId)).thenReturn(Optional.of(user));

        double expectedBMR = (10 * 70) + (6.25 * 175) - (5 * 30) + 5;
        double actualBMR = userDbSql.calculateBMR(userId);
        System.out.println(actualBMR);

        assertEquals(expectedBMR, actualBMR);
    }
}