package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.MemberDto;
import com.polstat.perpustakaan.entity.Member;
import com.polstat.perpustakaan.mapper.MemberMapper;
import com.polstat.perpustakaan.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = MemberMapper.mapToMember(memberDto);
        member = memberRepository.save(member);
        return MemberMapper.mapToMemberDto(member);
    }

    @Override
    public MemberDto updateMember(MemberDto memberDto) {
        Member member = MemberMapper.mapToMember(memberDto);
        member = memberRepository.save(member);
        return MemberMapper.mapToMemberDto(member);
    }

    @Override
    public void deleteMember(MemberDto memberDto) {
        Member member = MemberMapper.mapToMember(memberDto);
        memberRepository.delete(member);
    }

    @Override
    public List<MemberDto> getMembers() {
        Iterable<Member> members = memberRepository.findAll();
        return StreamSupport.stream(members.spliterator(), false)
                .map(MemberMapper::mapToMemberDto)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDto getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member tidak ditemukan"));
        return MemberMapper.mapToMemberDto(member);
    }
}