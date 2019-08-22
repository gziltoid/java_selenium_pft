package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Groups groups = app.db().groups();
    if (groups.size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test3"));
    }

    File photo = new File("src/test/resources/stru.png");
    ContactData newContact = new ContactData().withFirstName("test1").withLastName("test2").withPhoto(photo)
            .inGroup(groups.iterator().next());

    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    app.contact().createContact(newContact);
    Assert.assertEquals(app.contact().count(), before.size() + 1);
    List<ContactData> after = app.contact().list();
    before.add(newContact.withId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId()));
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

//  @Test
//  public void testCurrentDir() {
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsolutePath());
//    File photo = new File("src/test/resources/stru.png");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }
}
