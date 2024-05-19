package com.airwave.geofence.service;

import com.airwave.geofence.model.Geofence;
import com.airwave.geofence.repository.GeofenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GeofenceServiceTest {

    @Mock
    private GeofenceRepository geofenceRepository;

    @InjectMocks
    private GeofenceService geofenceService;



    @Test
    public void testGetAllGeofence() {
        // Arrange
        List<Geofence> expectedGeofences = new ArrayList<>();
        expectedGeofences.add(new Geofence());

        // Act
        when(geofenceRepository.findAll()).thenReturn(expectedGeofences);
        List<Geofence> actualGeofences = geofenceService.getAllGeofence();

        // Assert
        assertEquals(expectedGeofences, actualGeofences);
    }

    @Test
    public void testGeoFenceSize() {
        // Arrange
        int expectedSize = 10;

        // Act
        when(geofenceRepository.geoFenceSize()).thenReturn(expectedSize);
        int actualSize = geofenceService.geoFenceSize();

        // Assert
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testGetGeofenceById() {
        // Arrange
        int id = 1;
        Geofence geofence = new Geofence();
        geofence.setGeofenceId(id);
        List <Geofence> expectedGeofenceList = new ArrayList<>();
        expectedGeofenceList.add(geofence);

        when(geofenceRepository.getGeofenceById(1)).thenReturn(expectedGeofenceList);

        // Act
        List<Geofence> actualGeofence =  geofenceService.getGeofenceById(id);

        // Assert
        assertEquals(expectedGeofenceList, actualGeofence);
    }

    @Test
    public void testGetGeofenceByViewName() {
        // Arrange
        String viewname = "test-view";
        Geofence expectedGeofence = new Geofence();
        expectedGeofence.setViewname(viewname);

        when(geofenceRepository.getGeofenceByViewName(viewname)).thenReturn(expectedGeofence);

        // Act
        Geofence actualGeofence = geofenceService.getGeofenceByViewName(viewname);

        // Assert
        assertEquals(expectedGeofence, actualGeofence);
    }


}