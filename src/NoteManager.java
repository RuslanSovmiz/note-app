import java.io.*;
import java.util.*;

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
                System.out.println(note.toString());
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
            System.out.println(foundNote);
        } else {
            System.out.println("Заметка с таким ID не найдена!");
        }
    }

    public void findInText(String findText) {
        boolean found = false;
        if(findText.trim().isEmpty()) {
            System.out.println("Введите текст для поиска");
            return;
        }
        String findTextLower = findText.toLowerCase();
        for(Note note : notes.values()) {
            String title = note.getTitle();
            String content = note.getContent();
            if(title.toLowerCase().contains(findTextLower) ||
                    content.toLowerCase().contains(findTextLower)) {
                if (!found) {
                    System.out.println("Результаты поиска: ");
                }
                found = true;
                System.out.println(note);
            }
        }
        if(!found) {
            System.out.println("По вашему запросу ничего не найдено");
        }
    }

    public void sortNotesByTitle() {
        ArrayList<Note> sortingNotes = new ArrayList<>();
        sortingNotes.addAll(notes.values());
        sortingNotes.sort(Comparator.comparing(Note::getTitle));
        for (Note note : sortingNotes) {
            System.out.println(note.toString());
        }
    }

    public void sortNotesByUpdatedAt() {
        ArrayList<Note> sortingNotes = new ArrayList<>();
        sortingNotes.addAll(notes.values());
        sortingNotes.sort(Comparator.comparing(Note::getUpdatedAt, Comparator.nullsFirst(Comparator.naturalOrder())));
        for (Note note : sortingNotes) {
            System.out.println(note.toString());
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
        NoteManager loadedManager;
        int maxId = 0;
            if (!file.exists()) {
                return new NoteManager();
            }
            try (FileInputStream fis = new FileInputStream(fileName);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                loadedManager = (NoteManager) ois.readObject();

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Ошибка при загрузке данных: " + e.getMessage());
                return new NoteManager();
            }
        for (Note note : loadedManager.notes.values()) {
            if (note.getId() > maxId) {
                maxId = note.getId();
            }
        }
        Note.setCounter(maxId + 1);
        return loadedManager;
    }

}

