package com.example2semestereksamensprojekt2024.service;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.example2semestereksamensprojekt2024.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example2semestereksamensprojekt2024.repository.DbSql;

@Service
public class Usecase {
    @Autowired
    private DbSql dbsql;

    public void saveMember(Member member) {
        dbsql.saveMember(member);
    }

    public void delete(Long id) {
        dbsql.delete(id);
    }

    public Member findLogin(String email, String password) {
        return dbsql.findLogin(email, password);
    }

    public List<Member> findAllMembers() {
        return dbsql.findAllMembers();
    }

    public Optional<Member> findUserByID(Long id){
        return dbsql.findMemberByID(id);
    }
}
