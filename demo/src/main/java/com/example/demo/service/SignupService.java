package com.example.demo.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面 Service
 * 
 * @author tukinari
 * 
 */
@Service
@RequiredArgsConstructor
public class SignupService {

    /** ユーザー情報テーブル Repository(DAO) */
    private final UserInfoRepository repository;

    /** Dozer Mapper */
    private final Mapper mapper;

    /** PasswordEncoder */
    private final PasswordEncoder passwordEncoder;

    /**
     * ユーザー情報テーブル 新規登録
     *
     * @param form 入力情報
     * @return 登録情報(ユーザー情報Entity)、すでに同じユーザーIDで登録がある場合はEmpty
     */
    public Optional<UserInfo> resistUserInfo(SignupForm form) { // 引数は入力データ(フォーム)
        var userInfoExisted = repository.findById(form.getLoginId());
        if (userInfoExisted.isPresent()) { // ID重複データがあったら処理を抜ける（Enpty）
            return Optional.empty();
        } else { // ID重複データが無かったら、パスワードをハッシュ化し、
            var userInfo = mapper.map(form, UserInfo.class);
            var encodedPassword = passwordEncoder.encode(form.getPassword());
            userInfo.setPassword(encodedPassword);
            return Optional.of(repository.save(userInfo));
        }
    }
}
