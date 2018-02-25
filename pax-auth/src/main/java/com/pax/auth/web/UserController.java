package com.pax.auth.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.pax.auth.entity.Role;
import com.pax.auth.entity.User;
import com.pax.auth.inputparam.UpdatePwdInput;
import com.pax.auth.inputparam.UserAddInput;
import com.pax.auth.inputparam.UserUpdateInput;
import com.pax.auth.service.AuthJsonService;
import com.pax.auth.service.RoleService;
import com.pax.auth.service.UserService;
import com.pax.auth.util.AuthUtils;
import com.pax.common.util.BindingResultHandler;
import com.pax.common.util.ConstantUtil;
import com.pax.common.util.DateUtils;
import com.pax.common.util.MapUtils;
import com.pax.common.util.TranslationUtils;
import com.pax.common.util.WebUtils;
import com.pax.common.web.BaseAjaxController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController extends BaseAjaxController {
	private static final Logger	logger	= LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private UserService			userService;
	
	@Resource
	private RoleService			roleService;
	
	@Resource
	private AuthJsonService		authJsonService;
	
	@RequiresPermissions("user:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "auth/user";
	}
	
	@RequestMapping(value = "/createRandomCode")
	public void createRandomCode(HttpServletResponse response) {
		
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		
		// 在内存中创建图象
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		// 获取图形上下文
		Graphics g = image.getGraphics();
		
		// 生成随机类
		Random random = new Random();
		
		// 设定背景色
		g.setColor(getRandColor(100, 250));
		g.fillRect(0, 0, width, height);
		
		// 设定字体
		g.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);
		
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		
		// 取随机产生的认证码(4位)
		String sRand = "";
		
		for (int i = 0; i < 4; i++) {
			
			int r = random.nextInt(62);
			String rand;
			if (r < 10) {// 数字
				rand = String.valueOf(r);
			} else if (r > 9 && r < 36) {// 大写字母
				char temp = (char) (r + 55);
				rand = String.valueOf(temp);
			} else {// 小写字母
				char temp = (char) (r + 61);
				rand = String.valueOf(temp);
			}
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
				20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}
		
		// 将认证码存入SESSION
		session.setAttribute("randomCode", sRand);
		
		// 图象生效
		g.dispose();
		OutputStream output;
		try {
			output = response.getOutputStream();
			// 输出图象到页面
			ImageIO.write(image, "JPEG", output);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	/**
	 * 显示主页
	 * @return
	 */
	@RequestMapping(value = "/main")
	public String main(String kickout, Model model) {
		
		TranslationUtils translation = TranslationUtils
				.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			model.addAttribute("username", AuthUtils.getUserName());
			model.addAttribute("webtitle", translation.__(AuthUtils.getUser().getSite().getName()));
			model.addAttribute("orgname", translation.__(AuthUtils.getUser().getOrg().getName()));
			return "main";
		} else {
			
			if (StringUtils.isNotBlank(kickout)) {
				model.addAttribute("emsg", (TranslationUtils.getInstance(WebUtils.getSession() == null ? null : (Locale) WebUtils.getSession().getAttribute("locale")).__("你被强制退出登录")));
			}
			
			return "login/login";
		}
	}
	
	@RequestMapping(value = "/login")
	public String login(String loginname, String password,String loginlocal, String randomCode, Model model) throws ParseException {
		TranslationUtils translation = TranslationUtils
				.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		if (subject.isAuthenticated()) {
			return "redirect:/";
		}
		if(StringUtils.isBlank(loginlocal)){
			return "login/login";
		}
		Locale $locale = new Locale(StringUtils.split(loginlocal, "_")[0], StringUtils.split(loginlocal, "_")[1]);
		WebUtils.getSession().setAttribute("locale", $locale);
		String emsg = null;
		
		try {
			
			Object code = session.getAttribute("randomCode");
			logger.info("session中验证码:{}", code);
			logger.info("前端传入的验证码:{}", randomCode);
			if (code == null) {
				throw new AuthenticationException((TranslationUtils.getInstance(WebUtils.getSession() == null ? null : (Locale) WebUtils.getSession().getAttribute("locale")).__("验证码错误")));
			} else {
				if (!code.toString().equalsIgnoreCase(randomCode)) {
					throw new AuthenticationException((TranslationUtils.getInstance(WebUtils.getSession() == null ? null : (Locale) WebUtils.getSession().getAttribute("locale")).__("验证码错误")));
				}
			}
			
			if (StringUtils.isBlank(loginname)) {
				throw new AuthenticationException((TranslationUtils.getInstance(WebUtils.getSession() == null ? null : (Locale) WebUtils.getSession().getAttribute("locale")).__("用户名不能为空")));
			}
			if (StringUtils.isBlank(password)) {
				throw new AuthenticationException((TranslationUtils.getInstance(WebUtils.getSession() == null ? null : (Locale) WebUtils.getSession().getAttribute("locale")).__("密码不能为空")));
			}
			
			UsernamePasswordToken token = new UsernamePasswordToken(loginname, password);
			
			subject.login(token);
			User user = userService.login(loginname);
			String currentTime = DateUtils.stringDateFormat(DateUtils.getCurrentDateString(), "yyyy-MM-dd HH:mm:ss");
			//首次登陆修改密码
			if(StringUtils.isBlank(user.getLastlogintime())){
				throw new AuthenticationException("请修改密码");
			}
			if(StringUtils.isBlank(user.getLastupdatepwdtime())){
				throw new AuthenticationException("请修改密码");
			}
			String lastupdatepwdTime = DateUtils.stringDateFormat(user.getLastupdatepwdtime(), "yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			if(StringUtils.isBlank(lastupdatepwdTime)){
				throw new AuthenticationException("请修改密码");
			}
			if(lastupdatepwdTime != null&&!"".equals(lastupdatepwdTime)){ 
				Date currentDate = sdf.parse(currentTime);
				Date lastupdatepwdDate = sdf.parse(lastupdatepwdTime);
				int countDate = DateUtils.daysBetween(lastupdatepwdDate, currentDate);
				if(countDate>ConstantUtil.UPDATE_PASSWORD_TIME){
					throw new AuthenticationException("请修改密码");
					
				}
			}
			
			
		} catch (IncorrectCredentialsException e) {
			User user = userService.login(loginname);
			if("5".equals(user.getErrortimes())){
				emsg = translation.__("密码输入次数超过5次，已被冻结");
			}
			int errrortimes = 0;
			if(user.getErrortimes()!=null&&!"".equals(user.getErrortimes())){
				
				errrortimes = Integer.valueOf(user.getErrortimes());
			}
			errrortimes++;
			user.setErrortimes(String.valueOf(errrortimes));
			user.setFrozenreason(translation.__("密码输入次数超过五次"));
			userService.updateErrortimes(user);
			String count  = String.valueOf(5-errrortimes);
			if("0".equals(count)){
				userService.frozen(user);
				emsg = translation.__("密码输入次数超过5次，已被冻结");
			}else{
				String emsg1 = translation.__("密码错误，还剩")+count;
				String emsg2 = translation.__("次输入机会");
				emsg = emsg1 + emsg2;
			}
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
			emsg = e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			emsg = translation.__("系统异常 ");
		}
		
		User user = userService.login(loginname);
		
		
		
		if (StringUtils.isBlank(emsg)) {
			user.setLastlogintime(DateUtils.getCurrentDateString());
			userService.updateLastlogintime(user);
			return "redirect:/";
		}if("请修改密码".equals(emsg)){
			subject.logout();
			user.setErrortimes(StringUtils.EMPTY);
			userService.updateErrortimes(user);
			model.addAttribute("loginname", user.getLoginname());
			model.addAttribute("password", password);

			return "login/updatePwd";
		} else {
			model.addAttribute("emsg", emsg);
			return "login/login";
		}
		
	}
	
	@RequiresAuthentication
	@RequestMapping(value = "/logout")
	public String logout() {
		
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout();
		}
		
		return "redirect:/";
		
	}
	
	@RequiresPermissions("user:save")
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseEntity save(@Validated UserAddInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		
		userService.save(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
	}
	
	//@RequiresPermissions("user:updatePwd")
	@RequestMapping(value = "/updatePwd")
	@ResponseBody
	public ResponseEntity updatePwd(@Validated UpdatePwdInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		
		userService.updatePwd(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
	}
	@RequestMapping(value = "/firstUpdatePwd")
	@ResponseBody
	public ResponseEntity firstUpdatePwd(@Validated UpdatePwdInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		userService.firstUpdatePwd(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
	}
	
	
	/**
	 * 得到本级机构和子机构的用户
	 * @return
	 */
	@RequiresPermissions("user:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseEntity list() {
		
		initializePagingSortingFiltering();
		
		PageInfo<User> page = userService.list(pageQueryParam);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForUser(page.getList());
		
		JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresPermissions("user:detail")
	@RequestMapping(value = "/detail")
	@ResponseBody
	public ResponseEntity detail(String id) {
		
		User user = userService.get(id);
		
		JSONObject result = authJsonService.getJson(user);
		
		return new ResponseEntity(makeSuccessJson("操作成功", result), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("user:update")
	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseEntity update(@Validated UserUpdateInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		
		userService.update(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("user:resetPwd")
	@RequestMapping(value = "/resetPwd")
	@ResponseBody
	public ResponseEntity resetPwd(String id) {
		
		String newPwd = userService.resetPwd(id);
		
		return new ResponseEntity(makeSuccessJson("操作成功", newPwd), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("user:frozen")
	@RequestMapping(value = "/frozen")
	@ResponseBody
	public ResponseEntity frozen(String[] ids) {
		
		userService.frozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("user:unfrozen")
	@RequestMapping(value = "/unfrozen")
	@ResponseBody
	public ResponseEntity unfrozen(String[] ids) {
		
		userService.unfrozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("user:delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity delete(String[] ids) {
		
		userService.delete(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	/**
	 * 得到某个用户的所有角色，即用户已经选择了的角色
	 * @param id
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/getRolesByUser")
	@ResponseBody
	public ResponseEntity getRolesByUser(String id) {
		
		List<Role> list = roleService.getRolesByUser(id);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForRole(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	/**
	 * 得到某个用户所有可以选择的角色，1、通过被分配用户所属的机构来过滤；
	 * 2、只能从创建者所拥有的本机机构的最大角色范围取,再加上自己创建的角色；3、如果是平台的操作员，包含被分配用户的机构下的的所有权限
	 * @param 参数：id（用户id），search_name(按角色名称来过滤)
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/getRolesToUseByUser")
	@ResponseBody
	public ResponseEntity getRolesToUseByUser() {
		
		initializeFiltering();
		
		List<Role> list = roleService.getRolesToUseByUser(filterMap);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForRole(list);
		
		JSONObject result = makeSuccessJson2("操作成功", jsonArray, list.size());
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresPermissions("user:grantRoles")
	@RequestMapping(value = "/grantRoles")
	@ResponseBody
	public ResponseEntity grantRoles() {
		
		initializeFiltering();
		
		userService.grantRoles(filterMap);
		
		JSONObject result = makeSuccessJson("操作成功");
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
}
