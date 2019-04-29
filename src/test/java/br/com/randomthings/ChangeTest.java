package br.com.randomthings;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ChangeTest {
	
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
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[4]/a")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div/div[1]/button")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/table/tbody/tr[1]/td[2]/div/div/input")).clear();
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/table/tbody/tr[1]/td[2]/div/div/input")).sendKeys("1");
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"btnSaveField\"]")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[5]/a")).click();
		Thread.sleep(1500);
		navegador.get("http://localhost:3000/admin/trocas/solicitacoes");
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[5]/a")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"tableList\"]/tr/td[2]")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[3]/button")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"tableList\"]/tr/td[4]/a")).click();
		Thread.sleep(1500);
		navegador.get(caminho);
		Thread.sleep(1500);
		navegador.findElement(By.id("email")).sendKeys("admin@admin.com");
		navegador.findElement(By.id("password")).sendKeys("admin1234");
		navegador.findElement(By.id("entrar")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[5]/a")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[6]/a")).click();
		
		Thread.sleep(2000);
		navegador.close();
	}
	
//	@Test
//	public void finishOrder() throws InterruptedException {
//		navegador = new ChromeDriver();
//		navegador.get("http://localhost:3000/admin/pedidos/aprovado");
//		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		Thread.sleep(3000);
//		navegador.findElement(By.xpath("//*[@id=\"tableList\"]/tr/td[2]")).click();
//		Thread.sleep(1500);
//		navegador.findElement(By.xpath("//*[@id=\"btnInactiveProduct\"]")).click();
//		Thread.sleep(1500);
//		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[4]/a")).click();
//		Thread.sleep(1500);
//		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[4]/div/ul/li[2]/a")).click();
//		Thread.sleep(1500);
//		navegador.findElement(By.xpath("//*[@id=\"tableList\"]/tr/td[4]/a")).click();
//		Thread.sleep(1500);
//		navegador.get(caminho);
//		
//		Thread.sleep(3000);
//		
//		// login
//		navegador.findElement(By.id("email")).sendKeys("admin@admin.com");
//		navegador.findElement(By.id("password")).sendKeys("admin1234");
//		navegador.findElement(By.id("entrar")).click();
//		
//		Thread.sleep(1500);
//		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[4]/a")).click();
//		
//		Thread.sleep(2000);
//		navegador.close();
//	}

}
