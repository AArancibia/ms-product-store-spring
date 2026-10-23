package com.bodega.api.shared.utils;

public class Helpers {

  public static String generateRandomLetters(int length) {
    StringBuilder letters = new StringBuilder();
    for (int i = 0; i < length; i++) {
      char randomLetter = (char) ('A' + (int) (Math.random() * 26));
      letters.append(randomLetter);
    }
    return letters.toString();
  }

  public static String generateRandomDigits(int length) {
    StringBuilder digits = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int randomDigit = (int) (Math.random() * 10);
      digits.append(randomDigit);
    }
    return digits.toString();
  }
}
