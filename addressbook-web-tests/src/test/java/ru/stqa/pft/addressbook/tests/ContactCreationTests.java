package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void testContactCreation() {
    app.goTo().goToHomePage();
    int before = app.getContactHelper().getContactCount();
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test3"));
    app.getContactHelper().createContact(new ContactData("test1", "test2", "test3"));
    app.goTo().goToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }
}
