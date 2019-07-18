package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToHomePage();
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test3", null, null));
    app.getContactHelper().createContact(new ContactData("test1", "test2", "test3"));
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }
}
