package com.example2semestereksamensprojekt2024.repository;

import com.example2semestereksamensprojekt2024.model.Member;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class dbsql {
    private JdbcTemplate jdbcTemplate;

    public dbsql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveMember(Member member) {
        try {
            Long memberid = member.getMemberid();
            if (memberid == null) {
                String sql = "INSERT INTO member (email, password, name, surname, phone, weight, height, age, gender, goals, activitylevel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                jdbcTemplate.update(sql, member.getEmail(), member.getPassword(), member.getName(), member.getSurname(), member.getPhone(), member.getWeight(), member.getHeight(), member.getAge(), member.getGender(), member.getGoals(), member.getActivitylevel());
            } else {
                String sql = "UPDATE member SET email = ?, password = ?, name = ?, surname = ?, phone = ?, weight = ?, height = ?, age = ?, gender = ?, goals = ?, activitylevel = ? WHERE memberid = ?";
                jdbcTemplate.update(sql, member.getEmail(), member.getPassword(), member.getName(), member.getSurname(), member.getPhone(), member.getWeight(), member.getHeight(), member.getAge(), member.getGender(), member.getGoals(), member.getActivitylevel(), member.getMemberid());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error while creating an Member", e);
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM member WHERE memberid = ?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<Member> findMemberByid(Long id){
        try {
            String sql = "SELECT * FROM member WHERE memberid = ?";
            Member member = jdbcTemplate.queryForObject(sql, new Object[]{id}, memberRowMapper());
            return Optional.ofNullable(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch(DataAccessException e){
            throw new RuntimeException("Error accessing data while finding Member", e);
        }
    }

    public List<Member> findAllMembers() {
        try {
            String sql = "SELECT * FROM member";
            return jdbcTemplate.query(sql, memberRowMapper());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error while searching all members", e);
        }
    }

    public RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setMemberid(rs.getLong("memberid"));
            member.setName(rs.getString("name"));
            member.setSurname(rs.getString("surname"));
            member.setEmail(rs.getString("email"));
            member.setPassword(rs.getString("password"));
            member.setPhone(rs.getString("phone"));
            member.setWeight(rs.getString("weight"));
            member.setHeight(rs.getString("height"));
            member.setAge(rs.getString("age"));
            member.setGender(rs.getString("gender"));
            member.setActivitylevel(rs.getString("activitylevel"));
            return member;
        };
    }

    public Optional<Member> findLogin(String email, String password) {
        try {
            String sql = "SELECT * FROM member WHERE email = ? AND password = ?";
            List<Member> Members = jdbcTemplate.query(sql, new Object[]{email, password}, memberRowMapper());
            if (Members.size() == 1) {
                return Optional.of(Members.get(0));
            } else {
                return Optional.empty();
            }
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}