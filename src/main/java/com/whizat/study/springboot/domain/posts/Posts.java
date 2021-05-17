package com.whizat.study.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 클래스 내 모든 필드의 Getter 메소드 자동 생성
@Getter
// 기본 생성자 자동 추가
@NoArgsConstructor
// 테이블과 링크될 클래스임을 나타냄
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭함
// SalesManager.java -> sales_manager table
@Entity
public class Posts {

    // 해당 테이블의 PK
    @Id
    // PK 생성 규칙
    // PK auto_increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블의 컬럼, 굳이 선언하지 않더라도 클래스의 모든 필드는 모두 컬럼이 됨(ex: 아래 author)
    // 추가로 변경이 필요한 옵션이 있으면 사용
    // (ex: 문자열의 경우 기본 varchar2(255)인데 사이즈를 늘리거나(length), 타입을 TEXT로 변경하고 싶은 경우)
    @Column(length = 500, nullable = false)
    private  String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 해당 클래스의 빌더 패턴 클래스를 생성
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
