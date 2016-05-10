package pac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pac.entities.Account;
import pac.entities.AccountType;
import pac.entities.PositionOfPrice;
import pac.entities.Product;
import pac.errors.PhotoNotFoundException;
import pac.services.AccountService;
import pac.services.AccountTypeService;
import pac.services.PositionOfPriceService;
import pac.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MyController {
//    static final int DEFAULT_GROUP_ID = -1;

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountTypeService accountTypeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PositionOfPriceService positionOfPriceService;

//    private ContactService contactService;

    @RequestMapping("/")
    public String rootPage(Model model, HttpServletRequest request) {
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        System.out.println("in ///// "+userName);
//
//        Account account = accountService.findAccount(userName);
//        if (account != null) {
//            List<PositionOfPrice> listPositions = account.getPricePositions();
//            if (listPositions != null) {
//                System.out.println(listPositions.get(0).getProduct().getName());
//
//                model.addAttribute("listPosition", listPositions);
//            }
//            return "canvas";
////            return new ModelAndView()
//        }else {
//        model.addAttribute("accounts", accountService.listAccount());
        return "login";
//        }
    }

    @RequestMapping(value = "/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
//            if (login == null) {
//
//            }
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            String login = userDetail.getUsername();
            Account account = accountService.findAccount(login);

            if (account.getAccountType().getTypeName().equals("customer")) {
                List<PositionOfPrice> list = positionOfPriceService.listPositions(account);
                if (list.size() == 0) {
                    List<PositionOfPrice> list1 = new LinkedList<>();
                    Calendar c = Calendar.getInstance();
                    list1.add(new PositionOfPrice("Здесь будет ваши условия заказа", "Здесь будут ваши условия доставки",
                            new Date(c.YEAR, c.MONTH, c.DAY_OF_MONTH), 000000, account, new Product("Название товара",
                            "Описание товара", "defaultPhotoToScreen", "Код модели", 000000)));
                    list = list1;
                }
                model.addAttribute("listPositions", list);
//                model.addAttribute("login", login);
                return "canvas";
            } else if (account.getAccountType().getTypeName().equals("client")) {
                AccountType accountType = accountTypeService.findByTypeName("customer");
                List<Account> accountList = accountService.listAccount(accountType);
                model.addAttribute("accountList", accountList);
                return "home";
            } else return "login";
        } else return "login";

    }


    @RequestMapping(value = "/home/{login}")
    public String homePage(@PathVariable(value = "login") String login, Model model) {
        Authentication userName = SecurityContextHolder.getContext().getAuthentication();
        if (!(userName instanceof AnonymousAuthenticationToken)) {
            Account account = accountService.findAccount(login);
            if (account != null && account.getAccountType().getTypeName().equals("customer")) {
                List<PositionOfPrice> list = positionOfPriceService.listPositions(account);
                if (list.size() == 0) {
                    List<PositionOfPrice> list1 = new LinkedList<>();
                    Calendar c = Calendar.getInstance();
                    list1.add(new PositionOfPrice("Здесь будет ваши условия заказа", "Здесь будут ваши условия доставки",
                            new Date(c.YEAR, c.MONTH, c.DAY_OF_MONTH), 000000, account, new Product("Название товара",
                            "Описание товара", "defaultPhotoToScreen", "Код модели", 000000)));
                    list = list1;
                }
//                System.out.println(list.get(0).getProduct().getName() + "---------------------------");/\
                model.addAttribute("listPositions", list);
                model.addAttribute("login", login);
                return "canvas";
            } else return "login";
        } else return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");
        return model;
    }

    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Неверный логин или пароль";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Неверный логин или пароль";
        }
        return error;
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
    @RequestMapping(value = "/addNewPosition", method = RequestMethod.GET)
    public String getNewPosition(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "customer";
        } else return "login";


    }


    @RequestMapping(value = "/addPricePosition", method = RequestMethod.POST)
    public String addPricePosition(@RequestParam String name, @RequestParam String codeOfModel,
                                   @RequestParam String description, @RequestParam MultipartFile photo,
                                   @RequestParam int capacity, @RequestParam String bookingCondition,
                                   @RequestParam String deliveryCondition, @RequestParam double cost, Model model) {
//        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!(auth instanceof AnonymousAuthenticationToken)) {
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String login = userDetail.getUsername();
        Account account = accountService.findAccount(login);

        System.out.println("addPricePosition: "+account.getEmail()+"   "+account.getTelNumber());
        String ref = codeOfModel + login;

        Product product = productService.findProduct(name, codeOfModel, ref);    //////////////////////////  вот тут
//            System.out.println("next step");
        if (product != null) {
            model.addAttribute("error", "Название: " + name + ", модель: " + codeOfModel + " -- тот продукт уже существует," +
                    " попробуйте добавить что-то другое");
            return "customer";
        } else {
            if (!photo.isEmpty()) {
                File file = new File("/Users/macbookair/IdeaProjects/ComInternetPlatform/src/main/resources/" + ref + ".png");
                try (FileOutputStream fileOut = new FileOutputStream(file)) {
                    fileOut.write(photo.getBytes());
                    fileOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                product = new Product(name, description, ref, codeOfModel, capacity);
            } else product = new Product(name, description, "defaultPhotoToScreen", codeOfModel, capacity);

//            productService.updateProduct(product);
        }


        Calendar c = Calendar.getInstance();

        PositionOfPrice positionOfPrice = new PositionOfPrice(bookingCondition, deliveryCondition,
                new Date(c.YEAR, c.MONTH, c.DAY_OF_MONTH), cost, account, product);
        positionOfPriceService.addPositionOfPrice(positionOfPrice);
//            account.addPricePositions(positionOfPrice);   /////// проверить будет ли оно правильно работать добавле поз после ее создания
        System.out.println("-----------------");

//        accountService.updateAccount(account);
//
//        List<PositionOfPrice> listPosition = accountService.listPositions(account);  //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        List<PositionOfPrice> listPosition = positionOfPriceService.listPositions(account);
        if (listPosition.size() != 0) {
            model.addAttribute("listPositions", listPosition);
//            System.out.println(listPosition.get(0).getProduct().getName() + "     если есть то в контроллере позиция ");
        } else {
            System.out.println("Лист позиций пуст");
            model.addAttribute("error", "Проблеммы у сервера в addPricePosition");
            return "customer";
        }


//        model.addAttribute()  !!!!!!!!!!!!!!!!!!!!!!!!


//        System.out.println(login);
        return "canvas";
//        } else return "login";
    }


    @RequestMapping(value = "/ownData/{login}")
    public String ownData(@PathVariable(value = "login") String login, Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (login != null) {
            Account account1 = accountService.findAccount(userName);
            Account account = accountService.findAccount(login);
            if (account != null && account.getAccountType().getTypeName().equals("customer") && account1.getAccountType().getTypeName().equals("client")) {
                String fileName = "/Users/macbookair/IdeaProjects/ComInternetPlatform/src/main/resources/" + login + ".png";
                if ((new File(fileName)).exists()) {
                    // существует
                    model.addAttribute("refPhoto", login);
                    model.addAttribute("login", login);
                } else {
                    // не существует
                    model.addAttribute("refPhoto", "defaultPhotoToScreen");
                    model.addAttribute("login", login);
                }

                model.addAttribute("email", account.getEmail());
                model.addAttribute("telNumber", account.getTelNumber());

                return "ownData";
            } else return "login";
        }
        return "login";
    }

    @RequestMapping(value = "/ownData")
    public String ownData(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String login = auth.getName();
            Account account = accountService.findAccount(login);
            if (account.getAccountType().getTypeName().equals("customer")) {
                String fileName = "/Users/macbookair/IdeaProjects/ComInternetPlatform/src/main/resources/" + login + ".png";
                if ((new File(fileName)).exists()) {
                    // существует
                    model.addAttribute("refPhoto", login);
                    System.out.println("model.addAttribute(\"refPhoto\", login);");
                } else {
                    // не существует
                    model.addAttribute("refPhoto", "defaultPhotoToScreen");
                    System.out.println("model.addAttribute(\"refPhoto\", \"defaultPhotoToScreen\");");
                }

                System.out.println("ownData: "+account.getEmail() +"    "+account.getTelNumber());

                model.addAttribute("email", account.getEmail());
                model.addAttribute("telNumber", account.getTelNumber());

                return "ownData";
            } else return "login";
        } else return "login";
    }

    @RequestMapping(value = "/changeOwnData", method = RequestMethod.POST)
    public String changeOwnData(@RequestParam MultipartFile photo, @RequestParam String email,
                                @RequestParam String telNumber, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        Account account = accountService.findAccount(login);

        System.out.println(account.getLogin()+"  "+account.getPass()+"   "+account.getEmail()+"   "+account.getTelNumber());
        if (account.getAccountType().getTypeName().equals("customer")) {
            if (!photo.isEmpty()) {
                File file = new File("/Users/macbookair/IdeaProjects/ComInternetPlatform/src/main/resources/" + login + ".png");
                if (file.exists()) {
                    file.delete();
                }
                System.out.println("фотки нету но в IF вошел ---------------------------");
                // существует

                File file1 = new File("/Users/macbookair/IdeaProjects/ComInternetPlatform/src/main/resources/" + login + ".png");
                try (FileOutputStream fileOut = new FileOutputStream(file1)) {
                    fileOut.write(photo.getBytes());
                    fileOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            model.addAttribute("refPhoto", login);

            if (email.length() == 0 ) {
                model.addAttribute("email", account.getEmail());
                System.out.println("changeOwnData: email by Account: " + account.getEmail());

            } else {
                account.setEmail(email);
                System.out.println("changeOwnData: email: "+email);
                model.addAttribute("email", email);
            }

            if (telNumber.length() == 0) {
                model.addAttribute("telNumber", account.getTelNumber());
                System.out.println("changeOwnData: telNumber by Account: " + account.getTelNumber());

            } else {
                System.out.println("changeOwnData: telNumber:"+telNumber);
                account.setTelNumber(telNumber);
                model.addAttribute("telNumber", telNumber);
            }


            accountService.updateAccount(account);

            return "ownData";
        } else return "login";
    }

    @RequestMapping(value = "/changePosition/{positionID}")
    public String changePosition(@PathVariable(value = "positionID") String positionID, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
//        Account account = accountService.findAccount(login);
        PositionOfPrice positionOfPrice = positionOfPriceService.findPosition(Integer.valueOf(positionID));
        model.addAttribute("positionOfPrice", positionOfPrice);
        return "customer";
    }

    @RequestMapping(value = "/givePhoto/{refPhoto}")
    public ResponseEntity<byte[]> takePhoto(@PathVariable(value = "refPhoto") String refPhoto) {
        byte[] arr;
        try {
            File file = new File("/Users/macbookair/IdeaProjects/ComInternetPlatform/src/main/resources/" + refPhoto + ".png");
            if (!file.exists()){
                file = new File("/Users/macbookair/IdeaProjects/ComInternetPlatform/src/main/resources/defaultPhotoToScreen.png");
            }
            FileInputStream reader = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(reader);
            arr = new byte[inputStream.available()];
            int s = inputStream.read(arr);
            if (s == 0)
                throw new PhotoNotFoundException();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            System.out.println("отдает  фотку на страничку");

            return new ResponseEntity<byte[]>(arr, headers, HttpStatus.OK);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    public String addAccount(@RequestParam String login, @RequestParam String pass, @RequestParam String email,
                             @RequestParam String telNumber, @RequestParam String type, Model model) {

        AccountType at = accountTypeService.findByTypeName(type);
        accountService.addAccount(new Account(telNumber, email, pass, login, at));
//        model.addAttribute("accounts", accountService.listAccount());
        model.addAttribute("login", login);
        model.addAttribute("type", type);

//        if (type.equalsIgnoreCase("customer")) {
//            return "customer";
//        } else if (type.equalsIgnoreCase("client")) {
//            return "client";
//        } else throw new TypeIsNotFound();
        return "login";

    }


}
