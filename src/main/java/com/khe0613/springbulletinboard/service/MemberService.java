package com.khe0613.springbulletinboard.service;

import com.khe0613.springbulletinboard.domain.member.Member;
import com.khe0613.springbulletinboard.domain.member.MemberRepository;
import com.khe0613.springbulletinboard.dto.member.MemberInfoModifyRequestDto;
import com.khe0613.springbulletinboard.dto.member.MemberSignupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    // 회원 가입
    public void signup(MemberSignupRequestDto dto){
        memberRepository.save(dto.toEntity());
    }

    @Transactional
    // 회원 정보 수정
    public void modifyMemberInfo(MemberInfoModifyRequestDto dto, String id){
        Member member = memberRepository.findById(id);
        member.modifyPassword(dto.getPassword());
        member.modifyTel(dto.getTel());

        memberRepository.save(member);
    }

    @Transactional
    // 회원 탈퇴
    public void leaveMember(String id){
        memberRepository.deleteById(id);
    }


    @Transactional
    // 로그인할때의 검증과정에서 사용됨
    public Member getMember(Map<String, String> login_info){
        return memberRepository.findById(login_info.get("id"));
    }

    @Transactional
    // 회원가입할때의 검증과정(해당 아이디가 존재하는지 학인)에서 사용됨
    public Member getMember(String id){
        return memberRepository.findById(id);
    }


}
