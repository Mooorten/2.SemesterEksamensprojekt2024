package com.example2semestereksamensprojekt2024.service;
import java.util.List;
import java.util.Optional;
import com.example2semestereksamensprojekt2024.model.member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example2semestereksamensprojekt2024.repository.dbsql;

@Service
public class usecase {
    @Autowired
    private dbsql dbsql;

    public void saveMember(member member) {
        dbsql.saveMember(member);
    }

    public boolean findLogin(String email, String password) {
        return dbsql.findLogin(email, password).isPresent();
    }

    public List<member> findAllMembers() {
        return dbsql.findAllMembers();
    }

    public Optional<member> findMemberById(Long id) {
        return dbsql.findMemberByid(id);
    }
}
