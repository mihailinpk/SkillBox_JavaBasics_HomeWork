package main;

import main.services.MeetingSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class Main implements ApplicationRunner {

    private MeetingSiteService service;

    @Autowired
    public void setService(MeetingSiteService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Random random = new Random();
        String userOnMainPage;
        int i = 1;
        while (true)    {
            if (i > 20) {
                i = 1;
            }
            if (i % 10 == 0)    {
                userOnMainPage = service.getUserInLineByNumber(random.nextInt(19));
            } else {
                userOnMainPage = service.getFirstInLineUser();
            }
            System.out.println("Main page of meeting site: " + i++ + "."+ userOnMainPage);
            Thread.sleep(1000);
        }
    }

}