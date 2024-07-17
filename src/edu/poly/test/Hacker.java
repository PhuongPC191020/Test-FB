package edu.poly.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Scanner;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Hacker {
    @Test
    public void testSearchAndAccessFacebookProfile() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Truy cập vào trang Facebook
        driver.get("https://www.facebook.com");

        // Tìm và nhập thông tin tài khoản
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("hackerlol1478963@gmail.com");

        // Tìm và nhập mật khẩu
        WebElement passwordInput = driver.findElement(By.id("pass"));
        passwordInput.sendKeys("19102003PhuongHa.#");

        // Tìm và click vào nút Đăng nhập
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();

        // Chờ cho trang đăng nhập xử lý
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("facebook.com"));

        // Tạo một luồng mới để theo dõi việc nhập từ bàn phím
        Thread stopThread = new Thread(() -> {
            System.out.println("Nhấn phím Enter để dừng...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine(); // Chờ người dùng nhấn Enter
            scanner.close();
            driver.quit(); // Dừng WebDriver
        });
        stopThread.start(); // Bắt đầu luồng dừng

        // Vòng lặp vô hạn để thực hiện lướt và like
        while (true) {
            // Lướt xuống cuối trang
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

            // Chờ cho trang load hoàn chỉnh
            try {
                Thread.sleep(2000); // Đợi 2 giây để trang load hoàn chỉnh
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Tìm và like bài viết đầu tiên
            WebElement likeButton = driver.findElement(By.xpath("//div[contains(@class, 'userContentWrapper')]//span[contains(@class, 'UFILikeLink')]"));
            likeButton.click();
        }
    }
}
