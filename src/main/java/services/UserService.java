package services;

import java.util.List;

import model.User;
import persistence.UserDAO;
import persistence.commons.DAOFactory;
import utils.Crypt;

public class UserService {

	public List<User> list() {
		return DAOFactory.getUserDAO().findAll();
	}

	public User create(String username, String type, Integer coins, Double time, String password, Boolean admin) {
		User user = new User(-1,username, type, coins, time,Crypt.hash(password), admin,0,0.0);

		if (user.isValid()) {
			UserDAO userDAO = DAOFactory.getUserDAO();
			userDAO.insert(user);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return user;
	}

	public User update(Integer id, String username, Integer coins, Double time, String type, Boolean admin) {

		UserDAO userDAO = DAOFactory.getUserDAO();
		User user = userDAO.find(id);

		user.setUsername(username);
		user.setType(type);
		user.setCoins(coins);
		user.setTime(time);
		user.setAdmin(admin);
		

		if (user.isValid()) {
			userDAO.update(user);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return user;
	}

	public void delete(Integer id) {
		User user = new User(id, null, null, null, null, null,null,null,null);
		UserDAO userDAO = DAOFactory.getUserDAO();
		userDAO.delete(user);
	}

	public User find(Integer id) {
		UserDAO userDAO = DAOFactory.getUserDAO();
		return userDAO.find(id);
	}

}
