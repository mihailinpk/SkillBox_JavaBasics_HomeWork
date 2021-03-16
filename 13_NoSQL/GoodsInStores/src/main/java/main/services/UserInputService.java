package main.services;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserInputService {

    private Scanner keyboard = new Scanner(System.in);

    public String getLine() {
        return keyboard.nextLine();
    }

}