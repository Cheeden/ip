import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return taskList;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                taskList.add(Task.fromStorageFormat(line));
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return taskList;
    }

    public void save(ArrayList<Task> taskList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList) {
                bw.write(task.toStorageFormat());
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

}
