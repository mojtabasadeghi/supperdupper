package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {
    private CredentialsMapper credentialsMapper;
    private EncryptionService encryptionService;

    public CredentialsService(CredentialsMapper credentialsMapper,EncryptionService encryptionService)
    {
        this.credentialsMapper=credentialsMapper;
        this.encryptionService=encryptionService;
    }

    public List<Credentials> getAllCredentials(String username)
    {
        List<Credentials> tempCredentials=credentialsMapper.getCredentials(username);
        tempCredentials.forEach(credentials -> credentials.setDecryptedPassword(encryptionService.decryptValue(credentials.getPassword(),"Adam")) );
        return tempCredentials;
    }
}
