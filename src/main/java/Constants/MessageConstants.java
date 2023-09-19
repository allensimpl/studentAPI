package Constants;

public class MessageConstants {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED =  "FAILED";
    public static final String FAILED_NO_ID = "Failed| No such ID exist";
    public static final String FAILED_ID_EXISTS  = "Failed| The ID Already exists";

    public static final String SUB_ALREADY_EXIST =  "Subject Already Exists";

    public static final String FAILED_TO_GET_ID = "Failed to get ID";
    public static final String ALREADY_EXISTS =  "FAILED! | It Already Exists";
    public static final String UPDATED = "Updated";
    public static final String EMAIL_ALREADY_EXISTS = "The Email Already Exists!";
    public static String successfulDeletion(Integer id){
        return "Successfully Deleted "+id;
    }

    public static String AlreadyExists(String id){
        return id+" Already Exists";
    }

}
