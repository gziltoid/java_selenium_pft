package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id = 0;
  private String firstName;
  private String lastName;
  private String group = null;
  private String home;
  private String mobile;
  private String work;

  public String getHome() {
    return home;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.home = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobile = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.work = workPhone;
    return this;
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

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, id);
  }

}
