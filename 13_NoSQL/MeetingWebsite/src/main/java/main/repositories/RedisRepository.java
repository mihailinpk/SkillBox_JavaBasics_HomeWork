package main.repositories;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;

@Component
public class RedisRepository {

    private final String HOST_ADDRESS = "127.0.0.1";
    private final int PORT_NUMBER = 6379;
    private final String REDIS_KEY = "meeting_site";

    private Jedis jedis;

    public RedisRepository() {
        jedis = new Jedis(HOST_ADDRESS, PORT_NUMBER);
    }

    public ArrayList<String> getUsers() {
        ScanResult<Tuple> scanResult = jedis.zscan(REDIS_KEY, String.valueOf(0));
        ArrayList<String> resultList = new ArrayList<>();
        for(Tuple tuple: scanResult.getResult())    {
            resultList.add(tuple.getElement());
        }
        return resultList;
    }

    public void setUsers(ArrayList<String> usersList) {
        Integer i = 0;
        for (String element : usersList)  {
            jedis.zadd(REDIS_KEY, i++, element);
        }
    }

}