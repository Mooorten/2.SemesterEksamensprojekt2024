package com.example2semestereksamensprojekt2024;

import com.example2semestereksamensprojekt2024.model.Member;
import com.example2semestereksamensprojekt2024.repository.MemberDbSql;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Mock
    MemberDbSql memberDbSql;

    @Test
    void createMember() {
        Member m = new Member();
        m.setMemberid(1L);
        m.setName("test");
        m.setSurname("test");
        m.setEmail("test@test.com");
        m.setPassword("1234");
        m.setPhone("12345678");
        m.setWeight("70");
        m.setHeight("170");
        m.setAge(22);
        m.setGender("Male");
        m.setGoals("Gain weight");
        m.setActivitylevel("3 times a day");
        memberDbSql.createMember(m);
    }
}