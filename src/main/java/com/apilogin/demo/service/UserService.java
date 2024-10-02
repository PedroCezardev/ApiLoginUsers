package com.apilogin.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apilogin.demo.exception.InvalidUserException;
import com.apilogin.demo.model.User;
import com.apilogin.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // cadastrar usuarios
    public User registerUsers(User user) {
        validateUser(user);
        return userRepository.save(user);
    }

    // sistema de login para a api
    public User loginUser(String email, String password) throws InvalidUserException {
        User userSearch = userRepository.findByEmailAndPassword(email, password);
        if (userSearch != null) {
            return userSearch;
        }
        throw new InvalidUserException("Usuário ou senha inválidos");
    }

    // buscar todos usuarios
    public List<User> findAllUsers() {
        List<User> user = userRepository.findAll();
        if (user.isEmpty()) {
            throw new InvalidUserException("Nenhum usuario encontrado.");
        }
        return user;
    }

    public List<User> getByUsername(String username) {
        List<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new InvalidUserException("o usuario não foi encontrado com o Nome: " + username);
        }
        return user;
    }

    public List<User> getByEmail(String email) {
        List<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new InvalidUserException("O usuario não foi encontrado com o Email: " + email);
        }
        return user;
    }

    public List<User> getByInstituicao(String instituicaoParceira) {
        List<User> user = userRepository.findByInstituicaoParceira(instituicaoParceira);
        if(user.isEmpty()) {
            throw new InvalidUserException("O usuario não foi encontrado com a parceria nesta Instituição: " + instituicaoParceira);
        }
        return user;
    }

    public List<User> getByProposito(String propositoFinal) {
        List<User> user = userRepository.findByPropositoFinal(propositoFinal);
        if(user.isEmpty()) {
            throw new InvalidUserException("O usuario não foi encontrado com este proposito: " + propositoFinal);
        }
        return user;
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new InvalidUserException("O usuario com este Id não foi encontrado no sistema.");
        }
        userRepository.deleteById(id);
        System.out.println("O usuario foi deletado com sucesso!");
    }

    @Transactional
    public void deleteByName(String username) { 
        if (!userRepository.existsByUsername(username)) {
            throw new InvalidUserException("O usuario com este Nome não foi encontrado no sistema.");
        }
        userRepository.deleteByUsername(username);
        System.out.println("O usuario foi deletado com sucesso!");
    }

    // atualizar perfil de usuario
    public User updateUser(Long id, User userDetails) {

        if(userDetails == null) {
            throw new InvalidUserException("Os detalhes do usuario não podem ser nulos.");
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new InvalidUserException("Usuarii=o não encontrado com id: " + id);
        }

        validateUser(userDetails);
        User user = optionalUser.get();

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setInstituicaoParceira(userDetails.getInstituicaoParceira());
        user.setPropositoFinal(userDetails.getPropositoFinal());

        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    // validar informações
    public void validateUser(User user) {
        if ( user.getUsername().isEmpty() || user.getUsername().length() > 50) {
            throw new InvalidUserException("O Nome do usuario não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
        if ( user.getEmail().isEmpty() || user.getEmail().length() > 50) {
            throw new InvalidUserException("O Email do usuario não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
        if ( user.getInstituicaoParceira().isEmpty() || user.getInstituicaoParceira().length() > 70) {
            throw new InvalidUserException("A Instituição Parceira do usuario não pode ser nulo, vazio ou ter mais de 70 caracteres");
        }
        if ( user.getPropositoFinal().isEmpty() || user.getPropositoFinal().length() > 100) {
            throw new InvalidUserException("O Proposito do usuario não pode ser nulo, vazio ou ter mais de 100 caracteres");
        }
    }
    
}
