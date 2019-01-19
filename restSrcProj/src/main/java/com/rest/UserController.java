package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dao.ResponseDAO;
import com.rest.pojo.User;
import com.rest.pojo.UserRequest;
import com.rest.pojo.UserUpdateRequest;

@RestController
@RequestMapping("/user")
public class UserController {

	private final String addKey = "Add_KEY";
	private final String delKey = "Delete_KEY";
	private final String updateKey = "Update_KEY";
	
	private static final String SUCCESS_STATUS = "success";
	private static final String ERROR_STATUS = "error";
	private static final int CODE_SUCCESS = 100;
	private static final int AUTH_FAILURE = 102;

	@Autowired
	ResponseDAO responseDao;

	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)
	public BaseResponse addUser(@RequestParam(value = "key") String key, @RequestBody UserRequest request) {

		BaseResponse response = new BaseResponse();
		User res = new User();
		if (addKey.equalsIgnoreCase(key)) {
			int userId = request.getUserId();
			String username = request.getUsername();
			String password = request.getPassword();
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
			response.setMessageToDisplay("User added successfully");
			res.setUserId(userId);
			res.setName("" + username);
			res.setStatus("ACTIVATED");
			res.setPassword(password);
			responseDao.persist(res);
			// responseDao.delete(res);
		} else {
			res.setId(1);
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
			response.setMessageToDisplay("Error in adding payment");
			res.setName("" + AUTH_FAILURE);
			res.setPassword("Error in adding payment");
			res.setStatus(ERROR_STATUS);
			responseDao.persist(res);
			// need to check
			// responseDao.delete(res);
		}
		return response;
	}

	@RequestMapping(value = "/userDel", method = RequestMethod.DELETE)
	public BaseResponse delUser(@RequestParam(value = "key") String key, @RequestBody UserRequest request) {

		BaseResponse response = new BaseResponse();
		User res = new User();
		if (delKey.equalsIgnoreCase(key)) {
			int userId = request.getUserId();
			String username = request.getUsername();
			String password = request.getPassword();
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
			response.setMessageToDisplay("User added successfully");
			res.setUserId(userId);
			res.setName("" + username);
			res.setStatus("ACTIVATED");
			res.setPassword(password);
			responseDao.delete(res);
		} else {
			res.setId(1);
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
			response.setMessageToDisplay("Error in adding user invalid key");
			res.setName("" + AUTH_FAILURE);
			res.setPassword("Error in adding payment");
			res.setStatus(ERROR_STATUS);
			responseDao.delete(res);
		}
		return response;
	}

	@RequestMapping(value = "/userUpdate", method = RequestMethod.PUT)
	public BaseResponse updateUser(@RequestParam(value = "key") String key, @RequestBody UserUpdateRequest request) {

		BaseResponse response = new BaseResponse();
		User res = new User();
		if (updateKey.equalsIgnoreCase(key)) {
			int olduserId = request.getOldUserId();
			int newuserid = request.getNewUserId();
			String password = request.getNewPassword();
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
			response.setMessageToDisplay("User updated successfully");
			responseDao.update(olduserId, newuserid, password);
		} else {
			res.setId(1);
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
			response.setMessageToDisplay("Error in updating user");
			res.setName("" + AUTH_FAILURE);
			res.setPassword("Error in adding payment");
			res.setStatus(ERROR_STATUS);
			responseDao.delete(res);
		}
		return response;
	}

	@RequestMapping(value = "/userget", method = RequestMethod.GET)
	public BaseResponse getUser(@RequestParam(value = "userId") String userId) {
		BaseResponse response = new BaseResponse();
		User res = responseDao.getInfo(userId);
		return response;
	}

}
