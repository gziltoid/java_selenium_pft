package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"};

//    List<String> languages = new ArrayList<String>();
//    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
//    languages.add("Java");
//    languages.add("C#");
//    languages.add("Python");

//    for (String lang : languages) {
//      System.out.println(lang);
//    }

//    for (int i = 0; i < languages.size(); i++) {
//      System.out.println(languages.get(i));
//    }

    List languages = Arrays.asList("Java", "C#", "Python", "PHP");

    for (Object lang : languages) {
      System.out.println(lang);
    }
  }
}
