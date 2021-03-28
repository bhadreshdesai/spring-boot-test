package bdd.demo.springboottest.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/home")
public class HomeController {
    private static final String template = "Hello, %s!";
    private static final String templateWithParam = "Hello(param), %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/greeting-with-param")
    public Greeting greetingWithParam(@RequestParam(value = "name") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(templateWithParam, name));
    }
}
