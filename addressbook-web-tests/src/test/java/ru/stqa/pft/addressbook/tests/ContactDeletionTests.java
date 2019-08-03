package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test3"));
      app.contact().createContact(new ContactData("test1", "test2", "test3"));
    }
    List<ContactData> before = app.contact().list();
    app.contact().selectContact(before.size() - 1);
    app.contact().deleteSelectedContacts();
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
