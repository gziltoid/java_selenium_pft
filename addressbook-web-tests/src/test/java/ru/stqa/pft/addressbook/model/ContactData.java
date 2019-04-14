package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private String group;

  public ContactData(String firstname, String lastName, String group) {
    this.firstName = firstname;
    this.lastName = lastName;
    this.group = group;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getGroup() {
    return group;
  }
}
