package com.contact.list.controller;

import com.contact.list.model.Contact;
import com.contact.list.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String saveContactForm(@Validated Contact contact, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("contact", contact);
            return "new";
        }

        contactRepository.save(contact);
        redirectAttributes.addFlashAttribute("msgSuccess", "The contact was successfull created");
        return "redirect:/";
    }

    @GetMapping("/{id}/edit/")
    public String showUpdateContactForm(@PathVariable Long id, Model model){
        Contact contact = contactRepository.getById(id);
        model.addAttribute("contact", contact);
        return "new";
    }

    @PostMapping("/{id}/edit/")
    public String UpdateContact(@PathVariable Long id, @Validated Contact contact, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        Contact contactDB = contactRepository.getById(id);

        if(bindingResult.hasErrors()){
            model.addAttribute("contact", contact);
            return "new";
        }

        contactDB.setName(contact.getName());
        contactDB.setCellPhone(contact.getCellPhone());
        contactDB.setEmail(contact.getEmail());
        contactDB.setBirthDate(contact.getBirthDate());

        contactRepository.save(contactDB);
        redirectAttributes.addFlashAttribute("msgSuccess", "The contact was successfull updated");
        return "redirect:/";
    }

    @PostMapping("/{id}/delete/")
    public String deleteContact(@PathVariable Long id, RedirectAttributes redirectAttributes){
        Contact contact = contactRepository.getById(id);
        contactRepository.delete(contact);
        redirectAttributes.addFlashAttribute("msgSuccess", "The contact was successfull deleted");
        return "redirect:/";
    }

}
