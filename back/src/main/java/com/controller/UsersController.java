
package com.controller;

import java.util.List;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.UsersEntity;
import com.service.TokenService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 登录相关
 */
@RequestMapping("users")
@RestController
public class UsersController {

	@Autowired
	private UsersService usersService; // 自动注入 UsersService，用于用户管理相关操作

	@Autowired
	private TokenService tokenService; // 自动注入 TokenService，用于管理用户的身份令牌

	/**
	 * 用户登录方法
	 */
	@IgnoreAuth                     // 不进行身份验证
	@PostMapping(value = "/login") // 映射 POST 请求到 "/users/login"
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 从数据库中查找用户名对应的用户
		UsersEntity user = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
		// 检查用户是否存在及密码是否正确
		if(user == null || !user.getPassword().equals(password)) {
			return R.error("账号或密码不正确"); // 返回错误信息
		}
		// 生成令牌，并将用户的相关信息存入响应
		String token = tokenService.generateToken(user.getId(), username, "users", user.getRole());
		R r = R.ok();
		r.put("token", token);
		r.put("role", user.getRole());
		r.put("userId", user.getId());
		return r; // 返回带令牌的成功响应
	}

	/**
	 * 注册
	 */
	@IgnoreAuth // 不进行身份验证
	@PostMapping(value = "/register") // 映射 POST 请求到 "/users/register"
	public R register(@RequestBody UsersEntity user){
//    	ValidatorUtils.validateEntity(user);
		// 检查用户是否已经存在
    	if(usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", user.getUsername())) !=null) {
    		return R.error("用户已存在");// 返回错误信息
    	}
        usersService.insert(user); // 插入新用户
        return R.ok(); // 返回成功响应
    }

	/**
	 * 退出
	 */
	@GetMapping(value = "logout") // 映射 GET 请求到 "/users/logout"
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();       // 销毁会话，清除用户信息
		return R.ok("退出成功");                 // 返回成功响应
	}

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword") // 映射 GET 请求到 "/users/updatePassword"
	public R updatePassword(String oldPassword, String newPassword, HttpServletRequest request) {
		// 获取当前会话中的用户信息
		UsersEntity users = usersService.selectById((Integer) request.getSession().getAttribute("userId"));
		if (newPassword == null) {
			return R.error("新密码不能为空"); // 返回错误信息
		}
		if (!oldPassword.equals(users.getPassword())) {
			return R.error("原密码输入错误"); // 返回错误信息
		}
		if (newPassword.equals(users.getPassword())) {
			return R.error("新密码不能和原密码一致"); // 返回错误信息
		}
		users.setPassword(newPassword);     // 设置新密码
		usersService.updateById(users);     // 更新用户信息
		return R.ok(); // 返回成功响应
	}

	/**
	 * 密码重置
	 */
	@IgnoreAuth // 不进行身份验证
	@RequestMapping(value = "/resetPass") // 映射到 "/users/resetPass"
	public R resetPass(String username, HttpServletRequest request) {
		UsersEntity user = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
		if (user == null) {
			return R.error("账号不存在"); // 返回错误信息
		}
		user.setPassword("123456"); // 重置密码为 "123456"
		usersService.update(user, null); // 更新用户信息
		return R.ok("密码已重置为：123456"); // 返回成功响应
	}


	/**
	 * 用户列表分页
	 */
	@RequestMapping("/page") // 映射到 "/users/page"
	public R page(@RequestParam Map<String, Object> params, UsersEntity user) {
		EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();
		PageUtils page = usersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, user), params), params));
		return R.ok().put("data", page); // 返回分页数据
	}


	/**
	 * 获取用户列表
	 */
	@RequestMapping("/list") // 映射到 "/users/list"
	public R list(UsersEntity user) {
		EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();
		ew.allEq(MPUtil.allEQMapPre(user, "user"));
		return R.ok().put("data", usersService.selectListView(ew)); // 返回用户列表数据
	}





	/**
	 * 根据 ID 获取用户信息
	 */
	@RequestMapping("/info/{id}") // 映射到 "/users/info/{id}"
	public R info(@PathVariable("id") String id) {
		UsersEntity user = usersService.selectById(id); // 根据 ID 查找用户
		return R.ok().put("data", user); // 返回用户数据
	}





	/**
	 * 获取当前会话中的用户信息
	 */
	@RequestMapping("/session") // 映射到 "/users/session"
	public R getCurrUser(HttpServletRequest request) {
		Integer id = (Integer) request.getSession().getAttribute("userId");
		UsersEntity user = usersService.selectById(id); // 根据会话中的 ID 获取用户
		return R.ok().put("data", user); // 返回用户数据
	}





    /**
	 * 保存用户信息
	 */
    @PostMapping("/save")    // 映射 POST 请求到 "/users/save"
    public R save(@RequestBody UsersEntity user){
//    	ValidatorUtils.validateEntity(user);
		// 检查用户是否已经存在
    	if(usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", user.getUsername())) !=null) {
    		return R.error("用户已存在"); // 返回错误信息
    	}
        usersService.insert(user); // 插入新用户
        return R.ok();             // 返回成功响应
    }










    /**
     * 修改
     */
    @RequestMapping("/update")// 映射到 "/users/update"
    public R update(@RequestBody UsersEntity user){
//        ValidatorUtils.validateEntity(user);
        usersService.updateById(user);//全部更新
        return R.ok();// 返回成功响应
    }








    /**
     * 删除
     */
    @RequestMapping("/delete")// 映射到 "/users/delete"
    public R delete(@RequestBody Long[] ids){
		List<UsersEntity> user = usersService.selectList(null);// 获取所有用户
		// 检查是否至少保留一个管理员
		if(user.size() > 1){
			usersService.deleteBatchIds(Arrays.asList(ids)); // 删除指定 ID 的用户
		}else{
			return R.error("管理员最少保留一个");   // 返回错误信息
		}
        return R.ok();  // 返回成功响应
    }
}
