package com.example.TastyTrack.TastyTrackbackend.Service.Impl;

import com.example.TastyTrack.TastyTrackbackend.Entity.Admin;
import com.example.TastyTrack.TastyTrackbackend.Model.AdminModel;
import com.example.TastyTrack.TastyTrackbackend.Repository.AdminRepo;
import com.example.TastyTrack.TastyTrackbackend.Service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Admin save(AdminModel adminModel) {
        Admin admin= new Admin(adminModel.getAdmin_id(),adminModel.getEmail(),adminModel.getFirstName(), adminModel.getLastName(),passwordEncoder.encode(adminModel.getPassword()), adminModel.getPhone(), adminModel.getAddress(), adminModel.getImg_url(), adminModel.getGender());
        return adminRepo.save(admin);
    }

    @Override
    public Admin findEmail(String email) {
        return adminRepo.findByEmail(email);
    }

    @Override
    public AdminModel getUserById(Long id) {
        AdminModel adminModel=new AdminModel();
        Admin admin=new Admin();
        admin= adminRepo.getReferenceById(id);

        BeanUtils.copyProperties(admin,adminModel);

        return adminModel;
    }
}
