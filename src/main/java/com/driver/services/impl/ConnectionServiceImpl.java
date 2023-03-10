package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ConnectionRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    UserRepository userRepository2;
    @Autowired
    ServiceProviderRepository serviceProviderRepository2;
    @Autowired
    ConnectionRepository connectionRepository2;

    @Override
    public User connect(int userId, String countryName) throws Exception{
        User user=userRepository2.findById(userId).get();
        if(!user.getConnected())
        {
            throw new Exception("Already connected");
        }
        if(user.getCountry().getCountryName().toString()==countryName)
        {
            return user;
        }



        return user;
    }
    @Override
    public User disconnect ( int userId) throws Exception {
        User user=userRepository2.findById(userId).get();
        return user;

    }
    @Override
    public User communicate ( int senderId, int receiverId) throws Exception {
        User user=new User();
        return user;

    }
}
