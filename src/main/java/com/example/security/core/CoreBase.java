package com.example.security.core;

import com.example.security.source.repo.ProductDetailRepo;
import com.example.security.source.repo.RoleRepo;
import com.example.security.source.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CoreBase {

    public static ProductDetailRepo productDetailRepo;
    public static UserRepo userRepo;
    public static RoleRepo roleRepo;
    public static PasswordEncoder passwordEncoder;

    @Autowired
    public CoreBase(ProductDetailRepo productDetailRepo, UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        CoreBase.productDetailRepo = productDetailRepo;
        CoreBase.userRepo = userRepo;
        CoreBase.roleRepo = roleRepo;
        CoreBase.passwordEncoder = passwordEncoder;
    }
}
