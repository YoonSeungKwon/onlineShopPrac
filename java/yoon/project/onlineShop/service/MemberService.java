package yoon.project.onlineShop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yoon.project.onlineShop.domain.Members;
import yoon.project.onlineShop.dto.request.MemberLoginDto;
import yoon.project.onlineShop.dto.request.MemberRegisterDto;
import yoon.project.onlineShop.dto.response.MemberResponse;
import yoon.project.onlineShop.enums.Role;
import yoon.project.onlineShop.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private MemberResponse toResponse(Members member){
        return new MemberResponse(member.getId(), member.getName(), member.getRoleKey(), member.getRegdate());
    }

    public MemberResponse getInfo() {
        Members member = (Members) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(member == null)
            return null;
        return toResponse(member);
    }

    public MemberResponse register(MemberRegisterDto dto){
        Members member = Members.builder()
                .id(dto.getId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .role(Role.USER)
                .build();
        return toResponse(memberRepository.save(member));
    }

    public MemberResponse login(MemberLoginDto dto)throws UsernameNotFoundException, BadCredentialsException {
        String username = dto.getId();
        String password = dto.getPassword();

        Members member = memberRepository.findMembersById(username);

        if(member == null){
            throw new UsernameNotFoundException(username);
        }
        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new BadCredentialsException(username);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(member, null ,member.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return toResponse(member);
    }
}
