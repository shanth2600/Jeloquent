package jeloquent;

/**
 * Created by shant on 4/6/16.
 */
public class Post extends Model {

    protected String table = "posts";
    protected String[] fields = {"id","body","title"};
}
