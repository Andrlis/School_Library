package dao;

import bean.Literature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LiteratureDAO extends AbstractDAO {

    private final static String GET_AVAILABLE_QUERY =
            "SELECT * FROM school_library.literature WHERE numOfAvailable > 0";
    private final static String GET_ALL_QUERY =
            "SELECT * FROM school_library.literature";
    private final static String SAVE_QUERY =
            "INSERT INTO school_library.literature (item_type, item_name, author, numOfAvailable) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_QUERY =
            "UPDATE school_library.literature SET item_type = ?, item_name = ?, author = ?, numOfAvailable = ? WHERE id_item = ?";
    private final static String REMOVE_QUERY =
            "DELETE FROM school_library.literature WHERE id_item = ?";

    public Literature getLiteratureById(int id) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Literature item = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_AVAILABLE_QUERY);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                item = new Literature();

                item.setId(resultSet.getInt("id_item"));
                item.setType(resultSet.getString("item_type"));
                item.setName(resultSet.getString("item_name"));
                item.setAuthor(resultSet.getString("author"));
                item.setNumOfAvailable(resultSet.getInt("numOfAvailable"));

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeDB(connection, statement, resultSet);
        }
        return item;
    }

    public List<Literature> getAllLiterature() throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Literature> literature = new ArrayList<Literature>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_ALL_QUERY);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Literature item = new Literature();

                item.setId(resultSet.getInt("id_item"));
                item.setType(resultSet.getString("item_type"));
                item.setName(resultSet.getString("item_name"));
                item.setAuthor(resultSet.getString("author"));
                item.setNumOfAvailable(resultSet.getInt("numOfAvailable"));

                literature.add(item);
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            closeDB(connection, statement, resultSet);
        }

        return literature;
    }

    public void saveLiterature(Literature item) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = getConnection();

            statement = connection.prepareStatement(SAVE_QUERY);

            statement.setString(1, item.getType());
            statement.setString(2, item.getName());
            statement.setString(3, item.getAuthor());
            statement.setInt(4, item.getNumOfAvailable());

            statement.executeUpdate();
        }catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeDB(connection,statement);
        }
    }

    public void updateLiterature(int id, Literature item) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = getConnection();

            statement = connection.prepareStatement(UPDATE_QUERY);

            statement.setString(1, item.getType());
            statement.setString(2, item.getName());
            statement.setString(3, item.getAuthor());
            statement.setBoolean(4, item.isAvailable());

            statement.executeUpdate();
        }catch (SQLException e) {
            throw new DaoException();
        } finally {
            closeDB(connection, statement);
        }
    }

    public void removeLiterature(int id) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = getConnection();

            statement = connection.prepareStatement(REMOVE_QUERY);

            statement.setInt(1, id);

            statement.executeUpdate();
        }catch (SQLException e) {
            throw new DaoException();
        } finally {
            closeDB(connection, statement);
        }
    }
}
