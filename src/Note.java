import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Note implements Serializable {

    private static int counter = 1;
    private int id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;

    public int getId() { return id; }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void updateUpdatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        updatedAt = LocalDateTime.now().format(formatter);
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        createdAt = LocalDateTime.now().format(formatter);
        this.id = counter;
        counter++;
        updateUpdatedAt();
    }

    public Note() {

    }

}
