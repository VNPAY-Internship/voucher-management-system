package com.vnpay.vouchersystem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.vnpay.vouchersystem.model.User;
import com.vnpay.vouchersystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> saveAll(List<User> users) {

        return userRepository.saveAll(users);
    }

    @Override
    public void parseAndSave(MultipartFile file) {
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        try {
            if (".csv".equals(fileType)) {
                // For simplicity, assuming CSV has same structure as User entity
                CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(new InputStreamReader(file.getInputStream()))
                        .withType(User.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<User> users = csvToBean.parse();
                saveAll(users);
            } else if (".json".equals(fileType)) {
                ObjectMapper objectMapper = new ObjectMapper();
                List<User> users = Arrays.asList(objectMapper.readValue(file.getInputStream(), User[].class));
                saveAll(users);
            } else {
                throw new RuntimeException("Unsupported file type");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse file", e);
        }
    }
}

