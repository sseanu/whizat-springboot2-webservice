package com.whizat.study.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
//여기서는 SpringRunner라는 스프링 실행자를 사용
@RunWith(SpringRunner.class)
//여러 스프링 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
//@Controller, @ControllerAdvice 등 사용 가능
//@Service, @Component, @Repository 사용 불가
@WebMvcTest(HelloController.class)
public class HelloControllerTest {

//     Bean 주입 받음
    @Autowired
//    웹 API 테스트할 때 사용
//    스프링 MVC 테스트의 시작점
//    HTTP, GET, POST 등에 대한 API 테스트 가능
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

//        MockMvc를 통해 /hello 주소로 HTTP GET 요청
//        체이닝 지원
        mvc.perform(get("/hello"))
//                HTTP Header의 Status 검증 (200, 404, 500 등)
//                OK(200)인지 아닌지 검증
                .andExpect(status().isOk())
//                응답 본문의 내용으 검증
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                    get("/hello/dto")
//                            API 테스트할 때 사용될 요청 파라미터 설정. 단 값은 String만 허용됨
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
//                JSON 응답값을 필드별로 검증하는 메소드. $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}