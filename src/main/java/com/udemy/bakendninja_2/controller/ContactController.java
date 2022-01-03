package com.udemy.bakendninja_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.udemy.bakendninja_2.constant.ViewConstant;
import com.udemy.bakendninja_2.model.ContactModel;
import com.udemy.bakendninja_2.service.ContactService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/contacts")
@Log
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/contactform")
	public String redirectContactForm(@RequestParam(name="id", required = true) int id, Model model) {
		ContactModel contactModel = id==0?new ContactModel():contactService.findContactById(id);
		model.addAttribute("contactModel", contactModel);
		return ViewConstant.CONTACT_FORM;
	}
	
	@GetMapping("/cancel")
	public String cancel() {
		return ViewConstant.REDIRECT_CONTACTS;
	}
	
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel, Model model) {
		log.info("METHOD: addContact() -- PARAMS: "+contactModel);
		if(contactService.addContact(contactModel) != null) {
			model.addAttribute("result", 1);
		}else {
			model.addAttribute("result", 0);
		}
		return ViewConstant.REDIRECT_CONTACTS;
	}
	
	@GetMapping("/showcontacts")
	public ModelAndView showContacts() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		mav.addObject("contacts", contactService.listAllContacts());
		return mav;
	}
	
	@GetMapping("/removecontact")    //Debería usarse Post pero se usa Get para no usar Ajax ni javascript en la vista
	public String removeContact(@RequestParam(name="id", required = true) int id) {
		contactService.removedContact(id);
		return ViewConstant.REDIRECT_CONTACTS;
	}
}
