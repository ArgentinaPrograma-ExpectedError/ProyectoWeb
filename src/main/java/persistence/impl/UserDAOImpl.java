package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.Attraction;
import model.Promotion;
import model.Suggestion;
import model.User;
import model.nullobjects.NullUser;
import persistence.AttractionDAO;
import persistence.UserDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;

public class UserDAOImpl implements UserDAO {

	public int insert(User user) {
		try {
			String sql = "INSERT INTO USUARIOS (NOMBRE, TIPO_PREFERIDO,DINERO_DISPONIBLE,TIEMPO_DISPONIBLE,PASSWORD, ADMIN) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUsername());

			if (user.getType().equals("AVENTURA")) {
				statement.setInt(2, 1);
			}
			if (user.getType().equals("DEGUSTACION")) {
				statement.setInt(2, 2);
			}
			if (user.getType().equals("PAISAJE")) {
				statement.setInt(2, 3);
			}

			statement.setInt(3, user.getCoins());
			statement.setDouble(4, user.getTime());
			statement.setString(5, user.getPassword());
			statement.setBoolean(6, user.isAdmin());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(User user) {
		try {
			String sql = "UPDATE USUARIOS SET NOMBRE = ?, TIPO_PREFERIDO = ?,DINERO_DISPONIBLE = ?, TIEMPO_DISPONIBLE = ?,ADMIN = ? WHERE ID= ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, user.getUsername());

			if (user.getType().equals("AVENTURA")) {
				statement.setInt(2, 1);
			}
			if (user.getType().equals("DEGUSTACION")) {
				statement.setInt(2, 2);
			}
			if (user.getType().equals("PAISAJE")) {
				statement.setInt(2, 3);
			}

			statement.setInt(3, user.getCoins());
			statement.setDouble(4, user.getTime());

			statement.setBoolean(5, user.isAdmin());
			statement.setInt(6, user.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(User user) {
		try {
			String sql = "DELETE FROM USUARIOS WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public User findByUsername(String username) {
		try {
			String sql = "SELECT ID, NOMBRE, TIPO_PREFERIDO, DINERO_DISPONIBLE, TIEMPO_DISPONIBLE, PASSWORD ,ADMIN FROM USUARIOS WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultados = statement.executeQuery();

			User user = NullUser.build();

			if (resultados.next()) {
				user = toUser(resultados);
			}

			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public User find(Integer id) {
		try {
			String sql = "SELECT  usuarios.id,usuarios.NOMBRE, tipo_atraccion.tipo , usuarios.DINERO_DISPONIBLE, usuarios.TIEMPO_DISPONIBLE, usuarios.password ,usuarios.ADMIN from usuarios\r\n"
					+ "JOIN tipo_atraccion on usuarios.tipo_preferido = tipo_atraccion.id \r\n"
					+ "WHERE usuarios.id= ?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			User user = NullUser.build();

			if (resultados.next()) {
				user = toUser(resultados);
			}

			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM USUARIOS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<User> findAll() {
		try {
			String sql = "SELECT USUARIOS.ID, USUARIOS.NOMBRE,tipo_atraccion.tipo,USUARIOS.dinero_disponible, USUARIOS.tiempo_disponible ,	USUARIOS.password, usuarios.admin FROM usuarios  JOIN tipo_atraccion on usuarios.tipo_preferido=tipo_atraccion.id";
			;
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<User> users = new LinkedList<User>();
			while (resultados.next()) {
				users.add(toUser(resultados));
			}

			return users;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public void agregarAItinerario(User user, Suggestion suggestion) {
		if (suggestion instanceof Attraction) {
			try {
				String sql = "INSERT INTO itinerarios (id_usuario, id_atraccion) VALUES (?, ?)";
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);

				statement.setInt(1, user.getId());
				statement.setInt(2, suggestion.getId());

				statement.executeUpdate();

			} catch (Exception e) {
				throw new MissingDataException(e);
			}
		}
		if (suggestion instanceof Promotion) {
			try {
				String sql = "INSERT INTO itinerarios (id_usuario, id_atraccion) VALUES (?, ?)";
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);

				for (Attraction a : suggestion.getAttractions()) {

					statement.setInt(1, user.getId());
					statement.setInt(2, a.getId());
					statement.executeUpdate();

				}

			} catch (Exception e) {
				throw new MissingDataException(e);
			}
		}

	}

	public List<Attraction> cargarAtraccionesItinerario(User u) {
		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		List<Attraction> array = new LinkedList<Attraction>();
		List<Attraction> todas = attractionDAO.findAll();
		List<String> atracciones = cargarAtraccionesCompradas(u);

		for (String string : atracciones) {
			for (Attraction a : todas) {
				if (string.equals(a.getName())) {
					array.add(attractionDAO.find(a.getId()));
				}

			}
		}
		return array;
	}

	public List<String> cargarAtraccionesCompradas(User u) {
		try {
			String sql = "SELECT atracciones.nombre FROM atracciones JOIN itinerarios on itinerarios.id_atraccion=atracciones.id JOIN usuarios on itinerarios.id_usuario=usuarios.id WHERE usuarios.id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, u.getId());
			ResultSet resultados = statement.executeQuery();

			List<String> atraccionesCompradas = new ArrayList<String>();
			while (resultados.next()) {
				atraccionesCompradas.add(resultados.getString(1));
			}

			return atraccionesCompradas;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private User toUser(ResultSet userRegister) throws SQLException {
		return new User(userRegister.getInt(1), userRegister.getString(2), userRegister.getString(3),
				userRegister.getInt(4), userRegister.getDouble(5), userRegister.getString(6),
				userRegister.getBoolean(7));
	}

	@Override
	public int enable(User t) {

		return 0;
	}

}
