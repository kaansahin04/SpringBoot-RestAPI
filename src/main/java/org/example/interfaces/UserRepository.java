package org.example.interfaces;

import org.example.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>{
    @Query("select u from User u where u.cevrimici=true")   /*çevrimiçi olanları seçen sorgu*/
    List<User> findOnlineUsers();
}
