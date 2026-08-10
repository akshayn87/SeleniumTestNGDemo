import com.crcgroup.automation.config.ConfigReader;
import com.crcgroup.automation.driver.DriverFactory;
import com.crcgroup.automation.driver.DriverManager;
import com.crcgroup.automation.enums.ConfigKey;
import com.crcgroup.automation.reporting.ExtentLogger;
import com.crcgroup.automation.reporting.ExtentReportManager;
import com.crcgroup.automation.reporting.ExtentTestManager;
import com.crcgroup.automation.utils.AssertionUtils;
import com.crcgroup.automation.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Cucumber lifecycle hooks: driver lifecycle, reporting and failure diagnostics. */
public class Hooks {

    private static final Logger LOG = LoggerFactory.getLogger(Hooks.class);

    @BeforeAll
    public static void beforeAll() {
        ExtentReportManager.getInstance();
    }

    @Before
    public void before(Scenario scenario) {
        LOG.info("Starting scenario: {}", scenario.getName());
        DriverFactory.initDriver();
        ExtentTestManager.startTest(scenario.getName());
        ExtentLogger.info("Started scenario: " + scenario.getName());
    }

    @After
    public void after(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                captureFailure(scenario);
            } else if (ConfigReader.getBoolean(ConfigKey.SCREENSHOT_ON_PASS, false)) {
                capturePass();
            }
            AssertionUtils.assertAll();
        } finally {
            DriverFactory.quitDriver();
            ExtentTestManager.remove();
            LOG.info("Finished scenario: {} [{}]", scenario.getName(), scenario.getStatus());
        }
    }

    @AfterAll
    public static void afterAll() {
        ExtentReportManager.flush();
    }

    private void captureFailure(Scenario scenario) {
        if (!DriverManager.hasDriver()) {
            return;
        }
        ScreenshotUtils screenshots = new ScreenshotUtils(DriverManager.getDriver());
        byte[] image = screenshots.viewportBytes();
        screenshots.save(image, "FAILED_" + safe(scenario.getName()));
        scenario.attach(image, "image/png", scenario.getName());
        ExtentLogger.failWithScreenshot("Scenario failed: " + scenario.getName(),
                ScreenshotUtils.toBase64(image));
    }

    private void capturePass() {
        ScreenshotUtils screenshots = new ScreenshotUtils(DriverManager.getDriver());
        String base64 = screenshots.viewportBase64();
        ExtentLogger.passWithScreenshot("Scenario passed", base64);
    }

    private String safe(String name) {
        return name.replaceAll("[^a-zA-Z0-9-_]", "_");
    }
}

