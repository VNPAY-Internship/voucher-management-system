package com.vnpay.vouchersystem.service.user;

import com.vnpay.vouchersystem.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> saveAll(List<User> users);

    void parseAndSave(MultipartFile file);
}
