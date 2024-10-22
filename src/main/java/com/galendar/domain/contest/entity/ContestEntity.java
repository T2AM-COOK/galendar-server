package com.galendar.domain.contest.entity;

import com.galendar.domain.contest.entity.enums.ContestCost;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity // 이 클래스가 JPA 엔티티임을 선언
@Table(name = "tbl_contest") // 이 엔티티가 매핑될 데이터베이스 테이블 이름을 지정
@Getter // Lombok을 사용하여 필드의 getter 메서드 자동 생성
@Setter // Lombok을 사용하여 필드의 setter 메서드 자동 생성
public class ContestEntity {

    @Id // 해당 필드가 엔티티의 기본 키(Primary Key)임을 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 기본 키를 자동 생성하도록 설정, 데이터베이스의 AUTO_INCREMENT를 사용
    private Long id;

    // 공모전 작성자 ID (외부 시스템에서 참조할 수 있는 사용자 또는 멤버 ID)
    private Long memberId;

    // 공모전의 제목
    private String title;

    // 공모전의 내용이나 설명
    private String content;

    // 공모전 비용 (enum 타입으로 사용, 비용이 무료인지 유료인지 구분)
    @Enumerated(EnumType.STRING)
    // EnumType.STRING으로 선언하여 enum 값을 문자열로 데이터베이스에 저장
    private ContestCost cost;

    // 공모전 관련 추가 정보로 연결되는 외부 링크(URL)
    private String link;

    // 공모전 이미지 파일의 URL
    private String imgLink;

    // 공모전 제출 시작 날짜
    private LocalDateTime submitStartDate;

    // 공모전 제출 종료 날짜
    private LocalDateTime submitEndDate;

    // 공모전 개최 시작 날짜
    private LocalDateTime contestStartDate;

    // 공모전 개최 종료 날짜
    private LocalDateTime contestEndDate;

    // 이 엔티티가 생성된 날짜와 시간 (엔티티 생성 시 자동으로 기록)
    private LocalDateTime createdDate;

    // 이 엔티티가 마지막으로 수정된 날짜와 시간 (수정 시 자동으로 기록)
    private LocalDateTime modifiedDate;

}