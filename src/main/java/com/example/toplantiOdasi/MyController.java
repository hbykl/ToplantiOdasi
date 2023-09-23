package com.example.toplantiOdasi;
import com.example.toplantiOdasi.Classes.Room;
import com.example.toplantiOdasi.Classes.User;
import com.example.toplantiOdasi.Services.RoomService;
import com.example.toplantiOdasi.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/hutu")
public class MyController {

    @Autowired
    private UserService userService;
    RoomService roomService;
    public MyController(UserService userService,RoomService roomService){
        this.userService=userService;
        this.roomService=roomService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(String.class,new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public String login(Model theModel,User user){
        theModel.addAttribute("user",user);
        return "Login";
    }

    @RequestMapping(value = "/controlUser",method = {RequestMethod.GET,RequestMethod.POST})
    public String controlUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request){
        User user1=userService.getUser(user.getMail(),user.getPassword());
        if(userService.checkGetUser(user1)) {
            Cookie cookie = new Cookie("User",user1.getId().toString());
            cookie.setMaxAge(60*60);
            cookie.setPath("/");
            response.addCookie(cookie);
            request.getSession().setAttribute("Session",user1.getId());

            return "redirect:/hutu/user/rooms";
        } else return "Login";
    }

    @GetMapping("/register")
    public String register(Model theModel,User user){
        theModel.addAttribute("kayit",user);
        return "Register";
    }
    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("kayit") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "Register";
        }
        else {
        userService.saveUser(user);
        return "redirect:/hutu/login";}
    }
    @GetMapping("/user/rooms")
    public String rooms(Model theModel, @CookieValue(name = "User", required = false)Cookie myCookie, @CookieValue(name = "info",required = false)Cookie infoCookie, HttpSession session) {
        if(myCookie==null) {
            return "redirect:/hutu/login";
        }
        List<Room> rooms=roomService.getAll();
        theModel.addAttribute("room",rooms);
        String infCookie="null";
        if(infoCookie!=null)
            infCookie=infoCookie.getValue();

        theModel.addAttribute("infoCookie",infCookie);

        return "Rooms";
    }
    @GetMapping("/user/delete/{id}")
    public String delete( @PathVariable long id,@CookieValue(name = "User", required = false)Cookie myCookie,HttpServletResponse response) {
        if(myCookie==null) {
            return "redirect:/hutu/login";
        }
        String info=roomService.getRoom(id).getRoomName()+"Silindi";
        Cookie infoCookie=new Cookie("info",info.trim());
        roomService.deleteRoom(roomService.getRoom(id));
        infoCookie.setPath("/");
        infoCookie.setMaxAge(5);
        response.addCookie(infoCookie);
        return "redirect:/hutu/user/rooms";
    }

    @GetMapping("/user/newRoom")
    public String newRoom(Model theModel,Room room,@CookieValue(name = "User", required = false)Cookie myCookie) {
        if(myCookie==null) {
            return "redirect:/hutu/login";
        }
        theModel.addAttribute("newRoom",room);
        return "RoomRegister";
    }
    @PostMapping("/user/saveRoom")
    public String saveRoom(@Valid @ModelAttribute("newRoom") Room room, BindingResult bindingResult,@CookieValue(name = "User", required = false)Cookie myCookie,HttpServletResponse response,HttpSession session) {
        if(myCookie==null) {
            return "redirect:/hutu/login";
        }
        if(bindingResult.hasErrors()){
            return "RoomRegister";
        }
        else {
            room.setUserId((long) session.getAttribute("Session"));
            roomService.saveRoom(room);
            String info=room.getRoomName()+"Oluşturuldu";
            Cookie infoCookie=new Cookie("info",info.trim());
            infoCookie.setPath("/");
            infoCookie.setMaxAge(5);
            response.addCookie(infoCookie);
            return "redirect:/hutu/user/rooms";}
    }
    @GetMapping("/user/update/{id}")
    public String update(@PathVariable long id,Model theModel,@CookieValue(name = "User", required = false)Cookie myCookie) {
        if(myCookie==null) {
            return "redirect:/hutu/login";
        }
        Room room = roomService.getRoom(id);
        theModel.addAttribute("Room", room);
        return "Update";
    }
    @PostMapping("/user/updateRoom")
    public String updateRoom(@Valid @ModelAttribute("Room")Room room,BindingResult bindingResult,@CookieValue(name = "User", required = false)Cookie myCookie,HttpServletResponse response) {
        if(myCookie==null) {
            return "redirect:/hutu/login";
        }
        if(bindingResult.hasErrors())
            return "Update";
        roomService.saveRoom(room);
        String info=room.getRoomName()+"Güncellendi";
        Cookie infoCookie=new Cookie("info",info.trim());
        infoCookie.setPath("/");
        infoCookie.setMaxAge(5);
        response.addCookie(infoCookie);
        return "redirect:/hutu/user/rooms";
    }
    @RequestMapping(value = "/user/logout",method = {RequestMethod.GET,RequestMethod.POST})
    public String logout(HttpServletResponse response,@CookieValue(name = "User", required = false)Cookie myCookie){
        if(myCookie==null)
            return "redirect:/hutu/login";
        myCookie.setPath("/");
        myCookie.setMaxAge(0);
        response.addCookie(myCookie);
        return "redirect:/hutu/login";
    }

}
