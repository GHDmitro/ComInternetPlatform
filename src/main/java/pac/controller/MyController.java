package pac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pac.entities.Account;
import pac.entities.AccountType;
import pac.errors.TypeIsNotFound;
import pac.services.AccountService;
import pac.services.AccountTypeService;

@Controller
@RequestMapping("/")
public class MyController {
    static final int DEFAULT_GROUP_ID = -1;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountTypeService accountTypeService;
//    private ContactService contactService;

    @RequestMapping("/")
    public String rootPage(Model model) {
//        model.addAttribute("accounts", accountService.listAccount());
        return "canvas";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/login/{error}")
    public ModelAndView loginError(@PathVariable("error") String errorField) {

        return new ModelAndView("login", "errorField", new TypeIsNotFound());

    }

    @RequestMapping(value = "/registrat")
    public String registratPage() {
        return "registrat";
    }

    @RequestMapping(value = "/logout")
    public String logoutPage(Model model) {
//        model.addAttribute("error", )
        return "login";
    }



//    @RequestParam("/login?error")
//    pages.publ ModelAndView errorLogin( Model model){
//
//        throw new TypeIsNotFound();
//    }

//    @RequestMapping("/contact_add_page")
//    pages.publ String contactAddPage(Model model) {
//        model.addAttribute("groups", contactService.listGroups());
//        return "contact_add_page";
//    }
//
//    @RequestMapping("/group_add_page")
//    pages.publ String groupAddPage() {
//        return "group_add_page";
//    }

//    @RequestMapping("/group/{id}")
//    pages.publ String listGroup(@PathVariable(value = "id") long groupId, Model model) {
//        Group group = (groupId != DEFAULT_GROUP_ID) ? contactService.findGroup(groupId) : null;
//
//        model.addAttribute("groups", contactService.listGroups());
//        model.addAttribute("currentGroup", group);
//        model.addAttribute("contacts", contactService.listContacts(group));
//        return "index";
//    }
//
//
//
//    @RequestMapping(value = "/search", method = RequestMethod.POST)
//    pages.publ String search(@RequestParam String pattern, Model model) {
//        model.addAttribute("groups", contactService.listGroups());
//        model.addAttribute("contacts", contactService.searchContacts(pattern));
//        return "index";
//    }
//
//
//    @RequestMapping(value = "/contact/delete", method = RequestMethod.POST)
//    pages.publ String search(@RequestParam(value = "toDelete[]", required = false) long[] toDelete, Model model) {
//        if (toDelete != null)
//            contactService.deleteContact(toDelete);
//
//        model.addAttribute("groups", contactService.listGroups());
//        model.addAttribute("contacts", contactService.listContacts(null));
//        return "index";
//    }
//
//    @RequestMapping(value="/contact/add", method = RequestMethod.POST)
//    pages.publ String contactAdd(@RequestParam(value = "group") long groupId,
//                             @RequestParam String name,
//                             @RequestParam String surname,
//                             @RequestParam String phone,
//                             @RequestParam String email,
//                             Model model)
//    {
//        Group group = (groupId != DEFAULT_GROUP_ID) ? contactService.findGroup(groupId) : null;
//
//        Contact contact = new Contact(group, name, surname, phone, email);
//        contactService.addContact(contact);
//
//        model.addAttribute("groups", contactService.listGroups());
//        model.addAttribute("contacts", contactService.listContacts(null));
//        return "index";
//    }

    @RequestMapping(value = "/addPricePosition", method = RequestMethod.POST)
    public String addPricePosition(@RequestParam String login, @RequestParam String name, @RequestParam String codeOfModel,
                                   @RequestParam String description, @RequestParam MultipartFile photo) {
        System.out.println(login);
        return "customer";
    }

    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    public String addAccount(@RequestParam String login, @RequestParam String pass, @RequestParam String email,
                             @RequestParam String telNumber, @RequestParam String type, Model model) {

        AccountType at = accountTypeService.findByTypeName(type);
        accountService.addAccount(new Account(telNumber, email, pass, login, at));
        model.addAttribute("accounts", accountService.listAccount());
        model.addAttribute("login", login);
        model.addAttribute("type", type);

        if (type.equalsIgnoreCase("customer")) {
            return "customer";
        } else if (type.equalsIgnoreCase("client")) {
            return "client";
        } else throw new TypeIsNotFound();

    }


}
