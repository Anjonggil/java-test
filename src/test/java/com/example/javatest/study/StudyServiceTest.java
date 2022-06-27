package com.example.javatest.study;

import com.example.javatest.domain.Member;
import com.example.javatest.domain.Study;
import com.example.javatest.domain.StudyStatus;
import com.example.javatest.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class StudyServiceTest {

    private StudyService studyService;

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @BeforeEach
    private void setUp(){
        MockitoAnnotations.initMocks(this);
        studyService = new StudyService(memberService,studyRepository);
    }

    @Test
    void createStudyService(){
        StudyService studyService = new StudyService(memberService,studyRepository);
        assertNotNull(studyService);
    }

    @Test
    void createNewStudy(){
        StudyService studyService = new StudyService(memberService,studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("qws458@naver.com");

        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Optional<Member> findMember = memberService.findById(1L);
        Assertions.assertThat(findMember.get().getEmail()).isEqualTo("qws458@naver.com");

        assertThrows(RuntimeException.class, () -> {
            memberService.findById(2L);
        });

        assertEquals(Optional.empty(),memberService.findById(1L));
//        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
//
//        memberService.validate(1L);

//        studyService.createNewStudy(1L, new Study(10, "Java"));
    }

    @Test
    @DisplayName("study 생성2")
    void createNewStudy2(){
        Study study = new Study(10, "테스트");

        Member member = new Member();
        member.setId(1L);
        member.setEmail("qws458@naver.com");

//        when(memberService.findById(1L)).thenReturn(Optional.of(member));
//        when(studyRepository.save(study)).thenReturn(study);

        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        studyService.createNewStudy(1L,study);

        assertNotNull(study.getOwnerId());
        assertEquals(member.getId(), study.getOwnerId());
//
//        verify(memberService, times(1)).notify(study);
//        verify(memberService, never()).validate(any());

        then(memberService).should(times(1)).notify(study);
    }

    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudyTest(){
        Study study = new Study(10,"자바 테스트");

        given(studyRepository.save(any())).willReturn(study);

        study = studyService.openStudy(study);

        Assertions.assertThat(study.getStatus()).isEqualTo(StudyStatus.OPEN);
        Assertions.assertThat(study.getOpenedDateTime()).isNotNull();
        then(memberService).should().notify(study);
    }

}