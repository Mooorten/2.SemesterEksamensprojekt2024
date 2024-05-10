package com.example2semestereksamensprojekt2024.service;
import java.util.List;
import java.util.Optional;

import com.example2semestereksamensprojekt2024.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example2semestereksamensprojekt2024.repository.MemberDbSql;

@Service
public class MemberUsecase {
    @Autowired
    private MemberDbSql memberDbSql;

    public void createMember(Member member) {
        memberDbSql.createMember(member);
    }

    public void updateMember(Member member) {
        memberDbSql.updateMember(member);
    }

    public void deleteMember(Long id) {
        memberDbSql.deleteMember(id);
    }

    public Member findLogin(String email, String password) {
        return memberDbSql.findLogin(email, password);
    }

    public List<Member> findAllMembers() {
        return memberDbSql.findAllMembers();
    }

    public Optional<Member> findUserByID(Long id){
        return memberDbSql.findMemberByID(id);
    }
}
