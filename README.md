# English_Word_Study
## 프로젝트명 : 영단어 학습 프로그램

- 개발환경 : Eclipse Oxygen, HeidiSQL
- 사용기술 : Java, MySQL

- 개발 인원 : 3명
- 프로젝트 기간 :  2017.09.12 - 2017.12.05 (총 85일;약 2달 2주)

---

### <프로젝트 개발>

프로그램 개발 시, 현재의 불편함을 해결하는 것에 초점을 맞추어 주제를 선정하였다. 영단어 공부에 어려움에 팀원들이 가장 많이 공감하여 해당 내용을 기반으로 설계하게 되었다.

실제 영단어를 공부하는 방법들을 모아, 기능으로 설계하였다. DataBase를 사용해보며, 원하는 기능을 보다 더 효율적으로 활용하였다.

---

### <프로젝트 소개>

언제든 원하는 기능을 추가/검색/오답 단어를 확인할 수 있는 화면과 오답 단어로만 재시험을 칠 수 있도록 하는 화면, 또 오답여부와 관계없이 시험을 볼 수 있는 내용과 시험 결과를 보여주는 화면, 학습을 하며 일정 시간동안 반복하여 단어를 보여주는 화면으로 구성하였다. 세부적인 내용에 따라 별개의 .java로 구성하였다.

- **담당 업무** : 프로젝트 팀장, 디자인 제작, 기능 설계 및 SW/TW/MV/RW 구현, 데이터베이스 설계

- 시스템 구성

![database](https://user-images.githubusercontent.com/45550607/85915319-5126a300-b881-11ea-840a-fe025843873e.png)

**[EWS]** : Project Main 화면

**[SW]** : Study Word, 랜덤한 30개의 영단어응 7초(자동) 혹은 수동으로 반복 학습 ****

**[TW]** : Test Word, SW로 학습한 내용으로 시험 응시

**[TR]** : Test Result, TW/RW의 결과를 보여줌

**[MV]** : More View, 영단어 추가, 검색, 오답 확인

     -   오답만 txt file 생성 가능

**[RW]** : Restudy Word, 오답 단어들로만 시험 응시

- 데이터베이스(DB) 구성

![database_code](https://user-images.githubusercontent.com/45550607/85915320-51bf3980-b881-11ea-8fce-a11c34acf1fc.png)

▲ Database에서 찾아 Project로 연결하는 코드

- Idx(key) : 각 단어의 부여하는 숫자

- word : 영단어

- mean : 한글 뜻

- Passed : 시험 후 통과 유무에 따라 체크

- 프로젝트 실행 화면

- 첫 화면(EWS)

![first](https://user-images.githubusercontent.com/45550607/85915321-51bf3980-b881-11ea-8380-1b14aa8af7ca.png)

- 학습 화면(SW)

![slide](https://user-images.githubusercontent.com/45550607/85915317-4ff57600-b881-11ea-9885-3df674f7a416.png)

- 시험 화면(TW/RW)

![test](https://user-images.githubusercontent.com/45550607/85915318-5126a300-b881-11ea-9c94-8825cd3dbb8f.png)

- 추가 기능 화면(MV)

![menu](https://user-images.githubusercontent.com/45550607/85915322-5257d000-b881-11ea-9c1f-257ae16ed391.png)

- 현재 등록된 단어 확인 화면

![not_correct_output](https://user-images.githubusercontent.com/45550607/85915324-52f06680-b881-11ea-98cd-e66b4b05f68b.png)

- 틀린단어 모음 화면

![not_correct](https://user-images.githubusercontent.com/45550607/85915323-52f06680-b881-11ea-9d16-343017f9059b.png)
