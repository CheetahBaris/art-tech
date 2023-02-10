package org.example.controller;

import org.example.dto.MailFromUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
 public class MainPageController {

    private final JavaMailSender javaMailSender;
    @Autowired
    public MainPageController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @GetMapping("/")
    public String getMainPage(Model model){

        model.addAttribute("mailForm", new MailFromUserDto());
//        model.addAttribute("status", null);
          return "index";
    }
    @PostMapping("/getMail")
    public String getMail(MailFromUserDto form , RedirectAttributes redirectAttributes){
         SimpleMailMessage message = new SimpleMailMessage();
        try {

            if(form.getEmail()!=null){
                message.setFrom("live4read@mail.ru");
//            message.setTo("info@arttech.store");
//                message.setTo("nekit155rybin@gmail.com");
                message.setTo("sashabik@mail.ru");

                message.setSubject( form.getName()+ " - "+ form.getEmail());
                message.setText("Привет!\n Мой номер - "+ form.getPhone() +"  \n Мое сообщение - "+form.getMessage()+"\n\n\n С Уважением,  "+form.getName()+".");
                javaMailSender.send(message);
            }
            redirectAttributes.addFlashAttribute("status", true);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("status", false);

         }

         return "redirect:/";
    }

}
