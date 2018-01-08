package com.example.wcmovie;

import com.example.wcmovie.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WcmovieApplicationTests {
    @Autowired
    private MovieService movieService;

    @Test
    public void contextLoads() {
        /*String url = movieService.showing();
        System.out.println(url);*/
    }

}
