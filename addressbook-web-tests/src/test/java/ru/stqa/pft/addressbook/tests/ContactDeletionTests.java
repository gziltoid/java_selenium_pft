package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test3").withFooter("test").withHeader("test"));
      app.contact().createContact(new ContactData().withFirstName("test1").withLastName("test2").withGroup("test3"));
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    Set<ContactData> before = app.contact().all();

    ContactData contact = before.iterator().next();
    app.contact().delete(contact);

//    app.contact().selectContact(before.size() - 1);
//    app.contact().deleteSelectedContacts();

//    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Set<ContactData> after = app.contact().all();
//    before.remove(contact);
//    assertThat(after, equalTo(before));

    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(contact);
    Assert.assertEquals(before, after);
  }
}
