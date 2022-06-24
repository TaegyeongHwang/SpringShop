# SpringShop

## 개발환경
- OS : macOS Monterey
- IDEA : IntelliJ IDEA
- Language : Java 11
- Framework : Spring Boot, JPA
- Build Tool : Gradle
- Database : H2, MySQL

## Spring Initializr
- Spring Web
- Spring Data JPA
- Lombok
- H2 Database
- MySql

## application.yml
- 개발 & 서버환경을 구분하였습니다.
- dev는 개발환경 H2로 구동됩니다.
- prod는 서버환경 MySql로 구동됩니다.

## SpringShop Entity 연관관계
![스크린샷 2022-06-24 오후 8 27 51](https://user-images.githubusercontent.com/88227465/175528704-644f1dff-a8ad-46ee-8051-dc6ff69205a4.png)


## SpringShop & API
### 상품
- 같은이름의 상품은 등록할 수 없습니다.
- 상품이름으로 상품을 검색합니다.

1. 상품등록 : http://localhost:8080/item/register
2. 상품목록 : http://localhost:8080/item/findList
3. 상품검색 : http://localhost:8080/item/search/아이템이름 ex) http://localhost:8080/item/search/apple

### 장바구니
- 장바구니 저장은 상품번호를 통해 저장됩니다.
- 중복된 상품은 장바구니에 담을 수 없습니다.
- 장바구니 검색은 상품이름으로 검색합니다.
- 장바구니 삭제는 장바구니 번호로 삭제됩니다.

1. 장바구니 저장 : http://localhost:8080/cart/add
2. 장바구니 검색 : http://localhost:8080/cart/search/아이템이름 ex) http://localhost:8080/cart/search/apple
3. 장바구니 삭제 : http://localhost:8080/cart/delete
4. 장바구니 전체삭제 : http://localhost:8080/cart/delete/all

### 구매하기
- 구매하기는 장바구니 번호를 통해 저장됩니다.
- 구매하기는 선택된 장바구니만 구매목록에 저장됩니다.
- 상품의 재고 부족시 구입되지 않습니다.

1. 구매하기 : http://localhost:8080/order/register?cart=장바구니번호 ex) http://localshot:8080/order/register?cart=1&cart=2
2. 구매목록 : http://lcoalhost:8080/order/findList
