package com.example.amqpdemo.controller;

import com.example.amqpdemo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author HANGKANG
 * @date 2018/5/21 下午5:00
 */

@RestController
public class EmailController {
    @Autowired
    private EmailService service;

    @RequestMapping(value = "/sendSimple/{email}")
    public void sendSimpleEmail(@PathVariable(value = "email") String to) {
        service.sendSimpleEmail(to);
    }

    @RequestMapping(value = "/sendPic/{email}")
    public void sendPicEmail(@PathVariable(value = "email") String to) {
        service.sendPicEmail(to);
    }

    @RequestMapping(value = "/sendHtml/{email}")
    public void sendHtmlEmail(@PathVariable(value = "email") String to) {
        service.sendHtmlEmail(to);
    }

}
