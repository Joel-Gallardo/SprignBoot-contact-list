package com.contact.list.controller;

import com.contact.list.model.Contact;
import com.contact.list.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping({"/",""})
    public String showHomePage(Model model) {
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);
        return "index";
    }

    @GetMapping({"/new"})
    public String showRegisterContactForm(Model model){
        model.addAttribute("contact", new Contact());
        return "new";
    }

    @PostMapping({"/new"})
    public String saveContactForm(Contact contact, RedirectAttributes redirectAttributes){
        contactRepository.save(contact);
        redirectAttributes.addFlashAttribute("msgSuccess", "The contact was successfull created");
        return "redirect:/";
    }
}
