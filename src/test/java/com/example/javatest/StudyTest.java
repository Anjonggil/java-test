package com.example.javatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 단위 테스트가 아닌 유즈 케이스 테스트시에 의미가 있음
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

    int value;

    @Test
    @Order(1)
    @DisplayName("스터디 만들기") // 이거 사용하자
    void create(){
//        String test_env = System.getenv("TEST_ENV");
//        System.out.println(test_env);
//        Assumptions.assumeTrue("LOCAL".equalsIgnoreCase(test_env));
        Study study = new Study();
        assertNotNull(study);
        assertEquals(StudyStatus.DRAFT,study.getStudyStatus(), () -> "스터디가 생성되면 처음상태는 " +StudyStatus.DRAFT+ " 여야만한다");

        System.out.println("create");
    }

    @Order(2)
    @RepeatedTest(value = 10, name = "{displayedName}")
    @DisplayName("반복 테스트 학습")
    void repeat(RepetitionInfo repetitionInfo){
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" +
                repetitionInfo.getTotalRepetitions());
    }

    @Order(3)
    @ParameterizedTest
//    @ValueSource(strings = {"날씨가","많이","더워지고","있네요"})
    @CsvSource({"10,'자바 스터디'","20,스프링"})
    void repeatTest(Integer limit,String name){
        Study study = new Study(limit,name);
        System.out.println(study);
    }

    @Test
    @Disabled
    void create1(){
        System.out.println("create1");
    }

    @BeforeAll
    void beforeAll(){
        System.out.println("before All");
    }

    @AfterAll
    void afterAll(){
        System.out.println("after All");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("before each");
    }

    @AfterEach
    void afterEach(){
        System.out.println("after each");
    }

}