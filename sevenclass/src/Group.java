import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupName;
    private List<Student> students;
// 在 Group 类中添加以下方法
public String getGroupName() {
    return this.groupName;  // 修改为使用 groupName 字段
}
    public Group(String groupName) {
        this.groupName = groupName;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return groupName + ": " + students; // 简化显示格式
    }
}
