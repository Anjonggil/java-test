package com.example.javatest.study;

import com.example.javatest.domain.Member;
import com.example.javatest.domain.Study;
import com.example.javatest.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock MemberService memberService;

    @Mock StudyRepository studyRepository;

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
        member.setEmail("qws348@naver.com");

        Mockito.when(memberService.findById(1L)).thenReturn(Optional.of(member));
        studyService.createNewStudy(1L, new Study(10, "Java"));
    }

}