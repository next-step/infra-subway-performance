## 4단계 - 인덱스 설계
### 요구사항
- [ ] 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
    - M1의 경우엔 시간 제약사항을 달성하기 어렵습니다. 2배를 기준으로 해보시고 어렵다면, 일단 리뷰요청 부탁드려요
    - [ ] Coding as a Hobby 와 같은 결과를 반환하세요.
    - [ ] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
    - [ ] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
    - [ ] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
    - [ ] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)