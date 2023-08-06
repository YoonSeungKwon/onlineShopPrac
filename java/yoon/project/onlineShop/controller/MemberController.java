package yoon.project.onlineShop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoon.project.onlineShop.dto.request.MemberLoginDto;
import yoon.project.onlineShop.dto.request.MemberRegisterDto;
import yoon.project.onlineShop.dto.response.MemberResponse;
import yoon.project.onlineShop.dto.response.ResponseMessage;
import yoon.project.onlineShop.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public ResponseEntity<ResponseMessage> userInfo() {

        MemberResponse result = memberService.getInfo();
        if(result == null){
            //throw Exception;
        }
        ResponseMessage message = new ResponseMessage();
        message.setCode(HttpStatus.OK);
        message.setMessage("사용자 정보");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ResponseMessage> register(@RequestBody MemberRegisterDto dto){

        MemberResponse result = memberService.register(dto);

        ResponseMessage message = new ResponseMessage();
        message.setCode(HttpStatus.OK);
        message.setMessage(result.getName() + "님 회원가입 성공");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    };

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody MemberLoginDto dto){

        MemberResponse result = memberService.login(dto);

        ResponseMessage message = new ResponseMessage();
        message.setCode(HttpStatus.OK);
        message.setMessage("로그인 성공");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    };

}
