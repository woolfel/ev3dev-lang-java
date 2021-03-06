package ev3dev.sensors;

import ev3dev.hardware.EV3DevFileSystem;
import ev3dev.hardware.EV3DevPlatform;
import fake_ev3dev.ev3dev.sensors.FakeBattery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by jabrena on 19/6/17.
 */
@Slf4j
public class BatteryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void resetTest() throws IOException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        //https://stackoverflow.com/questions/8256989/singleton-and-unit-testing
        Field instance = Battery.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);

        FakeBattery.deleteEV3DevFakeSystemPath();
        FakeBattery.createEV3DevFakeSystemPath();
    }

    @Test
    public void getEV3BatteryVoltageTest() throws Exception {

        System.setProperty(EV3DevFileSystem.EV3DEV_TESTING_KEY, FakeBattery.EV3DEV_FAKE_SYSTEM_PATH);

        final FakeBattery fakeBattery = new FakeBattery(EV3DevPlatform.EV3BRICK);

        Battery battery = Battery.getInstance();
        LOGGER.info("{}", battery.getVoltage());

        assertThat(battery.getVoltage(),
                is(Float.parseFloat(FakeBattery.BATTERY_FIELD_VOLTAGE_VALUE)/1000000f));
    }

    @Test
    public void getBrickPiBatteryVoltageTest() throws Exception {

        System.setProperty(EV3DevFileSystem.EV3DEV_TESTING_KEY, FakeBattery.EV3DEV_FAKE_SYSTEM_PATH);

        final FakeBattery fakeBattery = new FakeBattery(EV3DevPlatform.BRICKPI);

        Battery battery = Battery.getInstance();
        LOGGER.info("{}", battery.getVoltage());

        assertThat(battery.getVoltage(),
                is(Float.parseFloat(FakeBattery.BATTERY_FIELD_VOLTAGE_VALUE)/1000000f));
    }

    @Test
    public void getBrickPi3BatteryVoltageTest() throws Exception {

        System.setProperty(EV3DevFileSystem.EV3DEV_TESTING_KEY, FakeBattery.EV3DEV_FAKE_SYSTEM_PATH);

        final FakeBattery fakeBattery = new FakeBattery(EV3DevPlatform.BRICKPI3);

        Battery battery = Battery.getInstance();
        LOGGER.info("{}", battery.getVoltage());

        assertThat(battery.getVoltage(),
                is(Float.parseFloat(FakeBattery.BATTERY_FIELD_VOLTAGE_VALUE)/1000000f));
    }

    @Test
    public void getPiStormsBatteryVoltageTest() throws Exception {

        System.setProperty(EV3DevFileSystem.EV3DEV_TESTING_KEY, FakeBattery.EV3DEV_FAKE_SYSTEM_PATH);

        final FakeBattery fakeBattery = new FakeBattery(EV3DevPlatform.PISTORMS);

        Battery battery = Battery.getInstance();
        LOGGER.info("{}", battery.getVoltage());

        assertThat(battery.getVoltage(),
                is(Float.parseFloat(FakeBattery.BATTERY_FIELD_VOLTAGE_VALUE)/1000000f));
    }

    @Test
    public void getUnknownPlatformBatteryVoltageTest() throws Exception {

        thrown.expect(RuntimeException.class);

        System.setProperty(EV3DevFileSystem.EV3DEV_TESTING_KEY, FakeBattery.EV3DEV_FAKE_SYSTEM_PATH);

        final FakeBattery fakeBattery = new FakeBattery(EV3DevPlatform.UNKNOWN);

        Battery battery = Battery.getInstance();
    }

}
