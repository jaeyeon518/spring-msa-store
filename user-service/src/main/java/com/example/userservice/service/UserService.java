package com.example.userservice.service;

import com.example.userservice.dto.UserRegisterRequestDto;
import com.example.userservice.dto.UserUpdateRequestDto;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(UserRegisterRequestDto requestDto) {

        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("중복된 이메일입니다.");
        }

        User user = User.builder()
            .name(requestDto.getName())
            .password(passwordEncoder.encode(requestDto.getPassword())) // 비밀번호 인코딩
            .email(requestDto.getEmail())
            .phone(requestDto.getPhone())
            .role(requestDto.getRole()) // DTO에서 받은 역할 설정
            .build();

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUser(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
    }

    @Transactional
    public User updateUser(Long id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        user.updateProfile(requestDto.getUsername());
        return user;
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.valueOf(username))
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserDetailsImpl(user);
    }
}