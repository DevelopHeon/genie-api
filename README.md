# genie-api 개발
* ## 사용 기술
  * spring boot
  * mybatis
  * java 8
  * h2 DB
* ### 라이브러리
  * pagehelper
  * lombok
  * modelmapper

* ## ERD 다이어그램
![image](https://user-images.githubusercontent.com/87063007/196070922-7440042b-e392-4516-8260-1838e1addf0c.png)

* ## 패키지 구조
![image](https://user-images.githubusercontent.com/87063007/196103806-4b5e9614-5109-4c01-a6f1-659a714d1bf9.png)

* ## 요구사항
 * ### 아티스트
  * 조회
    + 아티스트 목록 조회 - 아티스트 전체 조회, 페이징, 검색
    + 아티스트 상세 조회 - 아티스트를 선택하여 상세 조회
  * 생성
    + 아티스트 정보를 입력하여 생성할 수 있다.
  * 수정
    + 수정할 값을 입력하여 아티스트 정보를 수정할 수 있다.
  * 삭제
    + 아티스트 ID의 값으로 삭제할 수 있다. 

 * ### 앨범
 * 조회
   + 앨범 목록 조회 - 앨범 전체 조회, 페이징, 검색
   + 앨범 상세 조회 - 앨범을 선택하여 수록된 음원과 등록된 앨범 내용 조회
   + 아티스트 검색 조회 - 앨범 등록시에 아티스트를 검색하여 조회, 페이징, 건수별 조회 가능
 * 생성
   + 앨범 생성 - 앨범 정보를 입력하여 생성, 음원도 함께 등록한다. (음원은 등록하지 않아도 생성 가능)
 * 수정
   + 앨범 수정 - 앨범 생성과 동일하다. 기존의 등록된 음원 삭제 이후 수정시에 작성한 값으로 새로 등록
 * 삭제
   + 앨범 삭제 - 앨범 삭제시 등록된 음원도 모두 삭제 처리
