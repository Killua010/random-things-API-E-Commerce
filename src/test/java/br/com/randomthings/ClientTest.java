package br.com.randomthings;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ClientTest {
	
	private WebDriver navegador;
	private String caminho = "http://localhost:3000/cadastro";

	@Test
	public void crud() throws InterruptedException {
		navegador = new ChromeDriver();
		navegador.get(caminho);
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(3000);
		// cadastro cliente
		navegador.findElement(By.id("firstName")).sendKeys("Daniel");
		navegador.findElement(By.id("lastName")).sendKeys("Dias de Souza");
		navegador.findElement(By.id("email")).sendKeys("daniel.dias@daniel.com");
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div/div[2]/form/div/div[4]/select")).click();
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div/div[2]/form/div/div[4]/select/option[2]")).click();
		
		navegador.findElement(By.id("birthDate")).sendKeys("16051998");
		navegador.findElement(By.id("phone")).sendKeys("11975905803");
		navegador.findElement(By.id("cpf")).sendKeys("45856020898");
		
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div/div[2]/form/div/div[8]/select")).click();
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div/div[2]/form/div/div[8]/select/option[3]")).click();
		
		navegador.findElement(By.id("password")).sendKeys("qwer1234");
		navegador.findElement(By.id("confirmPassword")).sendKeys("qwer1234");
		Thread.sleep(1000);
		navegador.findElement(By.id("saveClient")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();
		
		
		Thread.sleep(2000);
		// login
		navegador.findElement(By.id("email")).sendKeys("daniel.dias@daniel.com");
		navegador.findElement(By.id("password")).sendKeys("qwer1234");
		navegador.findElement(By.id("entrar")).click();
		
		// edição cliente
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[1]/a")).click();
		Thread.sleep(2000);
		navegador.findElement(By.id("editar-dados-basicos")).click();
		Thread.sleep(1500);
		navegador.findElement(By.id("firstName")).sendKeys("abcd");
		navegador.findElement(By.id("lastName")).sendKeys("efg");
		Thread.sleep(1000);
		navegador.findElement(By.xpath("/html/body/div[3]/div[2]/div/form/div[2]/button")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();
		Thread.sleep(1000);
		// cadastro casinha 1
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[2]/a")).click();
		
		Thread.sleep(1000);
		
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div/h4")).click();
		
		Thread.sleep(1000);
		
		navegador.findElement(By.id("fullName")).sendKeys("minha casinha");
		navegador.findElement(By.id("zipCode")).sendKeys("08589589");
		navegador.findElement(By.id("street")).sendKeys("Rua aleatoria");
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[4]/select")).click();
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[4]/select/option[26]")).click();
		
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[5]/select")).click();
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[5]/select/option[268]")).click();
		
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[6]/select")).click();
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[6]/select/option[2]")).click();
		navegador.findElement(By.id("neighborhood")).sendKeys("das Flores");
		navegador.findElement(By.id("number")).sendKeys("30");
		navegador.findElement(By.xpath("//*[@id=\"favorite\"]")).click();
		
		navegador.findElement(By.xpath("/html/body/div[3]/div[2]/div/form/div[2]/button")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();
		Thread.sleep(1000);
		
		//cadastro casinha 2
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/div/h4")).click();
		
		navegador.findElement(By.id("fullName")).sendKeys("CASA 2");
		navegador.findElement(By.id("zipCode")).sendKeys("08588569");
		navegador.findElement(By.id("street")).sendKeys("Rua Qualquer");
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[4]/select")).click();
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[4]/select/option[26]")).click();
		
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[5]/select")).click();
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[5]/select/option[347]")).click();
		
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[6]/select")).click();
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[6]/select/option[3]")).click();
		navegador.findElement(By.id("neighborhood")).sendKeys("Bunitu");
		navegador.findElement(By.id("number")).sendKeys("40");
		navegador.findElement(By.xpath("//*[@id=\"favorite\"]")).click();
		
		navegador.findElement(By.xpath("/html/body/div[3]/div[2]/div/form/div[2]/button")).click();
		
		Thread.sleep(1000);
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/div[2]/button[1]")).click();
		
		Thread.sleep(1000);
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();
		Thread.sleep(1000);
		
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/ul/li[3]/a")).click();
		// cadastro cartão 1
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div/h4")).click();
		Thread.sleep(1000);
		navegador.findElement(By.id("number")).sendKeys("1234123412341234");
		navegador.findElement(By.id("securityCode")).sendKeys("123");
		navegador.findElement(By.id("printedName")).sendKeys("Daniel Dias de Souza");
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[4]/select")).click();
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[4]/select/option[3]")).click();
		navegador.findElement(By.xpath("//*[@id=\"favorite\"]")).click();
		navegador.findElement(By.xpath("/html/body/div[3]/div[2]/div/form/div[2]/button")).click();
		
		Thread.sleep(1000);
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();
		Thread.sleep(1000);
		// cadastro cartão 2
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/div/h4")).click();
		Thread.sleep(1000);
		navegador.findElement(By.id("number")).sendKeys("4321432143214321");
		navegador.findElement(By.id("securityCode")).sendKeys("321");
		navegador.findElement(By.id("printedName")).sendKeys("Daniel Dias de Souza");
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[4]/select")).click();
		navegador.findElement(By.xpath("//*[@id=\"modal-slide-description\"]/form/div/div[4]/select/option[2]")).click();
		navegador.findElement(By.xpath("//*[@id=\"favorite\"]")).click();
		navegador.findElement(By.xpath("/html/body/div[3]/div[2]/div/form/div[2]/button")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();
		Thread.sleep(1000);
		
		navegador.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/div[2]/button[1]")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/button")).click();
		Thread.sleep(2000);
		navegador.close();
	}

}
