package com.example.javatest.study;

import com.example.javatest.domain.Member;
import com.example.javatest.domain.Study;
import com.example.javatest.member.MemberService;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;

    private final StudyRepository studyRepository;

    public StudyService(MemberService memberService, StudyRepository studyRepository) {
        assert memberService != null;
        assert studyRepository != null;
        this.memberService = memberService;
        this.studyRepository = studyRepository;
    }

    public Study createNewStudy(Long memberId, Study study){
        Optional<Member> member = memberService.findById(memberId);
        if (member.isPresent()){
            study.setOwnerId(memberId);
        }else{
            throw new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'");
        }

        Study newStudy = studyRepository.save(study);
        memberService.notify(newStudy);
        return newStudy;
    }

    public Study openStudy(Study study){
        study.open();
        Study openedStudy = studyRepository.save(study);
        memberService.notify(openedStudy);
        return openedStudy;
    }

    public void hi(){

    }
}
