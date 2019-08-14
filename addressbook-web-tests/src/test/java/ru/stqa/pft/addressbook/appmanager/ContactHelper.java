package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {

  private boolean acceptNextAlert = true;

  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contact, boolean creation) {
    type(By.name("firstname"), contact.getFirstName());
    type(By.name("lastname"), contact.getLastName());
    type(By.name("home"), contact.getHomePhone());
    type(By.name("mobile"), contact.getMobilePhone());
    type(By.name("work"), contact.getWorkPhone());
    attach(By.name("photo"), contact.getPhoto());

    if (creation) {
      if (contact.getGroup() != null) {
        new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroup());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void returnToHomePage() {
//    click(By.linkText("home page"));
    if(isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void initContactModification() {
    click(By.cssSelector("img[title='Edit']"));
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
    String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
    String home = driver.findElement(By.name("home")).getAttribute("value");
    String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
    String work = driver.findElement(By.name("work")).getAttribute("value");
    driver.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }

  public void initContactModificationById(int id) {
    WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    returnToHomePage();
  }

  private void selectContactById(int id) {
//    driver.findElements(By.name("selected[]")).g.click();
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void selectContact(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContacts() {
    acceptNextAlert = true;
    click(By.cssSelector("input[value=\"Delete\"]"));
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  public int count() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = driver.findElements(By.name("entry"));

    for (WebElement element : elements) {
      String firstName = element.findElement(By.cssSelector("tr > td:nth-child(3)")).getText();
      String lastName = element.findElement(By.cssSelector("tr > td:nth-child(2)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
      contacts.add(contact);
    }
    return contacts;
  }

  //  public Set<ContactData> all() {
//    Set<ContactData> contacts = new HashSet<ContactData>();
//    List<WebElement> elements = driver.findElements(By.name("entry"));
//
//    for (WebElement element : elements) {
//      String firstName = element.findElement(By.cssSelector("tr > td:nth-child(3)")).getText();
//      String lastName = element.findElement(By.cssSelector("tr > td:nth-child(2)")).getText();
//      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
//      contacts.add(contact);
//    }
//    return contacts;
//  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = driver.findElements(By.name("entry"));

    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstName = cells.get(1).getText();
      String lastName = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAllPhones(allPhones);
      contacts.add(contact);
    }
    return contacts;
  }
}
