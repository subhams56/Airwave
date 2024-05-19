package com.airwave.geofence.repository;

import com.airwave.geofence.model.Geofence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.jdbc.core.JdbcOperationsExtensionsKt.query;

@Repository
public class GeofenceRepository {


    private JdbcTemplate jdbcTemplate;


    @Autowired
    public GeofenceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Geofence> findAll() {
        String sql = "SELECT * FROM geofence";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Geofence.class));

    }



    public int geoFenceSize() {
        String sql = "SELECT count(*) FROM geofence";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }

    public List<Geofence> getGeofenceById(int id){
        String sql = "SELECT * FROM geofence WHERE geofence_id = ?";


        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Geofence.class), id);

    }

    public Geofence getGeofenceByViewName(String viewname){
        String sql = "SELECT * FROM geofence WHERE viewname = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Geofence.class), viewname);
    }
}
