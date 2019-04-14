package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver driver) {
    super(driver);
  }

  public void goToHomePage() {
    click(By.linkText("home"));
  }

  public void goToGroupPage() {
    click(By.linkText("groups"));
  }

//  public void returnToHomePage() {
//    click(By.linkText("home"));
//  }
}
