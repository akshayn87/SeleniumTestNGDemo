Enterprise-grade UI automation framework using the Page Object / Page Component pattern.
It navigates to `https://www.crcgroup.com/About-Us/About-Us` and verifies the page loads.

## Tech stack

- Java 17
- Selenium 4 WebDriver
- TestNG (parallel, retry, listeners)
- Cucumber BDD (Gherkin features + step definitions)
- Maven
- Extent Reports (Spark)
- Log4j2 via SLF4J
- WebDriverManager (automatic driver setup)
- Jackson (JSON/YAML test data)

## 1. Project structure

```
demoSeleniumframework/
├── pom.xml
├── testng.xml
├── README.md
├── .gitignore
├── src/
│   ├── main/
│   │   ├── java/com/crcgroup/automation/
│   │   │   ├── config/
│   │   │   │   └── ConfigReader.java
│   │   │   ├── constants/
│   │   │   │   └── FrameworkConstants.java
│   │   │   ├── enums/
│   │   │   │   ├── BrowserType.java
│   │   │   │   ├── WaitStrategy.java
│   │   │   │   └── ConfigKey.java
│   │   │   ├── exceptions/
│   │   │   │   ├── FrameworkException.java
│   │   │   │   ├── ElementNotFoundException.java
│   │   │   │   └── ConfigException.java
│   │   │   ├── driver/
│   │   │   │   ├── DriverManager.java
│   │   │   │   ├── BrowserFactory.java
│   │   │   │   └── DriverFactory.java
│   │   │   ├── core/
│   │   │   │   ├── Locator.java
│   │   │   │   ├── LocatorUtils.java
│   │   │   │   ├── WaitUtils.java
│   │   │   │   └── ElementActions.java
│   │   │   ├── utils/
│   │   │   │   ├── JavaScriptUtils.java
│   │   │   │   ├── WindowUtils.java
│   │   │   │   ├── FrameUtils.java
│   │   │   │   ├── AlertUtils.java
│   │   │   │   ├── FileIoUtils.java
│   │   │   │   ├── RetryUtils.java
│   │   │   │   ├── ScreenshotUtils.java
│   │   │   │   └── AssertionUtils.java
│   │   │   ├── reporting/
│   │   │   │   ├── ExtentReportManager.java
│   │   │   │   ├── ExtentTestManager.java
│   │   │   │   └── ExtentLogger.java
│   │   │   ├── pages/
│   │   │   │   ├── BasePage.java
│   │   │   │   ├── AboutUsPage.java
│   │   │   │   └── components/
│   │   │   │       └── BaseComponent.java
│   │   │   └── testdata/
│   │   │       └── TestDataReader.java
│   │   └── resources/
│   │       └── config.properties
│   └── test/
│       ├── java/com/crcgroup/automation/
│       │   ├── runners/
│       │   │   └── TestRunner.java
│       │   ├── hooks/
│       │   │   └── Hooks.java
│       │   ├── stepdefinitions/
│       │   │   └── AboutUsSteps.java
│       │   └── listeners/
│       │       ├── RetryAnalyzer.java
│       │       └── TestListener.java
│       └── resources/
│           ├── features/
│           │   └── about_us.feature
│           ├── testdata/
│           │   └── about_us.json
│           ├── log4j2.xml
│           └── cucumber.properties
└── test-output/            (generated at runtime)
    ├── reports/            Extent HTML report
    ├── cucumber/           Cucumber HTML + JSON + timeline
    ├── screenshots/        Failure (and optional pass) screenshots
    └── logs/               automation.log
```

## 2. Folder and file explanation

### Build & config (root)
| Path | Purpose |
| --- | --- |
| `pom.xml` | Maven build file: dependencies, versions, compiler and Surefire config. |
| `testng.xml` | TestNG suite; runs `TestRunner`, enables parallel methods and registers `TestListener`. |
| `.gitignore` | Excludes `target/`, `test-output/`, IDE files. |

### `src/main/java` — framework (production code)
| Package / file | Responsibility |
| --- | --- |
| `config/ConfigReader.java` | Loads `config.properties`; `-Dkey=value` system properties override file values. Typed getters (`getInt`, `getLong`, `getBoolean`). |
| `constants/FrameworkConstants.java` | Central constants (default timeouts, output dirs, timestamp pattern). |
| `enums/BrowserType.java` | Supported browsers `CHROME/FIREFOX/EDGE`; `from()` defaults to Chrome. |
| `enums/WaitStrategy.java` | Wait intent (`NONE/PRESENCE/VISIBLE/CLICKABLE`). |
| `enums/ConfigKey.java` | Typed keys for every config property (no magic strings). |
| `exceptions/*` | `FrameworkException` (base), `ElementNotFoundException`, `ConfigException`. |
| `driver/DriverManager.java` | Thread-safe `ThreadLocal<WebDriver>` holder for parallel runs. |
| `driver/BrowserFactory.java` | Builds a configured driver per browser (options, headless, download dir) via WebDriverManager. |
| `driver/DriverFactory.java` | Orchestrates init/quit, timeouts and window sizing; picks browser from config. |
| `core/Locator.java` | Named, self-healing locator holding multiple `By` candidates. |
| `core/LocatorUtils.java` | Builders for resilient locators (data-testid, ARIA, name, text, CSS, XPath). |
| `core/WaitUtils.java` | Explicit waits (presence/visible/clickable/invisible), page-load, element-stable, overlay-gone. No `Thread.sleep()`. |
| `core/ElementActions.java` | Reusable element engine: `click/type/select/hover/scroll/upload/safeClick/...` with self-heal and stale recovery. |
| `utils/JavaScriptUtils.java` | JS click, scroll, set value, highlight, page-ready. |
| `utils/WindowUtils.java` | Window/tab switching by index/title, open/close tabs. |
| `utils/FrameUtils.java` | Iframe switching (index/name/By/parent/default). |
| `utils/AlertUtils.java` | JS alert accept/dismiss/getText/sendKeys. |
| `utils/FileIoUtils.java` | Read/write files, ensure dirs, wait for a download to complete. |
| `utils/RetryUtils.java` | Generic retry wrapper for flaky operations. |
| `utils/ScreenshotUtils.java` | Viewport, element and stitched full-page screenshots; timestamped saves. |
| `utils/AssertionUtils.java` | Hard + soft (`ThreadLocal<SoftAssert>`) assertions that log to logger and Extent. |
| `reporting/ExtentReportManager.java` | Single `ExtentReports` instance + Spark reporter + environment metadata. |
| `reporting/ExtentTestManager.java` | `ThreadLocal<ExtentTest>` per scenario. |
| `reporting/ExtentLogger.java` | Convenience pass/fail/info logging and screenshot attachment. |
| `pages/BasePage.java` | Base page composing the utilities (element engine, waits, JS, windows, frames, alerts). |
| `pages/AboutUsPage.java` | About Us page object with self-healing locators and `open()/isLoaded()`. |
| `pages/components/BaseComponent.java` | Base for reusable, root-scoped page components (Page Component pattern). |
| `testdata/TestDataReader.java` | Loads JSON test data from the classpath via Jackson. |
| `resources/config.properties` | Externalised settings: URL, browser, timeouts, retries, paths, parallel count. |

### `src/test/java` — test layer (BDD)
| Package / file | Responsibility |
| --- | --- |
| `runners/TestRunner.java` | TestNG + Cucumber entry point; parallel scenario data provider; plugin/report config. |
| `hooks/Hooks.java` | Cucumber lifecycle: init/quit driver, start Extent test, capture screenshot on failure, `assertAll()`, flush report. |
| `stepdefinitions/AboutUsSteps.java` | Gherkin step implementations for the About Us feature. |
| `listeners/RetryAnalyzer.java` | Retries a failed test up to `retry.count`. |
| `listeners/TestListener.java` | Suite-level TestNG logging around each test method. |

### `src/test/resources` — test assets
| Path | Responsibility |
| --- | --- |
| `features/about_us.feature` | Gherkin scenario: navigate to About Us and verify URL/title. |
| `testdata/about_us.json` | Sample JSON test data. |
| `log4j2.xml` | Log4j2 config: console + rolling file appender (`test-output/logs/automation.log`). |
| `cucumber.properties` | Cucumber runtime settings. |

## 3. Dependency compatibility & usage (from pom.xml)

Baseline: **Java 17**, **Maven 3.9.9**, Surefire **3.5.0**. All versions below are mutually compatible and verified building/running together.

| Dependency | Version | Compatibility notes | How it is used |
| --- | --- | --- | --- |
| `org.seleniumhq.selenium:selenium-java` | 4.25.0 | Requires Java 11+ (uses 17 here). Brings SLF4J 2 API. | Browser automation in `BrowserFactory`, `ElementActions`, `WaitUtils`, `ScreenshotUtils`. |
| `org.testng:testng` | 7.10.2 | Java 11+; integrates with Surefire 3.x and cucumber-testng. Pulls transitive SLF4J 1.7 (overridden — see below). | Test engine, parallel data provider, listeners, retry analyzer, soft assertions. |
| `io.github.bonigarcia:webdrivermanager` | 5.9.2 | Compatible with Selenium 4.x; auto-resolves driver binaries. | Auto-downloads chromedriver/geckodriver/edgedriver in `BrowserFactory`. |
| `io.cucumber:cucumber-java` | 7.18.1 | Cucumber 7.x pairs with cucumber-testng 7.x (same version). Java 11+. | Step definitions and hooks annotations (`@Given/@Then/@Before/@After`). |
| `io.cucumber:cucumber-testng` | 7.18.1 | Must match `cucumber-java`. Bridges Cucumber to TestNG. | `TestRunner extends AbstractTestNGCucumberTests`; parallel scenarios. |
| `com.aventstack:extentreports` | 5.1.2 | 5.x needs Java 8+; Spark reporter API used here. | HTML reporting in `ExtentReportManager/ExtentTestManager/ExtentLogger`. |
| `org.slf4j:slf4j-api` | 2.0.13 | **Pinned** to 2.x so the Log4j2 SLF4J-2 binding wins over TestNG's transitive SLF4J 1.7.36. | Logging facade used across the framework via `LoggerFactory`. |
| `org.apache.logging.log4j:log4j-api` | 2.23.1 | Log4j2 API; aligns with core and slf4j2-impl (same version). | Logging API backing SLF4J. |
| `org.apache.logging.log4j:log4j-core` | 2.23.1 | Log4j2 implementation; reads `log4j2.xml`. | Console + rolling-file appenders. |
| `org.apache.logging.log4j:log4j-slf4j2-impl` | 2.23.1 | Binds SLF4J **2.x** to Log4j2 (do not use `log4j-slf4j-impl`, which is for SLF4J 1.x). | Routes all SLF4J logs to Log4j2. |
| `com.fasterxml.jackson.core:jackson-databind` | 2.17.2 | Jackson 2.17 modules share the same version line. | JSON test-data binding in `TestDataReader`. |
| `com.fasterxml.jackson.dataformat:jackson-dataformat-yaml` | 2.17.2 | Must match `jackson-databind` minor line. | YAML test-data support. |
| `commons-io:commons-io` | 2.16.1 | Java 8+; independent utility. | File read/write and directory helpers in `FileIoUtils`. |

**Key compatibility rule enforced here:** TestNG drags in SLF4J `1.7.36`, which is incompatible with the Log4j2 SLF4J-2 binding. Declaring `slf4j-api:2.0.13` directly forces the 2.x API so logging is emitted to console and `automation.log`.

## 4. Environment information

| Item | Value (verified) |
| --- | --- |
| OS | Windows 11 |
| JDK | Microsoft OpenJDK 17.0.10 (`JAVA_HOME=C:\Users\ANikhare\AppData\Local\Programs\Microsoft\jdk-17.0.10.7-hotspot`) |
| Build tool | Apache Maven 3.9.9 (`C:\Users\ANikhare\tools\apache-maven-3.9.9`) |
| Default browser | Chrome (visible; `headless=false`) |
| Network | Corporate Zscaler TLS inspection — Zscaler root CA imported into the JDK `cacerts` so Maven can download dependencies |
| Target URL | `https://www.crcgroup.com/About-Us/About-Us` |

The Extent report also records the live environment (Environment, Application URL, Browser, Headless, OS, Java, User) at report-build time.

### Environment setup used
```powershell
# JDK 17 (user scope, no admin)
winget install --id Microsoft.OpenJDK.17 --exact --scope user

# Maven 3.9.9 (portable, extracted to %USERPROFILE%\tools)

# Persist for the user
setx JAVA_HOME "C:\Users\ANikhare\AppData\Local\Programs\Microsoft\jdk-17.0.10.7-hotspot"
# Add %JAVA_HOME%\bin and Maven bin to PATH

# One-off: trust corporate Zscaler CA in the JDK (needed behind Zscaler)
keytool -importcert -noprompt -trustcacerts -alias zscaler-root-ca `
  -file zscaler-root.cer -cacerts -storepass changeit
```

## 5. Class-by-class function reference (`src/main/java/com/crcgroup/automation`)

### `config/ConfigReader.java`
Loads `config.properties` once (static block); `-D` system properties override file values.
- `get(String key)` / `get(String key, String default)` — raw string value with optional fallback.
- `get(ConfigKey key, String default)` — typed-key lookup.
- `getBoolean(String/ConfigKey, boolean default)` — boolean value.
- `getInt(ConfigKey, int default)` / `getLong(ConfigKey, long default)` — numeric values (safe parse).

### `constants/FrameworkConstants.java`
Holds constants only (private constructor): config/log file names, default waits/retries, output directories, timestamp pattern.

### `enums/BrowserType.java`
- `from(String value)` — maps text to `CHROME/FIREFOX/EDGE`, defaulting to `CHROME`.

### `enums/WaitStrategy.java`
Enum values `NONE/PRESENCE/VISIBLE/CLICKABLE` describing wait intent.

### `enums/ConfigKey.java`
- `key()` — returns the underlying property string for each config key (no magic strings).

### `exceptions/`
- `FrameworkException` — base unchecked exception (message / message+cause constructors).
- `ElementNotFoundException` — thrown when no locator strategy resolves.
- `ConfigException` — thrown on config load/parse errors.

### `driver/DriverManager.java`
Thread-safe `ThreadLocal<WebDriver>` holder.
- `getDriver()` — current thread's driver (throws if not initialised).
- `setDriver(WebDriver)` / `unload()` — package-private lifecycle used by the factory.
- `hasDriver()` — whether a driver exists for this thread.

### `driver/BrowserFactory.java`
- `create(BrowserType, boolean headless)` — returns a configured driver.
- `chrome/firefox/edge(boolean headless)` (private) — per-browser options, download dir, headless flags; WebDriverManager sets up the binary.

### `driver/DriverFactory.java`
- `initDriver()` — builds the driver from config, applies timeouts, maximises, stores in `DriverManager` (idempotent per thread).
- `quitDriver()` — quits and unloads the driver.

### `core/Locator.java`
Named, self-healing locator holding multiple `By` candidates.
- `of(String name, By primary, By... fallbacks)` — factory.
- `name()`, `candidates()`, `primary()`, `toString()`.

### `core/LocatorUtils.java`
Builders for resilient `By` locators in priority order.
- `testId/dataTest/dataQa/ariaLabel/role/name/placeholder/exactText/partialText/css(...)`.
- `quote(String)` (private) — XPath-safe quoting for text with quotes.

### `core/WaitUtils.java`
Explicit-wait helpers (no `Thread.sleep`).
- `waitForPresence/waitForVisible/waitForClickable/waitForInvisible(By)`.
- `waitForOverlayGone(By)` — best-effort wait for loading overlays to disappear.
- `waitForStable(WebElement)` — waits until element stops moving/resizing.
- `waitForPageLoad()` — waits for `document.readyState == complete`.
- `driverWait()` / `sleepQuietly(long)` (private) — internal wait builder and poll delay.

### `core/ElementActions.java`
Reusable element engine with self-healing and stale recovery.
- `find(Locator)` — tries each candidate strategy until visible; logs self-heal.
- `findClickable(Locator)` / `resolvedBy(Locator)` (private) — clickable resolution.
- Interactions: `click`, `jsClick`, `type`, `clear`, `select`, `check`, `uncheck`, `hover`, `doubleClick`, `rightClick`, `dragDrop`, `upload`, `pressEnter`.
- Scrolling: `scrollIntoView`, `scrollToElement`, `scrollBy`.
- Reads: `getText`, `getAttribute`, `isDisplayed`.
- Waits: `waitUntilVisible`, `waitUntilClickable`, `waitUntilInvisible`, `waitUntilStable`.
- Safe variants: `safeClick`, `safeType`, `safeFind` (return status/null instead of throwing).
- `retry(Function<WebDriver,R>, int)` — generic retry; `withStaleRetry(...)` / `sleep()` (private) internal recovery.

### `utils/JavaScriptUtils.java`
- `execute(script, args...)`, `click(el)`, `setValue(el, value)`, `getText(el)`, `scrollIntoView(el)`, `scrollToBottom()`, `highlight(el)`, `isPageReady()`.

### `utils/WindowUtils.java`
- `currentHandle()`, `openNewTab()`, `switchToIndex(int)`, `switchToTitle(String)`, `closeCurrentAndSwitchToFirst()`.

### `utils/FrameUtils.java`
- `switchTo(int|String|By)`, `toParent()`, `toDefault()`.

### `utils/AlertUtils.java`
- `isPresent()`, `getText()`, `accept()`, `dismiss()`, `sendKeys(String)`.

### `utils/FileIoUtils.java`
- `readString(path)`, `writeString(path, content)`, `ensureDirectory(path)`.
- `waitForDownload(dir, extension, Duration)` — polls until a completed download appears.

### `utils/RetryUtils.java`
- `retry(Supplier<T>, int maxAttempts, long delayMillis)` — retries flaky operations; `sleep(long)` (private).

### `utils/ScreenshotUtils.java`
- `viewportBytes()`, `viewportBase64()`, `elementBytes(WebElement)`.
- `fullPageBytes()` — scrolls and stitches slices into one image.
- `save(byte[], name)` — writes a timestamped PNG, returns path.
- `toBase64(byte[])` (static); `toBytes(BufferedImage)` / `sleep()` (private).

### `utils/AssertionUtils.java`
- `soft()` — per-thread `SoftAssert`; `assertAll()` — evaluate + clear.
- `assertTrue`, `assertEquals`, `assertContains` — hard assertions that log to logger and Extent.
- `pass(String)` / `fail(String)` (private) — dual logging.

### `reporting/ExtentReportManager.java`
- `getInstance()` — single `ExtentReports` (Spark reporter + environment metadata).
- `build()` (private) — configures reporter and system info.
- `flush()` — writes the report to disk.

### `reporting/ExtentTestManager.java`
- `startTest(String)` — create a per-scenario node; `getTest()`; `remove()` — `ThreadLocal` cleanup.

### `reporting/ExtentLogger.java`
- `info/pass/fail/skip(String)` — log into the current node (null-safe).
- `failWithScreenshot(msg, base64)` / `passWithScreenshot(msg, base64)` — attach screenshots.

### `pages/BasePage.java`
Base page composing the utilities.
- Constructor — wires `WaitUtils`, `ElementActions`, `JavaScriptUtils`, `WindowUtils`, `FrameUtils`, `AlertUtils` from the current driver.
- `navigate(String url)` (protected) — go to URL and wait for load.
- `getPageTitle()`, `getCurrentUrl()`.

### `pages/AboutUsPage.java`
- `open()` — navigate to the configured About Us URL.
- `isLoaded()` — URL contains `about-us`.
- `headingText()` — self-healing heading lookup (safe).

### `pages/components/BaseComponent.java`
Base for root-scoped reusable components.
- Constructor — binds a root `WebElement` with its own `ElementActions`/`WaitUtils`.
- `root()` — the component's root element.

### `testdata/TestDataReader.java`
- `read(String resourcePath, Class<T>)` — bind JSON to a type via Jackson.
- `readMap(String resourcePath)` — read JSON into a `Map`.

## Prerequisites

- JDK 17+ (`java -version`)
- Maven 3.8+ (`mvn -version`)
- A local browser (Chrome by default; Firefox/Edge supported)

## Install dependencies

```powershell
mvn clean install -DskipTests
```

## Run

```powershell
mvn test                       # default Chrome, visible
mvn test -Dheadless=true       # headless
mvn test -Dbrowser=firefox     # chrome | firefox | edge
mvn test -Dbrowser=edge
mvn test -Dparallel.count=5    # parallel scenario threads
```

## Reports & logs

- Extent report: `test-output/reports/ExtentReport.html`
- Cucumber report: `test-output/cucumber/cucumber-report.html`
- Cucumber JSON: `test-output/cucumber/cucumber.json`
- Screenshots: `test-output/screenshots/`
- Logs: `test-output/logs/automation.log`

