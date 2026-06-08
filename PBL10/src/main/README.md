# 10주차 프론트엔드 코드

10주차 미션에서 사용하는 프론트엔드 코드입니다.
백엔드 API를 브라우저 화면에서 직접 테스트하고, HTTP 통신 흐름을 관찰하기 위한 용도입니다.

> **프론트엔드 코드를 직접 작성하는 과제가 아닙니다.**
> 제공된 코드가 백엔드 API를 어떻게 호출하고 응답을 처리하는지 **읽고 이해**하는 것이 목적입니다.

---

## 사용 방법

1. 이 레포지토리의 파일을 Spring Boot 프로젝트의 `src/main/resources/static/` 디렉토리에 복사합니다.

```
src/main/resources/static/
├── index.html
├── css/
│   └── style.css
└── js/
    ├── member.js
    └── assignment.js
```

2. Spring Boot 애플리케이션을 실행합니다.

```bash
./gradlew bootRun
```

3. 브라우저에서 `http://localhost:8080`에 접속합니다.

> Spring Boot는 `static` 디렉토리의 파일을 자동으로 정적 리소스로 서빙하므로 별도의 설정이 필요 없습니다.

---

## 화면 구성

### 멤버 관리 탭
- 멤버 등록 (LION / STAFF 선택)
- 멤버 목록 조회 + 파트별 필터링
- 멤버 수정 (모달)
- 멤버 삭제

### 과제 관리 탭
- 과제 등록 (멤버 선택 후 제목/설명 입력)
- 전체 과제 조회
- 멤버별 과제 조회
- 과제 단건 조회 (ID 입력)
- 과제 제목 검색 (키워드 입력)
- 과제 수정
- 과제 삭제

### HTTP 통신 로그 패널
화면 하단에 고정된 패널로, 모든 API 호출의 **메서드, URL, 상태 코드, 응답 시간**이 실시간으로 기록됩니다.

### 에러 토스트 알림
API 호출 실패 시 화면 우측 상단에 토스트 알림이 표시됩니다. 백엔드의 `ErrorResponse`에 담긴 `message`가 그대로 표시되며, 3초 후 자동으로 사라집니다.

---

## 연동 API 목록

이 프론트엔드가 호출하는 백엔드 API 목록입니다. 10주차 미션을 완료하면 모든 API가 정상 동작합니다.

### Member API

| JS 함수 | HTTP 요청 |
|---------|-----------|
| `MemberAPI.getAll()` | `GET /members` |
| `MemberAPI.getAll(part)` | `GET /members?part={part}` |
| `MemberAPI.getById(id)` | `GET /members/{id}` |
| `MemberAPI.createLion(data)` | `POST /members/lions` |
| `MemberAPI.createStaff(data)` | `POST /members/staffs` |
| `MemberAPI.updateLion(id, data)` | `PUT /members/lions/{id}` |
| `MemberAPI.updateStaff(id, data)` | `PUT /members/staffs/{id}` |
| `MemberAPI.delete(id)` | `DELETE /members/{id}` |

### Assignment API

| JS 함수 | HTTP 요청 |
|---------|-----------|
| `AssignmentAPI.create(memberId, data)` | `POST /members/{memberId}/assignments` |
| `AssignmentAPI.getAll()` | `GET /assignments` |
| `AssignmentAPI.getByMember(memberId)` | `GET /members/{memberId}/assignments` |
| `AssignmentAPI.getById(id)` | `GET /assignments/{id}` |
| `AssignmentAPI.search(keyword)` | `GET /assignments/search?keyword={keyword}` |
| `AssignmentAPI.update(id, data)` | `PUT /assignments/{id}` |
| `AssignmentAPI.delete(id)` | `DELETE /assignments/{id}` |

---

## 파일별 역할

| 파일 | 설명 |
|------|------|
| `index.html` | 전체 화면 구조, 탭 전환, `httpFetch()` HTTP 통신 래퍼, `showToast()` 에러 토스트 알림 |
| `css/style.css` | 카드, 테이블, 버튼, 로그 패널, 토스트 등 전체 스타일 |
| `js/member.js` | `MemberAPI` 객체 (API 호출) + 멤버 등록/조회/수정/삭제 UI 로직 |
| `js/assignment.js` | `AssignmentAPI` 객체 (API 호출) + 과제 CRUD/검색 UI 로직 |

---

## 핵심 코드: httpFetch

`index.html`의 `httpFetch()` 함수가 모든 API 호출을 감싸는 래퍼입니다.

```
사용자 동작 → JS 함수 → httpFetch() → fetch() API → Spring Controller
                                ↓
                        로그 패널에 기록
                                ↓
                    에러 시 토스트 알림 표시
```

- 정상 응답 (2xx): 응답을 그대로 반환
- HTTP 에러 (4xx, 5xx): 응답 JSON의 `message` 필드를 토스트로 표시 후 throw
- 네트워크 에러 (서버 연결 불가): "서버에 연결할 수 없습니다." 토스트 표시 후 throw

---

## 주의 사항

- 이 코드는 **백엔드 API가 올바르게 구현되어야** 정상 동작합니다.
- 프론트엔드 코드 수정 없이 백엔드만 구현하면 됩니다.
- `http://localhost:8080` 기준으로 동작합니다. 포트가 다르면 API 호출이 실패합니다.
