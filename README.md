# Todo App
할일을 작성하고 관리하는 앱<br>
## Introduction
할일을 작성하고 관리하는 REST API 입니다. 기본 CRUD 기능을 포함합니다
</br>

**추가사항**

- 기존의 JDBC 로 데이터베이스 액세스하던 방식에서 JPA 를 사용하는 방식으로 변경되었습니다
- Member / Comment 에 대한 CRUD
- 로그인/로그아웃 기능
</br>

## Tech Stack
- **Language**:  
  ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
- **Framework**:

  ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)  
  ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)  
  ![Lombok](https://img.shields.io/badge/Lombok-red?style=for-the-badge&logo=lombok&logoColor=white)

- **Database**:  
  ![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

- **Tools**:  
  ![Git](https://img.shields.io/badge/Git-F05033?style=for-the-badge&logo=git&logoColor=white)  
  ![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

## Installation
리포지토리 클론:
```bash
  git clone https://github.com/Seungmin-J/todo-api-jpa.git
```
## API 명세서
<details>
<summary> Todo API 명세서</summary>

## **1. 일정 생성**
| **Method** | **URL**  | **Request Body**                                                                                                                                                                 | **Response**                                                                                                                                           | **Status**                           |
|------------|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------|
| `POST`     | `/todos` | ```json { ```<br/> ```"title": "제목",```<br/> ``` "contents": "내용", ```<br/> ```"createdAt": "LocalDateTime 일정 생성 시간", ```<br/> ```"editedAt": "LocalDateTime 일정 수정 시간" ```<br/>```}``` | TodoResponseDto | CREATED : 201<br/> BAD_REQUEST : 400 |

---

## **2. 전체 일정 조회 / 수정일, 작성자명 기준 조회**
| **Method** | **URL** | **Request Param**         | **Request Body** | **Response**           | **Status** |
|------------|---------|---------------------------|------------------|------------------------|------------|
| `GET`      | `/todos` | `page`: 페이지|  -               | Page\<TodoResponseDto> | OK : 200   |

---

## **3. 선택 일정 조회**
| **Method** | **URL** | **PathVariable** | **Request Body** | **Response**    | **Status**                   |
|------------|---------|--------------------|------------------|-----------------|------------------------------|
| `GET`      | `/todos/{id}` | `id`: 조회할 일정의 ID | -                | TodoResponseWithCommentsDto | OK : 200<br/>NOT_FOUND : 404 |

---

## **4. 선택 일정의 이름 수정**
| **Method** | **URL** | **PathVariable** | **Request Body**                                               | **Response**    | **Status** |
|------------|---------|--------------------|----------------------------------------------------------------|-----------------|------------|
| `PUT`      | `/todos/{id}` | `id`: 수정할 일정의 ID | ```json {``` </br> `"contents"`: `"내용"` </br> `}` | TodoResponseDto | OK : 200<br/>NOT_FOUND : 404          |

---

## **5. 선택 일정 삭제**
| **Method** | **URL** | **PathVariable** | **Response** | **Status** |
|------------|---------|-------------------|---------|------------|
| `DELETE`   | `/todos/{id}` | `id`: 삭제할 일정의 ID    | -            | OK : 200<br/>BAD_REQUEST : 400|

</details>

<details>
<summary> Member API 명세서 </summary>

## **1. 회원 가입**
| **Method** | **URL**  | **Request Body** | **Response** | **Status** |
|------------|----------|----------------------------------------------------------------|--------------------|------------|
| `POST`     | `/members/signup` | ```json {```<br/>```"memberName": "이름",```<br/>```"email": "이메일",```<br/>```"password": "비밀번호"```<br/>```}``` | MemberResponseDto | OK : 200<br/>BAD_REQUEST : 400 |

---

## **2. 회원 조회 (ID로 조회)**
| **Method** | **URL** | **PathVariable** | **Request Body** | **Response** | **Status** |
|------------|---------|-----------------|------------------|--------------|------------|
| `GET`      | `/members/{id}` | `id`: 조회할 회원의 ID | - | MemberResponseDto | OK : 200<br/>NOT_FOUND : 404 |

---

## **3. 전체 회원 조회**
| **Method** | **URL** | **Request Param** | **Request Body** | **Response**             | **Status** |
|------------|---------|------------------|------------------|--------------------------|------------|
| `GET`      | `/members` | - | - | List\<MemberResponseDto> | OK : 200 |

---

## **4. 회원 정보 수정**
| **Method** | **URL** | **PathVariable** | **Request Body** | **Response** | **Status** |
|------------|---------|-----------------|------------------|--------------|------------|
| `PUT`      | `/members/{id}` | `id`: 수정할 회원의 ID | ```json {```<br/>```"memberName": "이름",```<br/>```"email": "이메일",```<br/>```"password": "비밀번호"```<br/>```}``` | MemberResponseDto | OK : 200<br/>NOT_FOUND : 404 |

---

## **5. 회원 삭제**
| **Method** | **URL** | **PathVariable** | **Response** | **Status** |
|------------|---------|-----------------|--------------|------------|
| `DELETE`   | `/members/{id}` | `id`: 삭제할 회원의 ID | - | OK : 200<br/>BAD_REQUEST : 400 |

---

## **6. 로그인**
| **Method** | **URL** | **Request Body** | **Response** | **Status** |
|------------|----------|----------------------------------------------------------------|----------------|------------|
| `POST`     | `/members/login` | ```json {```<br/>```"email": "이메일",```<br/>```"password": "비밀번호"```<br/>```}``` | `"로그인 성공"` | OK : 200<br/>UNAUTHORIZED : 401 |

---

## **7. 로그아웃**
| **Method** | **URL** | **Request Body** | **Response** | **Status** |
|------------|----------|------------------|------------------|------------|
| `POST`     | `/members/logout` | - | `"로그아웃 되었습니다."` | OK : 200 |

</details>

<details>
<summary>Comment API 명세서</summary>

## **1. 댓글 생성**
| **Method** | **URL** | **PathVariable** | **Request Body** | **Response** | **Status** |
|------------|---------|-----------------|------------------|--------------|------------|
| `POST`     | `/todos/{todoId}/comments` | `todoId`: 댓글을 추가할 Todo의 ID | ```json {```<br/>```"contents": "댓글 내용"```<br/>```}``` | CommentResponseDto | OK : 200<br/>UNAUTHORIZED : 401 |

---

## **2. 댓글 조회 (ID로 조회)**
| **Method** | **URL** | **PathVariable** | **Request Body** | **Response** | **Status** |
|------------|---------|-----------------|------------------|--------------|------------|
| `GET`      | `/comments/{commentId}` | `commentId`: 조회할 댓글의 ID | - | CommentResponseDto | OK : 200<br/>NOT_FOUND : 404 |

---

## **3. 댓글 수정**
| **Method** | **URL** | **PathVariable** | **Request Body** | **Response** | **Status** |
|------------|---------|-----------------|------------------|--------------|------------|
| `PUT`      | `/comments/{commentId}` | `commentId`: 수정할 댓글의 ID | ```json {```<br/>```"contents": "수정된 댓글 내용"```<br/>```}``` | UpdateCommentResponseDto | OK : 200<br/>UNAUTHORIZED : 401<br/>NOT_FOUND : 404 |

---

## **4. 댓글 삭제**
| **Method** | **URL** | **PathVariable** | **Response** | **Status** |
|------------|---------|-----------------|--------------|------------|
| `DELETE`   | `/comments/{commentId}` | `commentId`: 삭제할 댓글의 ID | - | OK : 200<br/>UNAUTHORIZED : 401<br/>NOT_FOUND : 404 |

</details>


# **ERD**
![img.png](src/main/resources/static/img.png)

## Contact
- **Email**: [seungmin103@gmail.com](mailto:seungmin103@gmail.com)
- **GitHub**: [Seungmin-J](https://github.com/Seungmin-J)