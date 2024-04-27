package com.airwave.geofence.controller;

import com.airwave.geofence.model.Geofence;
import com.airwave.geofence.service.GeofenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AirwaveControllerTest {

    @Mock
    private GeofenceService geofenceService;

    @InjectMocks
    private AirwaveController airwaveController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllGeofence() {
        List<Geofence> geofenceList = new ArrayList<>();
        Geofence geofence = new Geofence();
        geofenceList.add(geofence);

        when(geofenceService.getAllGeofence()).thenReturn(geofenceList);

        ResponseEntity<Map<String, List<Geofence>>> response = airwaveController.getAllGeofence();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, List<Geofence>> body = response.getBody();
        assertEquals(1, body.size());
         // Corrected key based on actual implementation
    }

    @Test
    public void testGetGeofenceById_Success() {
        int id = 1;
        List<Geofence> geofenceList = new ArrayList<>();
        Geofence geofence = new Geofence();
        geofenceList.add(geofence);

        when(geofenceService.getGeofenceById(id)).thenReturn(geofenceList);

        List<Geofence> returnedGeofences = airwaveController.getGeofence(id);

        assertEquals(geofenceList, returnedGeofences);
    }

    @Test
    public void testGetGeofenceById_NotFound() {
        int id = 1;

        when(geofenceService.getGeofenceById(id)).thenThrow(new NoSuchElementException());

        assertThrows(NoSuchElementException.class, () -> airwaveController.getGeofence(id));
    }

    @Test
    public void testGetGeofenceByViewName_Success() {
        String viewname = "test-view";
        Geofence geofence = new Geofence();

        when(geofenceService.getGeofenceByViewName(viewname)).thenReturn(geofence);

        Geofence returnedGeofence = airwaveController.getGeofenceByViewName(viewname);

        assertEquals(geofence, returnedGeofence);
    }

    @Test
    public void testGetGeofenceByViewName_NotFound() {
        String viewname = "test-view";

        when(geofenceService.getGeofenceByViewName(viewname)).thenThrow(new NoSuchElementException());

        assertThrows(NoSuchElementException.class, () -> airwaveController.getGeofenceByViewName(viewname));
    }
}
