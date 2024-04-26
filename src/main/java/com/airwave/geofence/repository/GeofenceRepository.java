package com.airwave.geofence.repository;

import com.airwave.geofence.model.Geofence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GeofenceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Geofence> FindAll() {
        String sql = "SELECT * FROM geofence";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Geofence.class));

    }

    public int geoFenceSize() {
        String sql = "SELECT count(*) FROM geofence";
        return jdbcTemplate.queryForObject(sql, Integer.class);

    }

    public List<Geofence> getGeofenceById(int id){
        String sql = "SELECT * FROM geofence WHERE geofence_id = ?";


        List<Geofence> geofences = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Geofence.class), id);
        return geofences;
    }

    public Geofence getGeofenceByViewName(String viewname){
        String sql = "SELECT * FROM geofence WHERE viewname = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Geofence.class), viewname);
    }
}
