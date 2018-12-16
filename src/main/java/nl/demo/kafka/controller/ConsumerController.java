package nl.demo.kafka.controller;

import nl.demo.kafka.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsumerController {
    @Autowired
    ConsumerService consumerService;

    @GetMapping("/consume")
    public String consumer() {
        //For now, this line will make sure the stream is consumed and the data will be logged.
        consumerService.consumeStream();

        return "consume";
    }
}
