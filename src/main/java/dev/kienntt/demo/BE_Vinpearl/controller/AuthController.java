package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.config.JwtTokenProvider;
import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.model.Hotel;
import dev.kienntt.demo.BE_Vinpearl.model.Role;
import dev.kienntt.demo.BE_Vinpearl.model.User;
import dev.kienntt.demo.BE_Vinpearl.service.RoleService;
import dev.kienntt.demo.BE_Vinpearl.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
import java.time.LocalDateTime;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    LocalDateTime localDateTime = LocalDateTime.now();

    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    @PostMapping("/register")
    public ResponseMessage create(@RequestBody User user) {
        userService.save(user);
        return new ResponseMessage(200, "Tạo tài khoản thành công", null, null);
    }

//    @PostMapping("/login")
//    public ResponseMessage login(@RequestBody User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
//        return new ResponseMessage(200, "Success", jwtTokenProvider.createJwtSignedHMAC(user), null);
//    }

    @PostMapping("/login")
    public ResponseMessage login(@RequestBody User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User userDb = userService.findByEmail(user.getEmail());
        String md5Password = DigestUtils.md5Hex(user.getPassword()).toUpperCase();
//        System.out.println("Verify: " + userDb.getPassword().equals(md5Password));
        if (!userDb.getPassword().equals(md5Password)) {
            return new ResponseMessage(404, "Fail", "Tài đăng nhập/ Mật khẩu không đúng", null);
        }
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        userDb.setToken(jwtTokenProvider.createJwtSignedHMAC(userDb));
        return new ResponseMessage(200, "Success", userDb, null);
    }

    @GetMapping("/findAll")
    public ResponseMessage findAll() {
        Iterable<User> listUser = userService.findAll();
        return new ResponseMessage(200, "Success", listUser, null);
    }

    @GetMapping("/detail/{id}")
    public ResponseMessage getUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseMessage(200, "Success", user, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @PutMapping("/updateUser")
    public ResponseMessage updateUser(@PathVariable("id") Long userId, @RequestBody User user) {
        user.setId(userId);
        user.setCreatedDate(localDateTime.toString());
        user.setCreatedBy(user.getCreator());
        userService.updateUser(user);
        return new ResponseMessage(200, "Success", "", null);
    }

    @GetMapping("/delete/{roomId}")
    public ResponseMessage deleteRoom(@PathVariable Long roomId) {
        userService.deleteUser(roomId);
        return new ResponseMessage(200, "User successfully deleted!", null, null);
    }

    @GetMapping("/search")
    public ResponseMessage searchUsers(@RequestParam(required = false) Long siteId,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) String phone,
                                            Pageable pageable) {
        Page<User> listUser = userService.searchUser(siteId, name, phone, pageable);
        return new ResponseMessage(200, "Success", listUser, null);
    }

    @GetMapping("/permission/findAll")
    public ResponseMessage getPermission() {
        Iterable<User> listUser = roleService.findAll();
        return new ResponseMessage(200, "Success", listUser, null);
    }

    @PostMapping("/permission/create")
    public ResponseMessage addPermission(@RequestBody Role role) {
        Role role1 = roleService.save(role);
        return new ResponseMessage(200, "Success", role1, null);
    }
}
