package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void TestContactCreation() {
    app.getNavigationHelper().goToHomePage();
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test3", null, null));
    app.getContactHelper().createContact(new ContactData("test1", "test2", "test3"));
  }
}
