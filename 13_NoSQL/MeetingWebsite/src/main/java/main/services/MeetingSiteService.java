package main.services;

import main.repositories.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MeetingSiteService {

    private RedisRepository repository;

    @Autowired
    public void setRepository(RedisRepository repository) {
        this.repository = repository;
    }

    public String getFirstInLineUser() {
        return getUserFromRepository(0);
    }

    public String getUserInLineByNumber(int userNumber) {
        return getUserFromRepository(userNumber);
    }

    private String getUserFromRepository(int userNumber)    {
        ArrayList<String> usersList = repository.getUsers();
        String firstInLineUser = usersList.get(userNumber);
        usersList.remove(userNumber);
        usersList.add(firstInLineUser);
        repository.setUsers(usersList);
        return firstInLineUser;
    }

}