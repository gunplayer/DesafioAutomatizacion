package application;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.seleniumhq.jetty9.util.IO;

import java.io.File;
import java.io.IOException;

//@RunWith(value = Parameterized.class)
public class DesafioAutomatizacion {

    private IO FileUtils;

    @Test
    public void login() throws Exception {
        // TODO Auto-generated method stub

        //System.setProperty("webdriver.chrome.driver", "D:\\Users\\lvaldes.externo\\Desktop\\eIBS\\automation_test_eibs\\src\\main\\resources\\drivers\\windows\\chromedriver.exe");

        System.setProperty("webdriver.chrome.driver", "D:\\Users\\lvaldes.externo\\Desktop\\Desafio_Automatizacion_QA\\bin\\src\\main\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");

        //Accion Hover al boton Women
        WebElement searchBtn = driver.findElement(By.xpath("//*[@title=\"Women\"]"));
        Actions action = new Actions(driver);
        action.moveToElement(searchBtn).perform();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[2]/a")).isDisplayed();
        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[2]/a")).click();
        Thread.sleep(2000);

        WebElement searchBtn2 = driver.findElement(By.xpath("//*[@title=\"Blouse\"]"));
        Actions action2 = new Actions(driver);
        action2.moveToElement(searchBtn2).perform();

        //Seleccionar blusa color negro
        driver.findElement(By.xpath("//*[@id=\"color_7\"]")).click();
        Thread.sleep(3000);

        //Seleccionar talla L
        driver.findElement(By.cssSelector("#group_1 > option:nth-child(3)")).click();
        Thread.sleep(4000);

        //Boton agregar al carro
        driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button")).click();
        Thread.sleep(4000);

        //Boton continuar compra
        //driver.findElement(By.className("continue btn btn-default button exclusive-medium")).click();
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span")).click();
        Thread.sleep(3000);

        //Accion Hover al boton Dresses
        WebElement searchBtn3 = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a"));
        Actions action3 = new Actions(driver);
        action3.moveToElement(searchBtn3).perform();
        Thread.sleep(3000);

        //Click en boton summer dresses
        driver.findElement(By.cssSelector("#block_top_menu > ul > li:nth-child(2) > ul > li:nth-child(3) > a")).click();
        Thread.sleep(3000);

        //Accion Hover sobre vestido largo
        WebElement searchBtn4 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]"));
        Actions action4 = new Actions(driver);
        action4.moveToElement(searchBtn4).perform();
        Thread.sleep(2000);

        //Seleccionar vestido largo
        driver.findElement(By.xpath("//*[@id=\"color_19\"]")).click();
        Thread.sleep(3000);

        //Boton agregar al carro
        driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button")).click();
        Thread.sleep(2000);

        //Evidencia Carro
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("D:\\Users\\lvaldes.externo\\Desktop\\Desafio_Automatizacion_QA\\CarroCompra.png"));

        //Boton Checkout
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium")).click();
        Thread.sleep(3000);

        driver.findElement(By.id("email")).sendKeys("leonardo.valdes.zamorano@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("autotest");
        driver.findElement(By.id("SubmitLogin")).click();

        driver.findElement(By.cssSelector("#center_column > form > p > button")).click();
        Thread.sleep(3000);

        //-----------Validar que costo de despacho sea $2.00

        driver.findElement(By.id("uniform-cgv")).click();

        //Evidencia CostoFinal
        File scrFile2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2, new File("D:\\Users\\lvaldes.externo\\Desktop\\Desafio_Automatizacion_QA\\CostoFinal.png"));

        driver.findElement(By.cssSelector("#form > p > button")).click();

        //Evidencia OrdenCompleta
        File scrFile3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3, new File("D:\\Users\\lvaldes.externo\\Desktop\\Desafio_Automatizacion_QA\\OrdenCompleta.png"));

        //Boton para pago tarjeta de credito
        driver.findElement(By.className("bankwire")).click();
        driver.findElement(By.cssSelector("#cart_navigation > button")).click();
        Thread.sleep(3000);

        //Boton Back to orders
        driver.findElement(By.cssSelector("#center_column > p > a")).click();

        //Evidencia OrderHistory
        File scrFile4 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4, new File("D:\\Users\\lvaldes.externo\\Desktop\\Desafio_Automatizacion_QA\\OrderHistory.png"));

        //Boton Descarga PDF
        driver.findElement(By.className("link-button")).click();
        Thread.sleep(5000);
        driver.findElement(By.className("logout")).click();
        Thread.sleep(3000);

        driver.close();
        driver.quit();

    }

    public void captureScreen() {
        String path;
        WebDriver driver = new ChromeDriver();
        try {
            WebDriver webDriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            path = "./target/screenshots/" + source.getName();
            FileUtils.copyFile(source, new File(path));
        } catch (IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
    }
}