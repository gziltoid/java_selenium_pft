package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().goToHomePage();

    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToGroupPage();
      app.getGroupHelper().createGroup(new GroupData("test3", null, null));
      app.getContactHelper().createContact(new ContactData("test1", "test2", "test3"));
    }

    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getNavigationHelper().goToHomePage();
  }
}
