package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test3"));
      app.contact().createContact(new ContactData().withFirstName("test1").withLastName("test2").withGroup("test3"));
    }
  }

  @Test(enabled = false)
  public void testContactPhones() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }
}
