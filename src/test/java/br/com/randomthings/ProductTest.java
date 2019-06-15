package br.com.randomthings;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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
	public void crud() throws InterruptedException{
		navegador = new ChromeDriver();
		navegador.get(caminho);
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(3000);
		navegador.findElement(By.id("btnNewProduct")).click();
		navegador.findElement(By.id("productName")).sendKeys("Calça jeans");
		navegador.findElement(By.id("productDescription")).sendKeys("calça muito boa, excelente :v");
		navegador.findElement(By.id("productBarCode")).sendKeys("69S269");
		new Select(navegador.findElement(By.id("productPricingGroup"))).selectByVisibleText("Platinum");
		new Select(navegador.findElement(By.id("productsubCategory"))).selectByVisibleText("Calça - Moda Masculina");
		navegador.findElement(By.id("tabDescricao")).click();
		navegador.findElement(By.id("btnNewDescription")).click();
		new Select(navegador.findElement(By.id("productFields"))).selectByVisibleText("Tamanho");
		navegador.findElement(By.id("productFieldValor")).sendKeys("38");
		Thread.sleep(1000);
		navegador.findElement(By.id("btnSaveField")).click();
		Thread.sleep(1500);
		navegador.findElement(By.id("tabFotos")).click();
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div/div[1]/div[2]/div/div/div")).click();
		Thread.sleep(1000);
		
		Robot robo;
		try {
			robo = new Robot();
			Thread.sleep(1000);
			robo.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(1000);    
			robo.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread.sleep(500);
		
		
		Thread.sleep(2000);
		navegador.findElement(By.id("btnSaveProduct")).click();
		Thread.sleep(1000);
		
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();
		
		Thread.sleep(1000);
		
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div/label/input")).click();
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div/label/input")).sendKeys("Calça jeans");
		Thread.sleep(1000);
		
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[3]/a")).click();
		
		navegador.findElement(By.id("productName")).sendKeys(" Masculino");
		
		navegador.findElement(By.id("productDescription")).sendKeys(" Recomendo");
		navegador.findElement(By.id("tabDescricao")).click();
		navegador.findElement(By.id("btnNewDescription")).click();
		new Select(navegador.findElement(By.id("productFields"))).selectByVisibleText("Material");
		navegador.findElement(By.id("productFieldValor")).clear();
		navegador.findElement(By.id("productFieldValor")).sendKeys("Algodão");
		Thread.sleep(1000);
		navegador.findElement(By.id("btnSaveField")).click();
		Thread.sleep(1500);
		navegador.findElement(By.id("btnSaveProduct")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();
		Thread.sleep(1000);
		
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div/label/input")).click();
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div/label/input")).sendKeys("Calça jeans");
		Thread.sleep(1000);
		
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[4]/a")).click();
		Thread.sleep(1500);
		navegador.findElement(By.id("statuDescription")).sendKeys("Poucas vendas");
		navegador.findElement(By.id("btnInactiveProduct")).click();
		Thread.sleep(2500);
		navegador.close();
	}
	
	private void adicionarArquivo() throws AWTException, InterruptedException {
		
	}

}
