package com.apilogin.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apilogin.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    public User findByEmailAndPassword(String email, String password);

    public List<User> findByUsername(String username);

    public List<User> findByEmail(String email);

    public List<User> findByInstituicaoParceira(String instituicaoParceira);

    public List<User> findByPropositoFinal(String propositoFinal);

    public boolean existsByUsername(String username);

    public void deleteByUsername(String username);
}
