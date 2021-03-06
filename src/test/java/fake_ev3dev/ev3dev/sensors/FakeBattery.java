package fake_ev3dev.ev3dev.sensors;

import ev3dev.hardware.EV3DevPlatform;
import fake_ev3dev.BaseElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FakeBattery extends BaseElement{

    private static final String BATTERY_PATH = "power_supply";
    private static final String BATTERY_EV3_SUBPATH = "legoev3-battery";
    private static final String BATTERY_BRICKPI_SUBPATH = "brickpi-battery";
    private static final String BATTERY_BRICKPI3_SUBPATH = "brickpi3-battery";
    private static final String BATTERY_PISTORMS_SUBPATH = "pistorms-battery";

    private String batterySubpath;

    private static final String BATTERY_FIELD_VOLTAGE = "voltage_now";
    public static final String BATTERY_FIELD_VOLTAGE_VALUE = "8042133";

    public FakeBattery(final EV3DevPlatform ev3DevPlatform) throws IOException {

        if(ev3DevPlatform.equals(EV3DevPlatform.EV3BRICK)) {
            batterySubpath = BATTERY_EV3_SUBPATH;
        }else if(ev3DevPlatform.equals(EV3DevPlatform.BRICKPI)) {
            batterySubpath = BATTERY_BRICKPI_SUBPATH;
        }else if(ev3DevPlatform.equals(EV3DevPlatform.BRICKPI3)) {
            batterySubpath = BATTERY_BRICKPI3_SUBPATH;
        }else if(ev3DevPlatform.equals(EV3DevPlatform.PISTORMS)) {
            batterySubpath = BATTERY_PISTORMS_SUBPATH;
        }

        if(ev3DevPlatform != EV3DevPlatform.UNKNOWN) {
            Path batteryPath = Paths.get(
                    EV3DEV_FAKE_SYSTEM_PATH + "/" +
                            BATTERY_PATH + "/" +
                            batterySubpath);
            Files.createDirectories(batteryPath);

            Path batteryVoltagePath = Paths.get(
                    EV3DEV_FAKE_SYSTEM_PATH + "/" +
                            BATTERY_PATH + "/" +
                            batterySubpath + "/" +
                            BATTERY_FIELD_VOLTAGE);
            Files.createFile(batteryVoltagePath);
            Files.write(batteryVoltagePath, BATTERY_FIELD_VOLTAGE_VALUE.getBytes());
        } else {
            createEV3DevFakeSystemPath();
        }
    }
}
