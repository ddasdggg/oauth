package com.choi.kakao_auth.controller;


import com.choi.kakao_auth.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@Log4j2
@RequiredArgsConstructor
public class AuthController {

    private final KakaoService kakaoService;

    @RequestMapping(value = "/login/auth")
    public String home(@RequestParam(value = "code", required = false) String code) throws Exception{
        String access_Token = kakaoService.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###userInfo#### : " + userInfo.get("email"));
        System.out.println("###nickname#### : " + userInfo.get("nickname"));
        return "testPage";
    }
}
