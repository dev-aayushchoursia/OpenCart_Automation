package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = {"Sanity", "Master", "DataDriven"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException {
		logger = LogManager.getLogger(this.getClass());

		// Load Configuration File
		String configFilePath = Paths.get("src", "test", "resources", "config.properties").toString();
		try (FileReader file = new FileReader(configFilePath)) {
			p = new Properties();
			p.load(file);
		} catch (IOException e) {
			logger.error("Error loading config.properties", e);
			throw e;
		}

		String executionEnv = p.getProperty("execution_env").toLowerCase();
		logger.info("Execution Mode: " + executionEnv);

		if (driver == null) {
			if (executionEnv.equals("remote")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();

				// OS Setup
				if (os.equalsIgnoreCase("windows")) {
					capabilities.setPlatform(Platform.WINDOWS);
				} else if (os.equalsIgnoreCase("linux")) {
					capabilities.setPlatform(Platform.LINUX);
				} else {
					logger.error("Invalid OS: " + os);
					throw new IllegalArgumentException("Invalid OS specified.");
				}

				// Browser Setup
				switch (br.toLowerCase()) {
					case "chrome":
						capabilities.setBrowserName("chrome");
						break;
					case "edge":
						capabilities.setBrowserName("MicrosoftEdge");
						break;
					case "firefox":
						capabilities.setBrowserName("firefox");
					break;
					default:
						logger.error("Invalid browser: " + br);
						throw new IllegalArgumentException("Invalid browser specified.");
				}

				// Remote WebDriver Setup
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				logger.info("Tests executing on Selenium Grid.");
			} else if (executionEnv.equals("local")) {
				// Local Execution
				switch (br.toLowerCase()) {
					case "chrome":
						driver = new ChromeDriver();
						break;
					case "edge":
						driver = new EdgeDriver();
						break;
					case "firefox":
						driver = new FirefoxDriver();
						break;
					default:
						logger.error("Invalid browser: " + br);
						throw new IllegalArgumentException("Invalid browser specified.");
				}
				logger.info("Tests executing on Local Machine.");
			} else {
				throw new IllegalStateException("Invalid execution environment in config.properties");
			}
		}

		// Browser Configuration
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Load Application URL
		String appUrl = p.getProperty("appUrl");
		if (appUrl == null || appUrl.isEmpty()) {
			throw new IllegalStateException("appUrl is missing in config.properties");
		}

		driver.get(appUrl);
		driver.manage().window().maximize();
	}

	@AfterClass(groups = {"Sanity", "Master", "DataDriven"})
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}

	public String randomNumber() {
		return RandomStringUtils.randomNumeric(10);
	}

	public String randomAlphaNumeric() {
		return RandomStringUtils.randomAlphabetic(3) + "@" + RandomStringUtils.randomNumeric(3);
	}

	public String captureScreenshot(String tname) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\Screenshots\\" + tname + "_" + timestamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}