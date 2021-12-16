package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Attraction;
import model.AttractionType;
import persistence.AttractionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class AttractionDAOImpl implements AttractionDAO {

	List<AttractionType> attractionTypes = findAttractionTypes();

	@Override
	public List<Attraction> findAll() {
		try {
			String sql = "SELECT ATRACCIONES.ID, ATRACCIONES.NOMBRE, ATRACCIONES.COSTO, ATRACCIONES.DURACION, ATRACCIONES.CUPO, TIPO_ATRACCION.TIPO, ATRACCIONES.DESCRIPCION, ATRACCIONES.ESTADO FROM ATRACCIONES\r\n"
					+ "JOIN TIPO_ATRACCION\r\n" + "ON TIPO_ATRACCION.ID = ATRACCIONES.TIPO;";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Attraction> attractions = new LinkedList<Attraction>();

			while (resultados.next()) {
				attractions.add(toAttraction(resultados));
			}

			return attractions;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Attraction find(Integer id) {
		try {
			String sql = "SELECT ATRACCIONES.ID, ATRACCIONES.NOMBRE, ATRACCIONES.COSTO, ATRACCIONES.DURACION, ATRACCIONES.CUPO, TIPO_ATRACCION.TIPO, ATRACCIONES.DESCRIPCION, ATRACCIONES.ESTADO FROM ATRACCIONES\r\n"
					+ "JOIN TIPO_ATRACCION\r\n" + "ON TIPO_ATRACCION.ID = ATRACCIONES.TIPO WHERE ATRACCIONES.ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultados = statement.executeQuery();

			Attraction attraction = null;
			if (resultados.next()) {
				attraction = toAttraction(resultados);
			}

			return attraction;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Attraction attraction) {
		try {
			String sql = "INSERT INTO ATRACCIONES (NOMBRE, COSTO, DURACION, CUPO, TIPO, DESCRIPCION) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, attraction.getName());
			statement.setInt(2, attraction.getCost());
			statement.setDouble(3, attraction.getDuration());
			statement.setInt(4, attraction.getCapacity());
			for (AttractionType at : attractionTypes) {
				if (at.getType().equals(attraction.getAttractionType())) {
					statement.setInt(5, at.getId());
				}
			}

			statement.setString(6, attraction.getDescription());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Attraction attraction) {
		try {
			String sql = "UPDATE ATRACCIONES SET NOMBRE = ?, COSTO = ?, DURACION = ?, CUPO = ?, TIPO = ?, DESCRIPCION = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, attraction.getName());
			statement.setInt(2, attraction.getCost());
			statement.setDouble(3, attraction.getDuration());
			statement.setInt(4, attraction.getCapacity());
			for (AttractionType at : attractionTypes) {
				if (at.getType().equals(attraction.getAttractionType())) {
					statement.setInt(5, at.getId());
				}
			}
			statement.setString(6, attraction.getDescription());
			statement.setInt(7, attraction.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Attraction attraction) {
		try {
			String sql = "DELETE FROM ATRACCIONES WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, attraction.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM ATTRACTIONS";
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

	@Override
	public int enable(Attraction attraction) {
		try {
			String sql = "UPDATE ATRACCIONES SET ESTADO = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			if (attraction.getEnable()) {
				statement.setInt(i++, 0);
			} else {
				statement.setInt(i++, 1);
			}
			statement.setInt(i++, attraction.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<AttractionType> findAttractionTypes() {
		try {
			String sql = "SELECT * FROM TIPO_ATRACCION";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<AttractionType> attractionTypes = new LinkedList<AttractionType>();

			while (resultados.next()) {
				attractionTypes.add(toAttractionType(resultados));
			}

			return attractionTypes;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Attraction toAttraction(ResultSet attractionRegister) throws SQLException {
		return new Attraction(attractionRegister.getInt(1), attractionRegister.getString(2),
				attractionRegister.getInt(3), attractionRegister.getDouble(4), attractionRegister.getInt(5),
				attractionRegister.getString(6), attractionRegister.getString(7), attractionRegister.getBoolean(8));
	}

	private AttractionType toAttractionType(ResultSet resultados) throws SQLException {
		return new AttractionType(resultados.getInt(1), resultados.getString(2));
	}

	@Override
	public List<Attraction> findAttractions(Integer id) {
		return null;
	}
}
