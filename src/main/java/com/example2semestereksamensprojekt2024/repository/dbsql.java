package com.example2semestereksamensprojekt2024.repository;

import com.example2semestereksamensprojekt2024.model.member;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class dbsql {
    private JdbcTemplate jdbcTemplate;

    public dbsql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public member saveMember(member member) {
        try {
            Long memberid = member.getMemberid();
            if (memberid == null) {
                String sql = "INSERT INTO member (name, surname, email, password, phone, weight, height, age, gender, goals, activitylevel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                jdbcTemplate.update(sql, member.getName(), member.getSurname(), member.getEmail(), member.getPassword(), member.getPhone(), member.getWeight(), member.getHeight(), member.getAge(), member.getGender(), member.getGoals(), member.getActivitylevel());
            } else {
                String sql = "UPDATE member SET name = ?, surname = ?, email = ?, password = ?, phone = ?, weight = ?, height = ?, age = ?, gender = ?, goals = ?, activitylevel = ? WHERE memberid = ?";
                jdbcTemplate.update(sql, member.getName(), member.getSurname(), member.getEmail(), member.getPassword(), member.getPhone(), member.getWeight(), member.getHeight(), member.getAge(), member.getGender(), member.getGoals(), member.getActivitylevel(), memberid);
            }
            return member;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error while creating or updating a member", e);
        }
    }

    public Optional<member> findMemberByid(Long id){
        try {
            String sql = "SELECT * FROM member WHERE memberid = ?";
            member member = jdbcTemplate.queryForObject(sql, new Object[]{id}, memberRowMapper());
            return Optional.ofNullable(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch(DataAccessException e){
            throw new RuntimeException("Error accessing data while finding member", e);
        }
    }

    public List<member> findAllMembers() {
        try {
            String sql = "SELECT * FROM member";
            return jdbcTemplate.query(sql, memberRowMapper());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error while searching all members", e);
        }
    }

    public RowMapper<member> memberRowMapper() {
        return (rs, rowNum) -> {
            member member = new member();
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

    public Optional<member> findLogin(String email, String password) {
        try {
            String sql = "SELECT * FROM member WHERE email = ? AND password = ?";
            List<member> members = jdbcTemplate.query(sql, new Object[]{email, password}, memberRowMapper());
            if (members.size() == 1) {
                return Optional.of(members.get(0));
            } else {
                return Optional.empty();
            }
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}