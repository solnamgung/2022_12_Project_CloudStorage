package com.solProject.cloudStorageProject.service;

import com.solProject.cloudStorageProject.mapper.CredentialMapper;
import com.solProject.cloudStorageProject.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }
    public Integer create(Credential credential) {
        return credentialMapper.create(credential);
    }
    public List<Credential> getCredentials(Integer userId) {
        return credentialMapper.getCredentials(userId);
    }
    public Credential getCredential(Integer credentialId) {
        return credentialMapper.getCredential(credentialId);
    }
    public Integer update(Credential credential) {
        return credentialMapper.update(credential);
    }
    public void delete(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }
}
