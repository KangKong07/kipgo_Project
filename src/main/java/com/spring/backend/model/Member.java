package com.spring.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String MEMBER_ID;   // 회원ID

    @Column(name = "MEMBER_PWD")
    private String MEMBER_PWD;  // 비밀번호

    @Column(name = "NAME")
    private String NAME;        // 이름

    @Column(name = "TEL_NO")
    private String TEL_NO;      // 연락처

    @Column(name = "EMAIL")
    private String EMAIL;       // 이메일주소

    @Column(name = "DELETE_YN")
    private String DELETE_YN;   // 삭제여부

    @Column(name = "JOIN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date JOIN_DATE;     // 가입일자

    @Column(name = "CHK_ID")
    private String CHK_ID;      // 최종작업자

    @Temporal(TemporalType.TIMESTAMP)
    private Date CHK_DATE;      // 최종작업시간

    public String getMEMBER_ID() {
        return MEMBER_ID;
    }

    public void setMEMBER_ID(String MEMBER_ID) {
        this.MEMBER_ID = MEMBER_ID;
    }

    public String getMEMBER_PWD() {
        return MEMBER_PWD;
    }

    public void setMEMBER_PWD(String MEMBER_PWD) {
        this.MEMBER_PWD = MEMBER_PWD;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getTEL_NO() {
        return TEL_NO;
    }

    public void setTEL_NO(String TEL_NO) {
        this.TEL_NO = TEL_NO;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getDELETE_YN() {
        return DELETE_YN;
    }

    public void setDELETE_YN(String DELETE_YN) {
        this.DELETE_YN = DELETE_YN;
    }

    public Date getJOIN_DATE() {
        return JOIN_DATE;
    }

    public void setJOIN_DATE(Date JOIN_DATE) {
        this.JOIN_DATE = JOIN_DATE;
    }

    public String getCHK_ID() {
        return CHK_ID;
    }

    public void setCHK_ID(String CHK_ID) {
        this.CHK_ID = CHK_ID;
    }

    public Date getCHK_DATE() {
        return CHK_DATE;
    }

    public void setCHK_DATE(Date CHK_DATE) {
        this.CHK_DATE = CHK_DATE;
    }

    @Override
    public String toString() {
        return "Member{" +
                "MEMBER_ID='" + MEMBER_ID + '\'' +
                ", MEMBER_PWD='" + MEMBER_PWD + '\'' +
                ", NAME='" + NAME + '\'' +
                ", TEL_NO='" + TEL_NO + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", DELETE_YN='" + DELETE_YN + '\'' +
                ", JOIN_DATE=" + JOIN_DATE +
                ", CHK_ID='" + CHK_ID + '\'' +
                ", CHK_DATE=" + CHK_DATE +
                '}';
    }
}
