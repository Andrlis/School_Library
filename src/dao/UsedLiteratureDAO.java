package dao;

import bean.Literature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsedLiteratureDAO extends AbstractDAO {

    private final static String GET_BY_USER_QUERY =
            "SELECT * FROM literature WHERE id_item IN (SELECT id_item FROM used_literature WHERE id_student = ?)";
    private final static String SAVE_QUERY =
            "INSERT INTO school_library.used_literature (id_student, id_item) VALUES (?, ?)";
    private final static String REMOVE_QUERY =
            "DELETE FROM school_library.used_literature WHERE id_student = ? AND id_item = ?";


    public List<Literature> getByUser(int id) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Literature> literature = new ArrayList<Literature>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_BY_USER_QUERY);
            statement.setInt(1, id);
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

    public void save(int id_student, int id_item) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = getConnection();

            statement = connection.prepareStatement(SAVE_QUERY);

            statement.setInt(1, id_student);
            statement.setInt(2, id_item);

            statement.executeUpdate();
        }catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeDB(connection,statement);
        }
    }

    public void remove(int id_student, int id_item) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = getConnection();

            statement = connection.prepareStatement(REMOVE_QUERY);

            statement.setInt(1, id_student);
            statement.setInt(2, id_item);

            statement.executeUpdate();
        }catch (SQLException e) {
            throw new DaoException();
        } finally {
            closeDB(connection, statement);
        }
    }
}
