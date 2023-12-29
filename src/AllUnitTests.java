import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class AllUnitTests {

    @Test
    void testMetadataConstructor() {
    	Metadata metadata = new Metadata("ChargingStationSoftware", "1.0","Charging stations management software to handle different stations.");
        assertNotNull(metadata);
    }
    
    @Test
    void testUserConstructor() {
        User user = new User("EXTERNAL", 22);
        assertNotNull(user);
    }

    @Test
    void testUsername() {
        User user = new User("ADMIN", 25);
        assertEquals("User-25", user.getUsername());
    }
    
    @Test
    void testSetAndGetAllUserVariables() {
        User user = new User("EXTERNAL", 30);
        user.setUserId(12);
        assertEquals(12, user.getUserId());
        user.setUsername("Test");
        assertEquals("Test", user.getUsername());
        user.setRole("ADMIN");
        assertEquals("ADMIN", user.getRole());
    }
    
    @Test
    void testWeatherSimulator() {
        String weather = WeatherSimulator.simulateWeather();
        assertNotNull(weather);
    }
    
    @Test
    void testCanChargeInCurrentWeatherRainy() {
        
        EnergySource source = new EnergySource(50 , "Solar" );
        String currentWeather = "Rainy";
        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setEnergySource(source);

        assertFalse(chargingStation.canChargeInCurrentWeather(currentWeather));
    }
    
    @Test
    void testOccupyStation() {
        EnergySource energySource = new EnergySource(50, "Electric Grid");
        assertTrue(energySource.occupyStation(30));
        assertFalse(energySource.occupyStation(60));
    }
    
    @Test
    void testReleaseWithReplenish() {
        EnergySource energySource = new EnergySource(50, "Solar");
        energySource.release(20);
        assertEquals(70, energySource.getEnergyLevel());
    }
    
    @Test
    void testCanChargeInCurrentWeatherSunny() {
        
        EnergySource source = new EnergySource(50 , "Solar" );
        String currentWeather = "Sunny";
        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setEnergySource(source);
        
        assertTrue(chargingStation.canChargeInCurrentWeather(currentWeather));
    }
    
    @Test
    void testChargeVehicleFailure() {
        EnergySource energySource = new EnergySource(10, "Solar");
        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setEnergySource(energySource);
        
        assertFalse(chargingStation.chargeVehicle(20));
        
        assertEquals(10, energySource.getEnergyLevel());
        
    }
   
    @Test
    void testMain() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
    
    @Test
    void testOpenLogFile() {
        LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedDate = currentDateTime.format(dateformatter);
		
        assertDoesNotThrow(() -> LogFileOpener.openLogFile("logs/" + formattedDate + "/SystemLogs.txt"));
    }
 
    @Test
    void testEnergyManagementConstructor() {
    	EnergyManagementSystem energyManagementSystem = new EnergyManagementSystem(2);
        assertNotNull(energyManagementSystem);
    }
    
    @Test
    void testGetOptimalEnergySourceSunny() {
        EnergyManagementSystem energyManagementSystem = new EnergyManagementSystem(2);
        EnergySource optimalSource = energyManagementSystem.getOptimalEnergySource("Sunny");
        assertEquals("Solar", optimalSource.getSourceName());
    }

    @Test
    void testGetOptimalEnergySourceCloudy() {
        EnergyManagementSystem energyManagementSystem = new EnergyManagementSystem(2);
        EnergySource optimalSource = energyManagementSystem.getOptimalEnergySource("Cloudy");
        assertEquals("Electrical Grid", optimalSource.getSourceName());
    }
}
