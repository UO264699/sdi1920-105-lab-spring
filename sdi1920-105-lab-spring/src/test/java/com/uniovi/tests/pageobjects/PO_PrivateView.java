package com.uniovi.tests.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.utils.SeleniumUtils;

public class PO_PrivateView extends PO_NavView{
	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp,
			String scorep)
	{
		//Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		//Seleccionamos el alumnos userOrder
		new Select (driver.findElement(By.id("user"))).selectByIndex(userOrder);
		//Rellenemos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	static public void login(WebDriver driver,String usuario) {
		
		//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_LoginView.fillForm(driver, usuario, "123456" );
		//COmprobamos que entramos en la pagina privada del Profesor
		PO_View.checkElement(driver, "text", usuario);
		
		
	}
	
static public void logout(WebDriver driver,String textoDestino) {
		
	//Ahora nos desconectamos
			PO_PrivateView.clickOption(driver, "logout", "text", textoDestino);
		
		
	}
}