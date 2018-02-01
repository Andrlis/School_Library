package dao;

public class UsedLiteratureDAO {

    private final static String GET_BY_USER_QUERY =
            "SELECT * FROM school_library.used_literature WHERE id_user = ?";
    private final static String GET_ALL_QUERY =
            "SELECT * FROM school_library.used_literature";
    private final static String SAVE_QUERY =
            "INSERT INTO school_library.used_literature (id_student, id_item) VALUES (?, ?)";
    private final static String UPDATE_QUERY =
            "UPDATE school_library.used_literature SET id_student = ?, id_item = ? WHERE id_used = ?";
    private final static String REMOVE_QUERY =
            "DELETE FROM school_library.used_literature WHERE id_used = ?";


}
