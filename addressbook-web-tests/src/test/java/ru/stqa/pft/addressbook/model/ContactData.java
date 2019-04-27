package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private String group;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(group, that.group);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, group);
  }

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
