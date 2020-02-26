package com.flashCards.domain.auth;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "oauth_refresh_token")
public class OauthRefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "bigint unsigned")
    private Integer id;

    @Column(name = "token_id")
    private String tokenId;

    @Lob
    @Column(name = "token", columnDefinition = "mediumblob")
    private byte[] token;

    @Lob
    @Column(name = "authentication", columnDefinition = "mediumblob")
    private byte[] authentication;
}
