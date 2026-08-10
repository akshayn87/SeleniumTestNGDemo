package com.crcgroup.automation.listeners;

import com.crcgroup.automation.config.ConfigReader;
import com.crcgroup.automation.enums.ConfigKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/** Retries a failed test up to the configured retry count. */
public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger LOG = LoggerFactory.getLogger(RetryAnalyzer.class);
    private final int maxRetries = ConfigReader.getInt(ConfigKey.RETRY_COUNT, 1);
    private int attempt = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (attempt < maxRetries) {
            attempt++;
            LOG.warn("Retrying '{}' (attempt {}/{})", result.getName(), attempt, maxRetries);
            return true;
        }
        return false;
    }
}

