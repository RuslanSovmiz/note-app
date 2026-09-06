import java.io.*;
import java.util.HashMap;

public class NoteManager implements Serializable {
    HashMap<Integer, Note> notes = new HashMap<>();


    public void addNote(String title, String content) {

        if (title.trim().isEmpty()) {
            System.out.println("Ошибка: заголовок не может быть пустым!");
            return;
        }
        if (content.trim().isEmpty()) {
            System.out.println("Ошибка: заметка не может быть пустой!");
            return;
        } else {
            Note newNote = new Note(title, content);
            notes.put(newNote.getId(), newNote);
            System.out.println("Заметка успешно создана!");
            System.out.println("ID: " + newNote.getId());
        }

    }

    public void showAllNotes() {
        if (notes.isEmpty()) {
            System.out.println("Заметок пока нет!");
        } else {
            for (Note note : notes.values()) {
                System.out.println("ID: " + note.getId() + "\nЗаголовок: " + note.getTitle() +
                        "\nДата создания: " + note.getCreatedAt() + "\n" + note.getContent() + "\n-----------\n");
            }
        }
    }

    public void editNote(int id, String title, String content) {
        Note foundNote = notes.get(id);
        if (foundNote == null) {
            System.out.println("Заметка с таким ID не найдена!");
            return;
        }
        if (!title.trim().isEmpty()) {
            foundNote.setTitle(title);
        }
        if (!content.trim().isEmpty()) {
            foundNote.setContent(content);
        }
        foundNote.updateUpdatedAt();
        System.out.println("Заметка успешно обновлена!");
    }

    public void deleteNote(int id) {
        Note foundNote = notes.get(id);
        if (foundNote == null) {
            System.out.println("Заметка с таким ID не найдена!");
            return;
        } else {
            notes.remove(id);
            System.out.println("Заметка успешно удалена!");
            return;
        }
    }

    public void showNoteById(int id) {
        Note foundNote = notes.get(id);
        if (foundNote != null) {
            System.out.println("ID: " + foundNote.getId());
            System.out.println("Заголовок : " + foundNote.getTitle());
            System.out.println("Текст: " + foundNote.getContent());
            System.out.println("Дата создания: " + foundNote.getCreatedAt());
        } else {
            System.out.println("Заметка с таким ID не найдена!");
        }
    }


    public void saveToFile(String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
            System.out.println("Данные сохранены в файл: " + fileName);
        }
    }

    public static NoteManager loadFromFile(String fileName) {
        File file = new File(fileName);
            if (!file.exists()) {
                return new NoteManager();
            }
            try (FileInputStream fis = new FileInputStream(fileName);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                return (NoteManager) ois.readObject();

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Ошибка при загрузке данных: " + e.getMessage());
                return new NoteManager();
            }
    }

}