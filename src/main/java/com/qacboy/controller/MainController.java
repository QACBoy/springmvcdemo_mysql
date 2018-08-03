package com.qacboy.controller;

import com.qacboy.model.UserEntity;
import com.qacboy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    UserRepository userRepository;

    public static int uid=-1;
    public static int isAdmin=0;

    /***
     * 描述：
     *      直接重定向到用户登录界面
     *
     * @return
     * TODO http://localhost:8080/
     */
    @RequestMapping(value = "/")
    public String index(){
        return "redirect:login";
    }

    /***
     * 描述：
     *      默认用户 name="Steve" password="gaussic"
     *      如果需要登录到管理员界面，请直接用管理员账号进行登录，账号和密码皆为：admin
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(ModelMap modelMap){
        String name="Steve";
        String password = "gaussic";

        modelMap.addAttribute("nickname",name);
        modelMap.addAttribute("password",password);
        return "login";
    }

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public String check(HttpServletRequest httpServletRequest, ModelMap modelMap){
        //获取网页端提交的数据
        String name=httpServletRequest.getParameter("nickname");
        String password=httpServletRequest.getParameter("password");

        if(name.equals("admin")&&password.equals("admin")){
            MainController.isAdmin=1;
            return "admin";
        }
        //验证是否账号的正确性，如果列表为空，证明不存在或者输入的账号密码有误，不为空的话，跳转至博客界面
        List<UserEntity>userList=userRepository.findByNameAndPassword(name,password);
        if(!userList.isEmpty()){
            uid=userList.get(0).getId();
            MainController.isAdmin=0;
            return "redirect:admin/blogs";
        }
        return "redirect:login";
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap) {
        if(MainController.isAdmin==1){
            // 查询user表中所有记录
            List<UserEntity> userList = userRepository.findAll();

            // 将所有记录传递给要返回的jsp页面，放在userList当中
            modelMap.addAttribute("userList", userList);
//            modelMap.addAttribute("updateUser",0);
            // 返回pages目录下的admin/users.jsp页面
            return "admin/users";
        }else {
            return "redirect:/login";
        }

    }

    // get请求，访问添加用户 页面
    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public String addUser() {
        // 返回 admin/addUser.jsp页面
        return "admin/addUser";
    }

    // post请求，处理添加用户请求，并重定向到用户管理页面
    @RequestMapping(value = "/admin/users/addP", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") UserEntity userEntity) {
        // 注意此处，post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
        // 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象

        // 数据库中添加一个用户，该步暂时不会刷新缓存
        //userRepository.save(userEntity);
        System.out.println(userEntity.getFirstName());
        System.out.println(userEntity.getLastName());

        // 数据库中添加一个用户，并立即刷新缓存
        userRepository.saveAndFlush(userEntity);

        // 重定向到用户管理页面，方法为 redirect:url
        return "redirect:/login";
    }

    // 查看用户详情
    // @PathVariable可以收集url中的变量，需匹配的变量用{}括起来
    // 例如：访问 localhost:8080/admin/users/show/1 ，将匹配 id = 1
    @RequestMapping(value = "/admin/users/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        UserEntity userEntity = userRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/userDetail";
    }

    // 更新用户信息 页面
    @RequestMapping(value = "/admin/users/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        UserEntity userEntity = userRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/updateUser";
    }

    // 更新用户信息 操作
    @RequestMapping(value = "/admin/users/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("userP") UserEntity user) {

        // 更新用户信息
        userRepository.updateUser(user.getNickname(), user.getFirstName(),
                user.getLastName(), user.getPassword(), user.getId());
        userRepository.flush(); // 刷新缓冲区
        return "redirect:/admin/users";
    }

    // 删除用户
    @RequestMapping(value = "/admin/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer userId) {

        // 删除id为userId的用户
        userRepository.delete(userId);
        // 立即刷新
        userRepository.flush();
        return "redirect:/admin/users";
    }
}
