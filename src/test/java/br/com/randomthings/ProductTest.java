package br.com.randomthings;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ProductTest {
	
	private WebDriver navegador;
	private String caminho = "http://localhost:3000/admin/produtos/listar-produtos";

	@Test
	public void create() throws InterruptedException {
		navegador = new ChromeDriver();
		navegador.get(caminho);
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(3000);
		navegador.findElement(By.id("btnNewProduct")).click();
		navegador.findElement(By.id("productName")).sendKeys("Suco de laranja");
		navegador.findElement(By.id("productDescription")).sendKeys("AI, que delícia!!!!!");
		navegador.findElement(By.id("productBarCode")).sendKeys("69S269");
		new Select(navegador.findElement(By.id("productPricingGroup"))).selectByVisibleText("Platinum");
		new Select(navegador.findElement(By.id("productsubCategory"))).selectByVisibleText("Refrgerante - Bebidas");
		new Select(navegador.findElement(By.id("productsubCategory"))).selectByVisibleText("Saia - Moda Feminina");
		navegador.findElement(By.id("btnSaveProduct")).click();
		System.out.println(navegador.findElement(By.className("swal-modal")).findElement(By.className("swal-title")).getText());
		
		Thread.sleep(10000);
//		navegador.close();
	}

}
