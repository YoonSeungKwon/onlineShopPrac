package yoon.project.onlineShop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoon.project.onlineShop.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


}
