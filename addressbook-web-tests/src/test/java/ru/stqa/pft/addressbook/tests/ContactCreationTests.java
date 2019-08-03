package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();

    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test3"));

    ContactData contact = new ContactData("test1", "test2", "test3");
    app.contact().createContact(contact);
    app.goTo().homePage();
    Assert.assertEquals(app.contact().count(), before.size() + 1);

    List<ContactData> after = app.contact().list();

    contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<ContactData>(before), new HashSet<ContactData>(after));
  }
}
