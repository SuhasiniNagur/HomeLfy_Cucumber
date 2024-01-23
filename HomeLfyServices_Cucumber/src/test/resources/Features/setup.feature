Feature: Android setup 

  Scenario Outline: Setup Functionality
    Given Setup Appium Server
    When Login the App Email as "<email>" and Password as "<password>"
    Then logOut the App

  Examples:
    | email                    | password    |
    | suhasinivnagur8600@gmail.com | Suhasini@123 |