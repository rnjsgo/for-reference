# 참고 코드용 미니 프로젝트
기능 구현 중심이 아닌, 미래에 참고하기 위한 미니 프로젝트

<br>
(24.04.22 ~ 24.04.27)
<br>

## 목차

1. [개발 환경](#개발-환경)
2. [깃 컨벤션](#깃-컨벤션)
3. [프로젝트 구조](#프로젝트-구조)
4. [API 문서 자동화](#api-문서-자동화)
5. [기능 구현](#기능-구현)
6. [로깅, 예외처리, 유효성 검사](#로깅-예외처리-유효성-검사)
7. [단위테스트, 통합테스트](#단위테스트-통합테스트)
8. [CI 관련](#ci-관련)
<br>

## 개발 환경


### 프로그래밍 언어
- **Java**: Oracle OpenJDK 1.8

### 프레임워크 및 라이브러리
- **Spring Boot**: 2.7.18
- **Spring Data JPA**: 2.7.18

### DB
- **H2 Database**: 2.1.214


### 개발 도구
- **IntelliJ IDEA**: IntelliJ IDEA 2022.2.3 (Community Edition)


### 빌드 도구
- **Gradle**: 8.7

### 테스트 도구
- **JUnit 5**: 단위 테스트
- **Mockito**: 모의 객체 생성

### CI
- **GitHub Actions**: 자동화된 빌드 및 테스트, 코드 커버리지 측정 파이프라인

### 문서화 도구
- **Swagger**: API 문서 자동 생성 및 테스트

<br>

## 깃 컨벤션

### 작업 과정
1. 이슈 템플릿, PR 템플릿 생성
2. 작업할 내용 바탕으로 이슈 생성
3. 이슈 번호로 구분한 브랜치 생성 ex) feat/#5 (develop 브랜치에서 분기)
4. 작업 완료 후 develop 브랜치에 PR & Merge
5. 큰 단위의 작업 완료 후 main 브랜치에 PR & Merge (main 브랜치는 배포 목적, 본 프로젝트에서는 배포 과정 x)
6. develop 브랜치와 main 브랜치 싱크 맞추기

### 커밋 컨벤션
- feat : 새로운 기능 추가 
- fix : 변경 사항 관련
- docs : 문서 (문서 추가, 수정, 삭제) 
- conf : 설정 관련
- test : 테스트 코드 추가 
- refactor : 코드 리팩토링 
- style : 코드 의미에 영향을 주지 않는 변경사항
- ci: Continuous Integration 관련

<br>

## 프로젝트 구조
<img width="697" alt="스크린샷 2024-04-27 오후 7 02 54" src="https://github.com/rnjsgo/the-commerce/assets/102651155/64329007-2e6b-4911-8893-42e2ea630595">
<img width="486" alt="스크린샷 2024-04-27 오후 7 03 13" src="https://github.com/rnjsgo/the-commerce/assets/102651155/a0aa0444-02b5-4028-a63b-2be03c25dedc">

### 코드 컨벤션
- Json: lowerCamelCase
- Code Style(IntelliJ Setting): GoogleStyle

<br>

## API 문서 자동화
- Swagger Springdoc 사용
- Swagger API 문서 : "/docs"
- UserApiInterface 인터페이스로 문서화 코드 분리 (컨트롤러 코드 가독성 개선)

<br>


## 기능 구현
### 공통 응답 커스텀 Response
<img width="264" alt="스크린샷 2024-04-27 오후 7 22 51" src="https://github.com/rnjsgo/the-commerce/assets/102651155/00bc24a1-559f-404f-bbf4-8f43e9e6c904">

<br>

### 1. 회원 가입
<img width="319" alt="스크린샷 2024-04-27 오후 7 17 02" src="https://github.com/rnjsgo/the-commerce/assets/102651155/47bcff90-c8b0-487c-9c04-73b307d930ba">

<br>

**Request**

<img width="350" alt="스크린샷 2024-04-27 오후 7 24 23" src="https://github.com/rnjsgo/the-commerce/assets/102651155/96424485-0b10-4ad0-b264-b965881b9ffd">

- Reqeust 데이터 유효성 검사
- userId, nickname과 같은 고유한 정보는 중복 검사

<br>

### 2. 회원 목록 조회
<img width="337" alt="스크린샷 2024-04-27 오후 7 28 25" src="https://github.com/rnjsgo/the-commerce/assets/102651155/32cb3cd5-9333-4789-9b82-3dd5a2c712fa">

<br>

**Query String**

<img width="564" alt="스크린샷 2024-04-27 오후 7 30 18" src="https://github.com/rnjsgo/the-commerce/assets/102651155/08c7f9bf-3ca1-42e6-b1bb-0246a2dd4fa9">

<br>

**Response 예시**

<img width="334" alt="스크린샷 2024-04-27 오후 7 32 02" src="https://github.com/rnjsgo/the-commerce/assets/102651155/39f89411-7f6b-4c05-af0f-913b5fc6dd33">

- name, created_at 컬럼 인덱싱

<br>

### 3. 회원 정보 수정
<img width="344" alt="스크린샷 2024-04-27 오후 7 41 17" src="https://github.com/rnjsgo/the-commerce/assets/102651155/670b85fc-9e00-4414-b7ad-522f3c933eb1">

<br>

**Request**

<img width="349" alt="스크린샷 2024-04-27 오후 7 40 57" src="https://github.com/rnjsgo/the-commerce/assets/102651155/846cce52-bdc2-4aef-bd1d-f6c23359add2">

<br>

**Response 예시**

<img width="261" alt="스크린샷 2024-04-27 오후 7 44 59" src="https://github.com/rnjsgo/the-commerce/assets/102651155/b433f33b-10a2-4953-a9c9-cdea409bec08">

- 수정 가능한 정보만 Request로 받음
- Request 데이터 유효성 검사
- nickname 중복 검사

<br> 

## 로깅, 예외처리, 유효성 검사

### 1. 로깅 (SLF4J + Logback) - 스레드 구분을 위해 스레드 id 부여

**Http Reqeust 정보 로깅 (Interceptor 활용)**

<img width="793" alt="스크린샷 2024-04-27 오후 7 53 32" src="https://github.com/rnjsgo/the-commerce/assets/102651155/a6af7059-2671-4989-98fd-6331c61e95ff">

**메서드 실행 시간 측정 (AOP 적용)**

<img width="786" alt="스크린샷 2024-04-27 오후 7 57 07" src="https://github.com/rnjsgo/the-commerce/assets/102651155/b31ac2b3-e6e4-4f54-a653-30d2f678d4b3">

**SQL 쿼리 파라미터 바인딩 값 로깅**

<img width="258" alt="스크린샷 2024-04-27 오후 7 58 14" src="https://github.com/rnjsgo/the-commerce/assets/102651155/e6641930-4187-4eec-995b-3994af30f9a2">

### 2. 예외 처리

- 예외 상황 별 Custom Exception 작성
- GlobalExceptionHandler 클래스를 통한 전역 예외 처리

<img width="590" alt="스크린샷 2024-04-27 오후 8 02 19" src="https://github.com/rnjsgo/the-commerce/assets/102651155/55dc4e78-5774-4f79-9caa-aac31707935b">

### 3. 유효성 검사


- GlobalValidationHandler 클래스를 통한 AOP 적용
- @Valid 어노테이션과 AOP를 통해 Post, Patch 요청에 대한 유효성 검사 예외 처리 커스텀

<br>

**유효성 검사 실패 시 예시 Response**

<img width="922" alt="스크린샷 2024-04-27 오후 8 08 45" src="https://github.com/rnjsgo/the-commerce/assets/102651155/cb6816c8-eaee-4b6d-be3e-6d400e65a83d">

<br>
<br>

## 단위테스트, 통합테스트
- 레포지토리, 서비스, 컨트롤러 별 단위 테스트 작성, 통합 테스트 작성
- JUnit5 + Mockito 를 통한 테스트
- SQL 스크립트 파일을 통해 테스트에 필요한 더미 데이터 삽입
- BDD (given - when - then) 방식으로 테스트 코드 작성
  

<br>

## CI 관련

### GitHub Actions CI 파이프 라인

**main 브랜치, develop 브랜치에 PR 시 트리거**

1. 빌드
2. 테스트  
3. 테스트 실패 시 코멘트
4. 코드 커버리지 측정 코멘트 (Jacoco)
   -> 코드 커버리지 측정만을 위한 목적으로, 성공 기준을 0%로 설정

**PR comment 예시**

<img width="422" alt="스크린샷 2024-04-27 오후 8 20 09" src="https://github.com/rnjsgo/the-commerce/assets/102651155/0f1e0419-352c-4ee8-afe6-99f7364789cb">

<br>
