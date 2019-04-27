package com.khe0613.springbulletinboard.service;

import com.khe0613.springbulletinboard.domain.member.Member;
import com.khe0613.springbulletinboard.domain.member.MemberRepository;
import com.khe0613.springbulletinboard.dto.LoginRequestDto;
import com.khe0613.springbulletinboard.dto.member.MemberSignupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public void signup(MemberSignupRequestDto dto){
        memberRepository.save(dto.toEntity());
    }

    @Transactional
    public Member getMember(LoginRequestDto dto){
        return memberRepository.findById(dto.getId());
    }
}
