package com.example2semestereksamensprojekt2024.service;
import java.util.List;
import java.util.Optional;

import com.example2semestereksamensprojekt2024.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example2semestereksamensprojekt2024.repository.dbsql;

@Service
public class usecase {
    @Autowired
    private dbsql dbsql;

    public void saveMember(Member member) {
        dbsql.saveMember(member);
    }

    public void delete(Long id) {
        dbsql.delete(id);
    }

    public boolean findLogin(String email, String password) {
        return dbsql.findLogin(email, password).isPresent();
    }

    public List<Member> findAllMembers() {
        return dbsql.findAllMembers();
    }

    public Optional<Member> findMemberById(Long id) {
        return dbsql.findMemberByid(id);
    }
}
