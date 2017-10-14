package elements;

import manager.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ron on 13/10/2017.
 */
public class Element {
    private static final int DEFAULT_TIME_OUT = 20;

    private WebDriver driver() {
        return DriverManager.getInstance();
    }

    private WebElement findWithWait(By by, long timeout) {
        new FluentWait<>(by)
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(250, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(by1 -> !findAllImmediately(by).isEmpty());
        return driver().findElement(by);
    }

    private WebElement findWithWait(final WebElement ele, final By by, long timeout) {
        new FluentWait<>(ele)
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(250, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(element -> !findAllImmediately(ele, by).isEmpty());
        return ele.findElement(by);
    }

    private List<WebElement> findAllWithWait(final By by, final long timeOutInSeconds) {
        final long startTime = System.currentTimeMillis();
        List<WebElement> elements = new ArrayList<>();

        while (System.currentTimeMillis() - startTime < timeOutInSeconds * 1000) {
            elements = findAllImmediately(by);
            if (!elements.isEmpty()) {
                break;
            }
        }
        return elements;
    }

    private List<WebElement> findAllWithWait(final WebElement ele, final By by, final long timeOutInSeconds) {
        final long startTime = System.currentTimeMillis();
        List<WebElement> elements = new ArrayList<>();

        while ((System.currentTimeMillis() - startTime) < timeOutInSeconds * 1000) {
            elements = ele.findElements(by);
            if (!elements.isEmpty()) {
                break;
            }
        }
        return elements;
    }

    public WebElement find(By by) {
        return findWithWait(by, DEFAULT_TIME_OUT);
    }

    public WebElement find(WebElement ele, By by) {
        return findWithWait(ele, by, DEFAULT_TIME_OUT);
    }

    public void clickByJavaScript(final WebElement element) {
        ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", element);
    }

    public List<WebElement> findAllImmediately(WebElement ele, By by) {
        return ele.findElements(by);
    }

    /**
     * Find all elements using the specified By identifier without any wait time.
     *
     * @param by by identifier to find
     * @return List of WebElements matching the by identifier
     */
    public List<WebElement> findAllImmediately(By by) {
        return driver().findElements(by);
    }

    /**
     * Find elements using the specified By identifier with the default timeout time
     *
     * @param by by identifier to find
     * @return List of WebElements matching the by identifier
     */
    public List<WebElement> findAll(By by) {
        return findAllWithWait(by, DEFAULT_TIME_OUT);
    }

    public List<WebElement> findAll(WebElement element, By by) {
        return findAllWithWait(element, by, DEFAULT_TIME_OUT);
    }

    public void waitForJavaScriptReadiness(long timeout) {
        new FluentWait<>(driver()).withTimeout(timeout, TimeUnit.SECONDS).
                pollingEvery(250, TimeUnit.MILLISECONDS).
                until((ExpectedCondition<Boolean>) driver -> (Boolean) ((JavascriptExecutor) driver).
                        executeScript("return document.readyState").toString().equals("complete"));
    }

    /**
     * Wait for a web element to exist
     *
     * @param by               the By identifier to wait for existence
     * @param timeoutInSeconds the time in seconds to wait for existence
     * @return the found web element
     */
    public WebElement waitForElementExists(By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver(), timeoutInSeconds);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public boolean isPresentImmediately(By by) {
        return !findAllImmediately(by).isEmpty();
    }
}
