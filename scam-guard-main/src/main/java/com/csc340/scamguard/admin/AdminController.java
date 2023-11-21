package com.csc340.scamguard.admin;

import com.csc340.scamguard.user.User;
import com.csc340.scamguard.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    @Autowired
    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

//    public AdminController(AdminService adminService) {
//        this.adminService = adminService;
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable Long id) {
        return adminService.getAdminById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers(); // Assuming there's a method to get all users
        return ResponseEntity.ok(users);
    }

    // Endpoint to delete a user account
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id); // Assuming there's a method that deletes a user and returns a boolean
        if (isDeleted) {
            return ResponseEntity.ok().build(); // Return 200 OK if the deletion was successful
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if the user didn't exist
        }
    }
}
