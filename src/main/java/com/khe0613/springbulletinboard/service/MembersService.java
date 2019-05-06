package com.khe0613.springbulletinboard.service;

import com.khe0613.springbulletinboard.domain.members.Members;
import com.khe0613.springbulletinboard.domain.members.MembersRepository;
import com.khe0613.springbulletinboard.dto.members.MembersInfoModifyRequestDto;
import com.khe0613.springbulletinboard.dto.members.MembersSignupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class MembersService {
    @Autowired
    private MembersRepository membersRepository;

    @Transactional
    // 회원 가입
    public void signup(MembersSignupRequestDto dto){
        membersRepository.save(dto.toEntity());
    }

    @Transactional
    // 회원 정보 수정
    public void modifyMemberInfo(MembersInfoModifyRequestDto dto, String id){
        Members member = membersRepository.findById(id);
        member.modifyPassword(dto.getPassword());
        member.modifyTel(dto.getTel());

        // Entity 객체를 수정하면 데이터베이스에 반영됨 (save 함수 필요없음)
        // membersRepository.save(member);
    }

    @Transactional
    // 회원 탈퇴
    public void leaveMember(String id){
        membersRepository.deleteById(id);
    }

    // Members로 반환하지 말고 dto로 반환 할것 (추후 수정)
    @Transactional
    // 로그인할때의 검증과정에서 사용됨
    public Members getMember(Map<String, String> login_info){
        return membersRepository.findById(login_info.get("id"));
    }

    // Members로 반환하지 말고 dto로 반환 할것 (추후 수정)
    @Transactional
    // 사용처
    // 1. 회원가입할때의 검증과정(해당 아이디가 존재하는지 학인)
    // 2. 게시물 등록시, 게시자의 정보를 불러오기 위해
    public Members getMember(String id){
        return membersRepository.findById(id);
    }


}
