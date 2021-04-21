package msql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySql {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	/*
	 * CREATE TABLE my_data ( my_id INT PRIMARY KEY AUTO_INCREMENT, my_name
	 * VARCHAR(40) NOT NULL, my_price DOUBLE NOT NULL, my_quantity INTEGER NOT NULL
	 * );
	 */
	
	
	public MySql() throws SQLException {
		try {
			String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
			connect = DriverManager.getConnection(url, "root", "admin" + "");
			statement = connect.createStatement();
			System.out.println("Server Runing");
		} catch (SQLException e) {
			System.out.println("Error: Failed to initialise DB Connection");
		}
	}
	
	// CREATE
	public boolean insertData(Data c) throws SQLException {
		boolean insertSucessfull = true;
		try {
			preparedStatement = connect.prepareStatement("INSERT INTO my_data values (default, ?, ?, ?);");
			preparedStatement.setString(1, c.getName());
			preparedStatement.setDouble(2, c.getPrice());
			preparedStatement.setInt(3, c.getQuantity());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			insertSucessfull = false;
		}
		return insertSucessfull;
	}
	
	//READ
	public ResultSet printData() throws SQLException {
		try {
			resultSet = statement.executeQuery("SELECT * FROM my_data;");
		} catch (SQLException e) {
			resultSet = null;
		}
		return resultSet;
	}
	
	// UPDATE
	public boolean updateData(Data c) throws SQLException {
		boolean insertSucessfull = true;
		try {
			preparedStatement = connect.prepareStatement("UPDATE my_data SET my_name = ?,my_price = ?,my_quantity = ?  WHERE my_id = ?;");
			preparedStatement.setString(1, c.getName());
			preparedStatement.setDouble(2, c.getPrice());
			preparedStatement.setInt(3, c.getQuantity());
			preparedStatement.setInt(4, c.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			insertSucessfull = false;
		}
		return insertSucessfull;
	}
	
	// DELETE
	public boolean deleteData(Data c) throws SQLException {
		boolean deleteSucessfull = true;
		try {
			preparedStatement = connect.prepareStatement("DELETE from my_data WHERE my_id = ?;");
			preparedStatement.setInt(1, c.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			deleteSucessfull = false;
		}
		return deleteSucessfull;
	}
	
	// Close Connection
	public void closeSQL() throws SQLException {
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
