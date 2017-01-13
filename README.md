# CallcenterKata

A small Java 8 TDD Kata to practice refactoring conventional Java 7 filter mechanisms to the functional Java 8 approach.

CAREFUL: This is already the solution so if you don't want to spoil yourself just delete `CallComponent.java`and `CallComponentTest.java` and follow the instructions.

## Instructions

`HiPath.java`, `Person.java` and `PersonImpl.java`are given at the start of the Kata.

It is your job to build a `CallcenterComponent`that is used to call different groups of people. There are a few requirements coming in from your Product Owner during time. First you should try to implement these requirements using only Java 7 mechanisms. Implement one requirement at a time. Afterwards you should try to refactor your filtering mechanism to use a functional Java 8 approach.

## Requirements

1. Your Callcenter should call all adult persons (over and including 18).
2. Your Callcenter should call all active airline pilots. Airline pilots are allowed to fly between 25 and 65.
3. Your Callcenter should call all female persons between 14 and 18 to promote the new Justin Bieber album.

## Credits

Thanks to [Peter Fichtner](https://github.com/pfichtner) for the idea

