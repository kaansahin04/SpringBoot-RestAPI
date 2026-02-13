package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;
    private String email;
    private boolean cevrimici;
    private LocalDateTime tarih;

    /*YAPICILAR*/
    public User(){}

    public User(Long id, String ad, String email, boolean cevrimici, LocalDateTime tarih){
        this.id=id;
        this.ad=ad;
        this.email=email;
        this.cevrimici=cevrimici;
        this.tarih=tarih;
    }

    /*GETTER-SETTER*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAd() {
        return ad;
    }
    public void setAd(String ad) {
        this.ad = ad;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isCevrimici() {
        return cevrimici;
    }
    public void setCevrimici(boolean cevrimici) {
        this.cevrimici = cevrimici;
    }
    public LocalDateTime getTarih() {
        return tarih;
    }
    public void setTarih(LocalDateTime tarih) {
        this.tarih = tarih;
    }
}
