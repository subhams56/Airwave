package com.airwave.geofence.repository;


import com.airwave.geofence.model.Geofence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GeofenceRepositoryTest {

    @InjectMocks
    GeofenceRepository geofenceRepository;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
     void testFindAll(){
        Geofence geofence1 = new Geofence();
        Geofence geofence2 = new Geofence();

        List<Geofence> geofenceList = new ArrayList<>();
        geofenceList.add(geofence1);
        geofenceList.add(geofence2);

        when(jdbcTemplate.query(anyString(),any(BeanPropertyRowMapper.class))).thenReturn(geofenceList);

        List<Geofence> returnedGeofenceList = geofenceRepository.findAll();

        assertEquals(geofenceList, returnedGeofenceList);
        assertNotNull(returnedGeofenceList);
        assertEquals(2, returnedGeofenceList.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(BeanPropertyRowMapper.class));
    }

    @Test
    void geoFenceSize_ShouldReturnGeofenceCount() {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class))).thenReturn(5);

        int count = geofenceRepository.geoFenceSize();

        assertEquals(5, count);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), eq(Integer.class));
    }

    @Test
    void getGeofenceById_ShouldReturnGeofenceList() {
        Geofence geofence = new Geofence();
        List<Geofence> geofences = Arrays.asList(geofence);

        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class), anyInt())).thenReturn(geofences);

        List<Geofence> result = geofenceRepository.getGeofenceById(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(BeanPropertyRowMapper.class), anyInt());
    }

    @Test
    void getGeofenceByViewName_ShouldReturnGeofence() {
        Geofence geofence = new Geofence();

        when(jdbcTemplate.queryForObject(anyString(), any(BeanPropertyRowMapper.class), anyString())).thenReturn(geofence);

        Geofence result = geofenceRepository.getGeofenceByViewName("viewname");

        assertNotNull(result);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(BeanPropertyRowMapper.class), anyString());
    }


    }







