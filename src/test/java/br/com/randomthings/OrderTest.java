package br.com.randomthings;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class OrderTest {
	
	private WebDriver navegador;
	private String caminho = "http://localhost:3000/login";

	@Test
	public void createOrder() throws InterruptedException {
		navegador = new ChromeDriver();
		navegador.get(caminho);
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
		// login
		navegador.findElement(By.id("email")).sendKeys("admin@admin.com");
		navegador.findElement(By.id("password")).sendKeys("admin1234");
		navegador.findElement(By.id("entrar")).click();
		
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/a[2]")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/header/div[1]/div[1]/div/ul/li[1]/a")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div/div[3]/div[2]/button[1]")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div/div[2]/div[1]/div[2]/div/div[1]/a")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div/div[2]/button[2]")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/header/div[1]/div[2]/ul/li[2]/a")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div/div[1]/table/tbody/tr[1]/td[4]/div/div[1]/div/div/input")).clear();
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div/div[1]/table/tbody/tr[1]/td[4]/div/div[1]/div/div/input")).sendKeys("2");
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div/div[2]/div/div/a")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div/div[1]/div/div[2]/button")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"modal-address-order\"]/div[1]")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div/div[3]/div/div/a")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/header/div[1]/div[2]/ul/li[3]/div/div/button")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"menu-list\"]/ul/li")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[4]/a")).click();
		Thread.sleep(2000);
		navegador.close();
	}
	
	@Test
	public void finishOrder() throws InterruptedException {
		navegador = new ChromeDriver();
		navegador.get(caminho);
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		navegador.findElement(By.id("email")).sendKeys("admin@admin.com");
		navegador.findElement(By.id("password")).sendKeys("admin1234");
		navegador.findElement(By.id("entrar")).click();
		Thread.sleep(2000);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[4]/a")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[4]/div/ul/li[1]/a")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"tableList\"]/tr/td[2]")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"btnInactiveProduct\"]")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[4]/div/ul/li[2]/a")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"tableList\"]/tr/td[4]/a")).click();
//		Thread.sleep(1500);
//		navegador.get(caminho);
//		
//		Thread.sleep(3000);
		
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/a[2]")).click();
		Thread.sleep(500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/header/div[1]/div[2]/ul/li[3]/div/div/button")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"menu-list\"]/ul/li")).click();
//		// login
//		navegador.findElement(By.id("email")).sendKeys("admin@admin.com");
//		navegador.findElement(By.id("password")).sendKeys("admin1234");
//		navegador.findElement(By.id("entrar")).click();
//		
//		Thread.sleep(1500);
//		navegador.get("http://localhost:3000/perfil");
		
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[4]/a")).click();
		
		Thread.sleep(2000);
		navegador.close();
	}

}
