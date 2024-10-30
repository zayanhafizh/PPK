package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.MemberDto;

import java.util.List;

public interface MemberService {
    MemberDto createMember(MemberDto memberDto);
    MemberDto updateMember(MemberDto memberDto);
    void deleteMember(MemberDto memberDto);
    List<MemberDto> getMembers();
    MemberDto getMember(Long id);
}