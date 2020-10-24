# English_Word_Study

## English Language Learning Program

<br>
<div align="right">

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-nc-nd/4.0/) [![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0) <a href="https://hits.seeyoufarm.com"/><img src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https://github.com/eona1301/English_Word_Study"/></a>

</div>

- Usage: Java, MySQL
- Development personnel: 3 persons
- Development Environment: Eclipse Oxygen, HeidiSQL
- Project Period: 2017.09.12 - 2017.12.05 (Total 85 days;About 2 months and 2 weeks)

<br>

## Motive

When developing the program, a topic was selected with a focus on solving the current discomfort. The team members sympathized the most with the difficulty in studying English words, so the design was based on the content.

Methods to study actual English words were collected and designed as functions. I tried using DataBase and utilized the desired function more efficiently.

<br>

## Introduce

A screen that allows you to add/search/check the wrong answer word at any time, a screen that allows you to retake the test with only the wrong answer word, and a screen that shows the contents and test results that you can take the test regardless of whether or not you have an incorrect answer, and a schedule while learning It consisted of a screen showing words repeatedly over time. It is composed of separate .java according to the details.

- **My Job** : Project team leader, design production, functional design and SW/TW/MV/RW implementation, database design

<br>

## System configuration

![database](https://user-images.githubusercontent.com/45550607/85915319-5126a300-b881-11ea-840a-fe025843873e.png)

| Function |                                   Detail                                    |
| :------: | :-------------------------------------------------------------------------: |
|   EWS    |                             Project Main Screen                             |
|    SW    | Repetitive learning of 30 random words in 7 seconds (automatic) or manually |
|    TW    |               Take the test with the contents learned with SW               |
|    TR    |                          Show the results of TW/RW                          |
|    MV    |              Add English word, search, check incorrect answer               |
|          |                   Only wrong answers can create txt file                    |
|    RW    |                   Take the test only with incorrect words                   |

<br>

## Database configuration

![database_code](https://user-images.githubusercontent.com/45550607/85915320-51bf3980-b881-11ea-8fce-a11c34acf1fc.png)

▲ Code to find in Database and connect to Project

|   Name   |                      Explanation                      |
| :------: | :---------------------------------------------------: |
| Idx(key) |                  Number of each word                  |
|   word   |                     English word                      |
|   mean   |                    Korean meaning                     |
|  Passed  | After the test, check according to the passing status |

<br>

## Project execution screen

- First screen(EWS)

![first](https://user-images.githubusercontent.com/45550607/85915321-51bf3980-b881-11ea-8380-1b14aa8af7ca.png)

- Learning screen(SW)

![slide](https://user-images.githubusercontent.com/45550607/85915317-4ff57600-b881-11ea-9885-3df674f7a416.png)

- Test screen(TW/RW)

![test](https://user-images.githubusercontent.com/45550607/85915318-5126a300-b881-11ea-9c94-8825cd3dbb8f.png)

- Add-on screen(MV)

![menu](https://user-images.githubusercontent.com/45550607/85915322-5257d000-b881-11ea-9c1f-257ae16ed391.png)

- Current registered word confirmation screen

![not_correct_output](https://user-images.githubusercontent.com/45550607/85915324-52f06680-b881-11ea-98cd-e66b4b05f68b.png)

- Wrong word collection screen

![not_correct](https://user-images.githubusercontent.com/45550607/85915323-52f06680-b881-11ea-9d16-343017f9059b.png)
