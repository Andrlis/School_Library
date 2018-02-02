package dao;

import bean.Student;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends AbstractDAO {

    private final static String GET_BY_ID_QUERY =
            "SELECT * FROM school_library.students WHERE id_student = ?";
    public final static String GET_ALL_QUERY =
            "SELECT * FROM school_library.students";
    public final static String GET_FIRST_LIST =
            "SELECT * FROM school_library.students WHERE number_of_books > 1 AND number_of_books <= 5 ORDER BY number_of_books";
    public final static String GET_SECOND_LIST =
            "SELECT * FROM school_library.students WHERE number_of_books >= 3 AND number_of_books <= 6 ORDER BY date_of_birth, number_of_books DESC";
    private final static String SAVE_QUERY =
            "INSERT INTO school_library.students (student_name, date_of_birth, number_of_books) VALUES (?, ?, ?)";
    private final static String UPDATE_QUERY =
            "UPDATE school_library.students SET student_name = ?, date_of_birth = ?, number_of_books = ? WHERE id_student = ?";
    private final static String REMOVE_QUERY =
            "DELETE FROM school_library.students WHERE id_student = ?";


    public Student getStudentById(int id) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_BY_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                student = new Student();

                student.setId(resultSet.getInt("id_student"));
                student.setName(resultSet.getString("student_name"));
                student.setDateOfBirth(resultSet.getDate("date_of_birth"));
                student.setNumberOfBooks(resultSet.getInt("number_of_books"));

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeDB(connection, statement, resultSet);
        }
        return student;
    }

    public List<Student> getStudents(String query) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Student> students = new ArrayList<Student>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Student student = new Student();

                student.setId(resultSet.getInt("id_student"));
                student.setName(resultSet.getString("student_name"));
                student.setDateOfBirth(resultSet.getDate("date_of_birth"));
                student.setNumberOfBooks(resultSet.getInt("number_of_books"));

                students.add(student);
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            closeDB(connection, statement, resultSet);
        }

        return students;
    }

    public void saveStudent(Student student) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = getConnection();

            statement = connection.prepareStatement(SAVE_QUERY);

            statement.setString(1, student.getName());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            statement.setString(2, simpleDateFormat.format(student.getDateOfBirth()));
            statement.setInt(3, student.getNumberOfBooks());

            statement.executeUpdate();
        }catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeDB(connection,statement);
        }
    }

    public void updateStudent(int id, Student student) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = getConnection();

            statement = connection.prepareStatement(UPDATE_QUERY);

            statement.setString(1, student.getName());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            statement.setString(2, simpleDateFormat.format(student.getDateOfBirth()));
            statement.setInt(3, student.getNumberOfBooks());
            statement.setInt(4, id);

            statement.executeUpdate();
        }catch (SQLException e) {
            throw new DaoException();
        } finally {
            closeDB(connection, statement);
        }
    }

    public void removeStudent(int id) throws DaoException{
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
