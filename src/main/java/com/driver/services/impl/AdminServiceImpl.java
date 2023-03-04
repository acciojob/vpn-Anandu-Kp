package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
        Admin admin=new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        adminRepository1.save(admin);
        return admin;
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        Admin admin=adminRepository1.findById(adminId).get();
        List<ServiceProvider> serviceProviderList=serviceProviderRepository1.findAll();
        ServiceProvider provider=new ServiceProvider();

        for(ServiceProvider serviceProvider:serviceProviderList)
        {
            if(serviceProvider.getName()==providerName)
            {
                provider=serviceProvider;
                break;
            }
        }

        admin.getServiceProviders().add(provider);
        provider.setAdmin(admin);
        adminRepository1.save(admin);
        return admin;
    }

    @Override
    public ServiceProvider addCountry ( int serviceProviderId, String countryName) throws Exception{
        ServiceProvider serviceProvider=serviceProviderRepository1.findById(serviceProviderId).get();
        List<Country> countries=countryRepository1.findAll();
        Country country=new Country();
        boolean gotCountry=false;
        for(Country country1:countries)
        {
            if(country1.getCountryName().toString()==countryName)
            {
                country=country1;
                gotCountry=true;

                break;
            }
        }
        if(gotCountry!=true)
        {
            throw   new Exception("Country not found");
        }
        country.setServiceProvider(serviceProvider);
        serviceProvider.getCountryList().add(country);
        countryRepository1.save(country);
        serviceProviderRepository1.save(serviceProvider);

        return serviceProvider;
    }
}
