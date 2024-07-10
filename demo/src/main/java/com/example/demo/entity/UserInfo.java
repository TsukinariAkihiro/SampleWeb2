package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *ユーザー情報テーブル　Entity
 *
 * @author tukinari
 *
 */
@Entity
@Table(name ="user_info")
@Data
public class UserInfo {

    /** ログインID */
    @Id
    @Column(name="login_id") //テーブルカラム名を記載してカラムアノテーションで紐づける。※passwordはテーブルカラムと一致しているため不要。
    private String loginId;

    /** パスワード */
    private String password;
}
