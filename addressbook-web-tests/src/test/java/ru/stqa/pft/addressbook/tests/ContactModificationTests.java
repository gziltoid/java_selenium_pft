package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
//    app.goTo().homePage();
//    if (!app.contact().isThereAContact()) {
//      app.goTo().groupPage();
//      app.group().create(new GroupData().withName("test3"));
//      app.contact().createContact(new ContactData().withFirstName("test1").withLastName("test2"));
//    }
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      Groups groups = app.db().groups();
      if (groups.size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test3"));
      }
      app.contact().createContact(new ContactData().withFirstName("test1").withLastName("test2").inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testContactModification() {
    Groups groups = app.db().groups();
    List<ContactData> before = app.contact().list();

    app.contact().selectContact(before.size() - 1);
    int id = before.get(before.size() - 1).getId();
    app.contact().initContactModificationById(id);
    ContactData contact = new ContactData().withId(id)
            .withFirstName("test_name").withLastName("test_lastname").inGroup(groups.iterator().next());
    app.contact().fillContactForm(contact, false);
    app.contact().submitContactModification();
    app.contact().returnToHomePage();

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
