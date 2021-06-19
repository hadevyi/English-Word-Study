![image](https://user-images.githubusercontent.com/45550607/122639731-7cf80400-d136-11eb-8e05-e42b9a22ece3.png)

<div align="right">

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-nc-nd/4.0/) [![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0) <a href="https://hits.seeyoufarm.com"/><img src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https://github.com/eona1301/English_Word_Study"/></a>

</div>

## 영단어 학습 웹 애플리케이션 📑
> 프로그램을 개발할 때 현재의 불편함을 해결하는 데 초점을 맞추었습니다. 해당 학기에 영단어 시험이 있었기에, 이에 대한 어려움에 가장 공감했기에 주제로 삼았습니다.<br>
> 실제 영단어를 공부하는 방법을 테스트하면서 기능으로 설계했습니다. 또한 모의 시험이 끝난 후 공부가 다시 필요한 단어 리스트를 별도의 파일로 저장할 수 있도록 기능을 추가했습니다.

- 개발스택 : Java, MySQL
- 개발인원 : 3인
- 개발환경 : Eclipse Oxygen, HeidiSQL
- 개발기간 : 2017.09.12 - 2017.12.05 (총 85일)


<br>

### 1️⃣ 개요

언제든지 오답 단어를 추가 / 검색 / 체크 할 수있는 화면, 오답 단어만으로 재시험을 볼 수있는 화면, 응시할 수있는 내용과 시험 결과를 보여주는 화면 오답 유무에 관계없이 테스트하고 학습하는 동안 일정 시간이 지남에 따라 단어를 반복적으로 보여주는 화면으로 구성되었습니다. 그리고 이 화면에 따라 별도의 .java로 구성되어 있습니다.

+ **내 역할**
  + 프로젝트 팀장
  + 전체 디자인 구성
  + SW/TW/MV/RW 페이지 기능 구현
  + DataBase 설계 및 구현

<br>

### 2️⃣ 프로젝트 구성

<div align="center">

![database](https://user-images.githubusercontent.com/45550607/85915319-5126a300-b881-11ea-840a-fe025843873e.png)

</div>

| 기능  |                                          설명                                          |
| :---: | :------------------------------------------------------------------------------------: |
|  EWS  |                                   Project Main 화면                                    |
|  SW   |            30개의 랜덤한 영단어는 7초마다 바꾸어 표시함. 수동 변경도 가능함            |
|  TW   | SW에서 선택된 30개의 영단어를 바로 시험칠 수 있음. 이때, 영어와 한글의 문제가 섞여나옴 |
|  TR   |          TW의 테스트 결과를 표출함. 이때 틀린 단어들은 DataBase에 따로 표기됨          |
|  MV   |             추가기능으로 이동할 수 있음 - 단어 추가/검색/틀린 단어 리스트              |
|       |                   이때, 틀린 단어 리스트는 txt 파일로 저장할 수 있음                   |
|  RW   |                       틀린 단어들을 기준으로 재시험을 칠 수 있음                       |

<br>

### 3️⃣ Database 구성

<div align="center">

![database_code](https://user-images.githubusercontent.com/45550607/85915320-51bf3980-b881-11ea-8fce-a11c34acf1fc.png)

</div>

▲ 프로젝트에서 단어의 리스트를 찾아오는 코드

- type의 keyword를 통해서 어떠한 리스트를 반환할 지 결정하게 됨

|   Name   |                         Explanation                          |
| :------: | :----------------------------------------------------------: |
| Idx(key) |                     각 단어의 고유 숫자                      |
|   word   |                          영어 단어                           |
|   mean   |                           한글 뜻                            |
|  Passed  | 단어 시험 정보(시험 응시 전/통과된 단어/틀린 단어)를 표시함. 이때 틀린 단어는 0으로 저장됨 |

<br>

### 4️⃣ 프로젝트 화면

- 프로젝트 첫 화면(EWS)

<div align="center">

![first](https://user-images.githubusercontent.com/45550607/85915321-51bf3980-b881-11ea-8380-1b14aa8af7ca.png)

</div>

- 학습 화면(SW)

<div align="center">

![slide](https://user-images.githubusercontent.com/45550607/85915317-4ff57600-b881-11ea-9885-3df674f7a416.png)

</div>

- 시험 화면(TW/RW)

<div align="center">

![test](https://user-images.githubusercontent.com/45550607/85915318-5126a300-b881-11ea-9c94-8825cd3dbb8f.png)

</div>

- 다양한 기능으로 전환할 수 있는 화면(MV)

<div align="center">

![menu](https://user-images.githubusercontent.com/45550607/85915322-5257d000-b881-11ea-9c1f-257ae16ed391.png)

</div>

- 틀린 단어 목록 화면

<div align="center">

![not_correct_output](https://user-images.githubusercontent.com/45550607/85915324-52f06680-b881-11ea-98cd-e66b4b05f68b.png)

</div>

- 단어 시험이 끝난 후 결과

<div align="center">

![not_correct](https://user-images.githubusercontent.com/45550607/85915323-52f06680-b881-11ea-9d16-343017f9059b.png)

</div>