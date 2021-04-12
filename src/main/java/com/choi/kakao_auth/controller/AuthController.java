package com.choi.kakao_auth.controller;


import com.choi.kakao_auth.service.GoogleService;
import com.choi.kakao_auth.service.KakaoService;
import com.choi.kakao_auth.service.NaverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@Log4j2
@RequiredArgsConstructor
public class AuthController {

    private final KakaoService kakaoService;

    private final GoogleService googleService;

    private final NaverService naverService;

    @RequestMapping(value = "/auth/kakao")
    public String kakao(@RequestParam(value = "code", required = false) String code) throws Exception{
        String access_Token = kakaoService.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###userInfo#### : " + userInfo.get("email"));
        System.out.println("###nickname#### : " + userInfo.get("nickname"));
        return "kakao";
    }

    @RequestMapping(value = "/auth/google")
    public String google(@RequestParam(value = "code", required = false) String code) throws Exception{
        String access_Token = googleService.getAccessToken(code);
        HashMap<String, Object> userInfo = googleService.getUserInfo(access_Token);
        return "google";
    }

    @RequestMapping(value = "/login/auth/naver")
    public String naver(){
        return "naver";
    }


    @RequestMapping(value = "/auth/naver")
    public String naver_post(@RequestParam(value = "code", required = false) String code,
                        @RequestParam(value = "state", required = false) String state) throws Exception{
        String access_Token = naverService.getAccessToken(code,state);
        naverService.getUserInfo(access_Token);
        return "/auth_naver";
    }
}
