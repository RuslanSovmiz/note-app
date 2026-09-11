import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Note implements Serializable {

    private static int counter = 1;
    private int id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public int getId() { return id; }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static void setCounter(int counter) { Note.counter = counter; }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void updateUpdatedAt() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        if (updatedAt == null) {
            return "ID: " + getId() + "\nЗаголовок: " + getTitle() +
                    "\nДата создания: " + getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n" + getContent() + "\n-----------\n";
        } else {
            return "ID: " + getId() + "\nЗаголовок: " + getTitle() + "\nДата создания: " + getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                    "\nДата изменения: " + getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n" + getContent() + "\n-----------\n";
        }
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        createdAt = LocalDateTime.now();
        this.id = counter;
        counter++;
    }

    public Note() {

    }

}
