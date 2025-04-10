package com.eemarisademello.eletiva_geotec.repository;

import com.eemarisademello.eletiva_geotec.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserEmail(String userName);

    User findByUserId(Long userId);

    User findByUserPhoneNumber(String phoneNumber);

    List<User> queryByUserNameLike(String name);
}
