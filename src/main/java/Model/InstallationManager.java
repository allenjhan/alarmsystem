package Model;

import Model.FireAlarmSensorSystem;

/**
 * Created by ahan on 5/29/17.
 */
public class InstallationManager {
    private FireAlarmSensorSystem fireAlarmSystem;
    private SecuritySensorSystem securitySensorSystem;

    public InstallationManager(){
        fireAlarmSystem = new FireAlarmSensorSystem();
        securitySensorSystem = new SecuritySensorSystem();
    }

    /**
     * Getter for the fire alarm SensorSystem.
     * @return the fire alarm SensorSystem
     */
    public FireAlarmSensorSystem getFireAlarmSystem() {
        return fireAlarmSystem;
    }

    /**
     * Getter for the security SensorSystem
     * @return the security SensorSystem
     */
    public SecuritySensorSystem getSecuritySensorSystem() {
        return securitySensorSystem;
    }

    /**
     * Installs the fire alarm SensorSystem
     */
    public void installFireAlarmSensorSystem(){
        fireAlarmSystem.installSystem(true);
    }

    /**
     * Uninstalls the fire alarm SensorSystem
     */
    public void uninstallFireAlarmSensorSystem(){
        fireAlarmSystem.uninstallSystem();
    }

    /**
     * Installs the security SensorSystem
     */
    public void installSecuritySensorSystem(){
        securitySensorSystem.installSystem(true);
    }

    /**
     * Uninstalls the security SensorSystem
     */
    public void uninstallSecuritySensorSystem(){
        securitySensorSystem.uninstallSystem();
    }
}
