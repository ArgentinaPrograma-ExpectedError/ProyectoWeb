package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.AbsolutePromotion;
import model.Attraction;
import model.AttractionType;
import model.AxBPromotion;
import model.PercentagePromotion;
import model.Promotion;
import model.PromotionType;
import persistence.AttractionDAO;
import persistence.PromotionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;

public class PromotionDAOImpl implements PromotionDAO {

	AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
	List<Attraction> attractions = attractionDAO.findAll();
	List<AttractionType> attractionTypes = attractionDAO.findAttractionTypes();
	List<PromotionType> promotionTypes = findPromotionTypes();

	@Override
	public List<Promotion> findAll() {
		try {
			String sql = "SELECT PROMOCIONES.ID, PROMOCIONES.NOMBRE, TIPO_ATRACCION.TIPO, TIPO_PROMOCION.TIPO, PROMOCIONES.DESCRIPCION, PROMOCIONES.DESCUENTO, PROMOCIONES.PRECIO, PROMOCIONES.ESTADO, PROMOCIONES.URL FROM PROMOCIONES\r\n"
					+ "JOIN TIPO_PROMOCION\r\n" + "on PROMOCIONES.TIPO_PROMOCION=TIPO_PROMOCION.ID\r\n"
					+ "JOIN TIPO_ATRACCION\r\n" + "on PROMOCIONES.TIPO_ATRACCIONES=TIPO_ATRACCION.ID";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promotion> promociones = new LinkedList<Promotion>();

			while (resultados.next()) {
				List<Integer> idAttractions = getIdAttractions(resultados.getString(2));
				if (resultados.getString(4).equals("ABSOLUTA")) {
					promociones.add(toAbsolutePromotion(resultados, idAttractions));
				}
				if (resultados.getString(4).equals("PORCENTUAL")) {
					promociones.add(toPercentagePromotion(resultados, idAttractions));
				}
				if (resultados.getString(4).equals("AXB")) {
					promociones.add(toAxBPromotion(resultados, idAttractions));
				}
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Promotion find(Integer id) {
		try {
			String sql = "SELECT PROMOCIONES.ID, PROMOCIONES.NOMBRE, TIPO_ATRACCION.TIPO, TIPO_PROMOCION.TIPO, PROMOCIONES.DESCRIPCION, PROMOCIONES.DESCUENTO, PROMOCIONES.PRECIO, PROMOCIONES.ESTADO,PROMOCIONES.URL FROM PROMOCIONES\r\n"
					+ "JOIN TIPO_PROMOCION\r\n" + "on PROMOCIONES.TIPO_PROMOCION=TIPO_PROMOCION.ID\r\n"
					+ "JOIN TIPO_ATRACCION\r\n" + "on PROMOCIONES.TIPO_ATRACCIONES=TIPO_ATRACCION.ID\r\n"
					+ "WHERE PROMOCIONES.ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultados = statement.executeQuery();

			Promotion promotion = null;
			if (resultados.next()) {
				List<Integer> idAttractions = getIdAttractions(resultados.getString(2));
				if (resultados.getString(4).equals("ABSOLUTA")) {
					promotion = toAbsolutePromotion(resultados, idAttractions);
				}
				if (resultados.getString(4).equals("PORCENTUAL")) {
					promotion = toPercentagePromotion(resultados, idAttractions);
				}
				if (resultados.getString(4).equals("AXB")) {
					promotion = toAxBPromotion(resultados, idAttractions);
				}
			}

			return promotion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Promotion promotion) {
		try {
			String sqlA = "INSERT INTO PROMOCIONES (TIPO_PROMOCION, NOMBRE, TIPO_ATRACCIONES, DESCUENTO, PRECIO, DESCRIPCION) VALUES (?, ?, ?, ?, ?, ?)";
			String sqlB = "INSERT INTO PROMOCIONES_ATRACCIONES (ID_PROMOCION, ID_ATRACCION) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statementA = conn.prepareStatement(sqlA);
			PreparedStatement statementB = conn.prepareStatement(sqlB);

			if (promotion.getPromotionType().equals("ABSOLUTA")) {
				statementA.setInt(1, 1);
			}
			if (promotion.getPromotionType().equals("AXB")) {
				statementA.setInt(1, 2);
			}
			if (promotion.getPromotionType().equals("PORCENTUAL")) {
				statementA.setInt(1, 3);
			}
			statementA.setString(2, promotion.getName());
			if (promotion.getAttractions().get(0).getAttractionType().equals("AVENTURA")) {
				statementA.setInt(3, 1);
			}
			if (promotion.getAttractions().get(0).getAttractionType().equals("DEGUSTACION")) {
				statementA.setInt(3, 2);
			}
			if (promotion.getAttractions().get(0).getAttractionType().equals("PAISAJE")) {
				statementA.setInt(3, 3);
			}

			if (promotion.getPromotionType().equals("ABSOLUTA")) {
				statementA.setInt(5, ((AbsolutePromotion) promotion).getCost());
			}
			if (promotion.getPromotionType().equals("PORCENTUAL")) {
				statementA.setInt(4, ((PercentagePromotion) promotion).getDiscount());
			}

			statementA.setString(6, promotion.getDescription());

			int rowsA = statementA.executeUpdate();

			for (Attraction a : promotion.getAttractions()) {
				for (Promotion p : findAll()) {
					if (p.getName().equals(promotion.getName()) && promotion.getAttractions().contains(a)) {
						statementB.setInt(1, p.getId());
						statementB.setInt(2, a.getId());
						statementB.executeUpdate();
					}
				}
			}

			return rowsA;
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
	public int update(Promotion t) {
		try {
			String sqlA = "UPDATE PROMOCIONES SET NOMBRE = ?, DESCRIPCION = ? WHERE ID = ?";
			String sqlB = "UPDATE PROMOCIONES SET PRECIO = ? WHERE ID = ?";
			String sqlC = "UPDATE PROMOCIONES SET DESCUENTO = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statementA = conn.prepareStatement(sqlA);
			PreparedStatement statementB = conn.prepareStatement(sqlB);
			PreparedStatement statementC = conn.prepareStatement(sqlC);

			statementA.setString(1, t.getName());
			statementA.setString(2, t.getDescription());
			statementA.setInt(3, t.getId());
			if (t.getPromotionType().equals("ABSOLUTA")) {
				statementB.setInt(1, ((AbsolutePromotion) t).getCost());
				statementB.setInt(2, t.getId());
				statementB.executeUpdate();
			}
			if (t.getPromotionType().equals("PORCENTUAL")) {
				statementC.setInt(1, ((PercentagePromotion) t).getCost());
				statementC.setInt(2, t.getId());
				statementC.executeUpdate();
			}

			int rowsA = statementA.executeUpdate();

			return rowsA;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Promotion Promotion) {
		try {
			String sqlA = "DELETE FROM PROMOCIONES_ATRACCIONES WHERE ID_PROMOCION = ?";
			String sqlB = "DELETE FROM PROMOCIONES WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statementA = conn.prepareStatement(sqlA);
			PreparedStatement statementB = conn.prepareStatement(sqlB);
			statementA.setInt(1, Promotion.getId());
			statementB.setInt(1, Promotion.getId());
			int rowsA = statementA.executeUpdate();
			int rowsB = statementB.executeUpdate();

			return rowsA + rowsB;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int enable(Promotion promotion) {
		try {
			String sql = "UPDATE PROMOCIONES SET ESTADO = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			if (promotion.getEnable()) {
				statement.setInt(i++, 0);
			} else {
				statement.setInt(i++, 1);
			}
			statement.setInt(i++, promotion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<PromotionType> findPromotionTypes() {
		try {
			String sql = "SELECT * FROM TIPO_PROMOCION";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<PromotionType> promotionTypes = new LinkedList<PromotionType>();

			while (resultados.next()) {
				promotionTypes.add(toPromotionType(resultados));
			}

			return promotionTypes;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private List<Integer> getIdAttractions(String name) {
		try {
			List<Integer> id_atracciones = new LinkedList<Integer>();
			String sql = "SELECT ATRACCIONES.ID FROM ATRACCIONES JOIN PROMOCIONES_ATRACCIONES ON PROMOCIONES_ATRACCIONES.ID_ATRACCION=ATRACCIONES.ID JOIN PROMOCIONES ON PROMOCIONES_ATRACCIONES.ID_PROMOCION=PROMOCIONES.ID WHERE PROMOCIONES.NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet resultados = statement.executeQuery();

			while (resultados.next()) {
				id_atracciones.add(resultados.getInt(1));
			}

			return id_atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promotion toAbsolutePromotion(ResultSet resultados, List<Integer> atracciones_promo) throws SQLException {
		List<Attraction> atraccionesPromo = new LinkedList<Attraction>();
		for (Integer i : atracciones_promo) {
			for (Attraction a : attractions) {
				if (i.equals(a.getId())) {
					atraccionesPromo.add(a);
				}
			}
		}
		return new AbsolutePromotion(resultados.getInt(1), resultados.getString(2), resultados.getString(3),
				resultados.getString(4), atraccionesPromo, resultados.getString(5), resultados.getBoolean(8),
				resultados.getInt(7),resultados.getString(9));
	}

	private Promotion toAxBPromotion(ResultSet resultados, List<Integer> atracciones_promo) throws SQLException {
		List<Attraction> atraccionesPromo = new LinkedList<Attraction>();
		for (Integer i : atracciones_promo) {
			for (Attraction a : attractions) {
				if (i.equals(a.getId())) {
					atraccionesPromo.add(a);
				}
			}
		}
		return new AxBPromotion(resultados.getInt(1), resultados.getString(2), resultados.getString(3),
				resultados.getString(4), atraccionesPromo, resultados.getString(5), resultados.getBoolean(8),resultados.getString(9));
	}

	private Promotion toPercentagePromotion(ResultSet resultados, List<Integer> atracciones_promo) throws SQLException {
		List<Attraction> atraccionesPromo = new LinkedList<Attraction>();
		for (Integer i : atracciones_promo) {
			for (Attraction a : attractions) {
				if (i.equals(a.getId())) {
					atraccionesPromo.add(a);
				}
			}
		}
		return new PercentagePromotion(resultados.getInt(1), resultados.getString(2), resultados.getString(3),
				resultados.getString(4), atraccionesPromo, resultados.getString(5), resultados.getBoolean(8),
				resultados.getInt(6),resultados.getString(9));
	}

	private PromotionType toPromotionType(ResultSet resultados) throws SQLException {
		return new PromotionType(resultados.getInt(1), resultados.getString(2));
	}

}