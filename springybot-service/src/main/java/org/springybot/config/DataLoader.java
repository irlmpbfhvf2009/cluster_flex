package org.springybot.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springybot.entity.Admin;
import org.springybot.service.impl.AdminServiceImpl;
import org.springybot.utils.RedisUtils;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AdminServiceImpl adminServiceImpl;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void run(String... args) throws Exception {

        try {
            redisUtils.clearAllData();
            

            Admin admin = adminServiceImpl.findByUsername("admin");
            if (admin == null) {
                admin = new Admin();
                admin.setUsername("admin");
                admin.setPassword("123456");
                admin.setEnabled(true);
                admin.setRoles(Arrays.asList(new String[] { "ADMIN" }));
                adminServiceImpl.saveAdmin(admin);
            }

        } catch (Exception e) {
            throw e;
        }

    }

}