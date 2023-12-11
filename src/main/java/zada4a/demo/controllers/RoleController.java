package zada4a.demo.controllers;


import groovy.lang.Lazy;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zada4a.demo.entities.Users;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
@RequestMapping("/admin")
public class RoleController {

    @Value("${baseURL}")
    private String baseURL;

    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return (Users) authentication.getPrincipal();
        }
        return null;
    }

    @GetMapping()
    public String AdminPage(Model model) {
        model.addAttribute("currentUser", getUser());
        return "/admin/admin";
    }

}
