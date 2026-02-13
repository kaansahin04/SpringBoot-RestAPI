package org.example.controller;

import org.example.entity.User;
import org.example.interfaces.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepo;
    public UserController(UserRepository userRepo){
        this.userRepo=userRepo;
    }

    /*ÇEVRİMİÇİ OLANLARI GÖSTER*/
    @GetMapping("/actives")
    public ResponseEntity<List<User>> getActiveUsers(){
        return ResponseEntity
                .status(HttpStatus.OK)       /*status: REST API çağrısının vereceği cevabın kodu (OK=200)*/
                .contentType(MediaType.APPLICATION_JSON)    /*contentType: cevabın gösterim formatı (JSON olsun)*/
                .body(userRepo.findOnlineUsers());     /*body: cevabın kendisi (çevrimici=true şeklindeki kayıtları seçecek olan sorgu çalışsın, cevap bu listedir)*/
    }

    /*HERKESİ GÖSTER*/
    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return ResponseEntity
                .ok()       /*ok: cevabın kodu 200 (.status(HttpStatus.OK) ifadesinin kısaltılmış hâli)*/
                .contentType(MediaType.APPLICATION_XML)     /*(cevap XML formatında olsun)*/
                .body(userRepo.findAll());      /*(her kaydı seçecek olan sorgu çalışsın, cevap bu listedir)*/
    }

    /*BELİRLİ BİR KULLANICIYI GÖSTER*/
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable("id") Long id
    ){
        return userRepo
                .findById(id)       /*findById: belirtilen ID'ye sahip olan kullanıcıyı bul*/
                .map((u) -> ResponseEntity      /*map: bir kullanıcı bulunursa*/
                                    .ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(u))       /*(cevap, kullanıcıdır)*/
                .orElse(ResponseEntity      /*orElse: bir kullanıcı bulunamazsa*/
                        .notFound()     /*notFound: cevabın kodu 404*/
                        .build());      /*build: bir kullanıcı bulunamadı, body (cevap) yok, işlem tamamlanır*/
    }

    /*KULLANICI EKLE*/
    @PostMapping("/send")
    public ResponseEntity<User> createUser(
            @RequestBody User user      /*ekleme yapmak için parametre olarak JSON formatında kullanıcı alınır ve User türüne çevrilir*/
    ){
        user.setId(null);       /*id değeri AutoIncrement olduğundan değer girilmez, sistem otomatik olarak atama yapacak*/
        user.setTarih(LocalDateTime.now());     /*eklenme zamanını şu an olarak ayarla*/
        return ResponseEntity
                .status(HttpStatus.CREATED)     /*(CREATED=201)*/
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRepo.save(user));     /*(cevap, kullanıcının kaydedilmesidir. ekrana bir şey çıkarılmaz)*/
    }
    /*
     * POST işlemini test edebilmek için örnek cURL komutu:
     * curl -X POST http://localhost:8080/users/send ^
     * -H "Content-Type: application/json" ^
     * -d "{\"ad\":\"Ad Soyad\",\"email\":\"Email\",\"cevrimici\":true}"
     */


    /*BELİRLİ BİR KULLANICININ VERİ(LER)İNİ DEĞİŞTİR*/
    @PutMapping("/change/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") Long id,
            @RequestBody User user
    ){
        return userRepo
                .findById(id)
                .map((u) -> {       /*kullanıcı bulunursa*/
                    /*parametre olarak gelen kullanıcının bilgilerini (get) yeni bilgiler olarak kaydet (set)*/
                    u.setAd(user.getAd());
                    u.setEmail(user.getEmail());
                    u.setCevrimici(user.isCevrimici());
                    /*Lambda içinde süslü parantez ile blok içine girdik. return ile bu bloktan çıkmalıyız*/
                    return ResponseEntity
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(userRepo.save(u));    /*(cevap, yeni bilgiler sonrası oluşan hâlin kaydedilmesidir. ekrana bir şey çıkarılmaz)*/
                })
                .orElse(ResponseEntity
                        .notFound()
                        .build());
    }
    /*
     * PUT işlemini test edebilmek için örnek cURL komutu:
     * curl -X PUT http://localhost:8080/users/change/{id} ^
     * -H "Content-Type: application/json" ^
     * -d "{\"ad\":\"Ad Soyad\",\"email\":\"Email\",\"cevrimici\":false}"
     */

    /*BELİRLİ BİR KULLANICIYI SİL*/
    @DeleteMapping("/delete/{id}")
    public void deleteUser(
            @PathVariable("id") Long id
    ){
        userRepo
            .findById(id)
            .map((u) -> {
                userRepo.delete(u);     /*kullanıcıyı silecek olan sorgu çalışsın*/
                return ResponseEntity
                        .noContent()    /*noContent: cevabın kodu 204*/
                        .build();       /*kullanıcı bulunmuştu ama silindiği için body (cevap) yok, işlem tamamlanır*/
            })
            .orElse(ResponseEntity
                    .notFound()
                    .build());
    }
    /*
     * DELETE işlemini test edebilmek için cURL komutu:
     * curl -X DELETE http://localhost:8080/users/delete/{id}
     */
}
