import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class RandomSelector extends JFrame {
    private SchoolClass schoolClass;
    private JTextArea displayArea;
    private JButton randomGroupBtn;
    private JButton randomStudentInGroupBtn;
    private JButton randomStudentInClassBtn;
    private JButton clearBtn;  // 新增清除按钮
    private JButton addGroupBtn;  // 新增添加小组按钮
    private JButton addStudentBtn;  // 新增添加学生按钮
    private JButton showClassListBtn;  // 新增显示班级列表按钮

    public RandomSelector() {
        setTitle("随机抽取系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeData();
        createComponents();
        setupLayout();
    }

    private void initializeData() {
        schoolClass = new SchoolClass("23级8班");

        Group group1 = new Group("第一小组");
        Group group2 = new Group("第二小组");

        Student student1 = new Student("刘志博", 1);
        Student student2 = new Student("梁均涵", 2);
        Student student3 = new Student("徐佳浩", 3);
        Student student4 = new Student("刘同泽", 4);

        group1.addStudent(student1);
        group1.addStudent(student2);
        group2.addStudent(student3);
        group2.addStudent(student4);

        schoolClass.addGroup(group1);
        schoolClass.addGroup(group2);

        schoolClass.addStudent(student1);
        schoolClass.addStudent(student2);
        schoolClass.addStudent(student3);
        schoolClass.addStudent(student4);
    }

    private void createComponents() {
        Font font = new Font("微软雅黑", Font.PLAIN, 16);

        displayArea = new JTextArea(15, 40);
        displayArea.setEditable(false);
        displayArea.setFont(font);
        displayArea.setMargin(new Insets(10, 10, 10, 10));

        randomGroupBtn = new JButton("随机抽取小组");
        randomStudentInGroupBtn = new JButton("随机抽取小组中的学生");
        randomStudentInClassBtn = new JButton("随机抽取班级学生");
        clearBtn = new JButton("清除显示");  // 新增清除按钮
        addGroupBtn = new JButton("添加小组");  // 新增添加小组按钮
        addStudentBtn = new JButton("添加学生");  // 新增添加学生按钮
        showClassListBtn = new JButton("显示班级列表");  // 新增显示班级列表按钮

        // 设置按钮字体
        randomGroupBtn.setFont(font);
        randomStudentInGroupBtn.setFont(font);
        randomStudentInClassBtn.setFont(font);
        clearBtn.setFont(font);
        addGroupBtn.setFont(font);
        addStudentBtn.setFont(font);
        showClassListBtn.setFont(font);

        // 添加按钮事件
        randomGroupBtn.addActionListener(e -> randomSelectGroup());
        randomStudentInGroupBtn.addActionListener(e -> randomSelectStudentFromGroup());
        randomStudentInClassBtn.addActionListener(e -> randomSelectStudentFromClass());
        clearBtn.addActionListener(e -> displayArea.setText(""));  // 清除文本区域内容
        addGroupBtn.addActionListener(e -> addGroup());
        addStudentBtn.addActionListener(e -> addStudent());
        showClassListBtn.addActionListener(e -> showClassList());  // 添加显示班级列表事件

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.add(randomGroupBtn);
        buttonPanel.add(randomStudentInGroupBtn);
        buttonPanel.add(randomStudentInClassBtn);
        buttonPanel.add(clearBtn);  // 添加清除按钮
        buttonPanel.add(addGroupBtn);  // 添加小组按钮
        buttonPanel.add(addStudentBtn);  // 添加学生按钮
        buttonPanel.add(showClassListBtn);  // 添加显示班级列表按钮

        // 创建标题面板
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel(schoolClass.getClassName() + " 随机抽取系统");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // 添加组件到窗口
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.add(randomGroupBtn);
        buttonPanel.add(randomStudentInGroupBtn);
        buttonPanel.add(randomStudentInClassBtn);
        buttonPanel.add(clearBtn);  // 添加清除按钮
        buttonPanel.add(addGroupBtn);  // 添加小组按钮
        buttonPanel.add(addStudentBtn);  // 添加学生按钮
        buttonPanel.add(showClassListBtn);  // 添加显示班级列表按钮

        // 创建标题面板
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel(schoolClass.getClassName() + " 随机抽取系统");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // 添加组件到窗口
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void randomSelectGroup() {
        if (schoolClass.getGroups().isEmpty()) {
            appendToDisplay("警告：没有可用的小组！\n");
            return;
        }
        Random random = new Random();
        Group randomGroup = schoolClass.getGroups().get(random.nextInt(schoolClass.getGroups().size()));
        appendToDisplay("随抽取的小组：\n" + randomGroup + "\n");
    }

    private void randomSelectStudentFromGroup() {
        // 选择班级
        String[] classNames = {schoolClass.getClassName()}; // 目前只支持一个班级
        String selectedClass = (String) JOptionPane.showInputDialog(this, "选择班级:", "班级选择", JOptionPane.QUESTION_MESSAGE, null, classNames, classNames[0]);
        
        if (selectedClass == null) return; // 用户取消选择

        // 选择小组
        List<Group> groups = schoolClass.getGroups();
        String[] groupNames = groups.stream().map(Group::getGroupName).toArray(String[]::new);
        String selectedGroup = (String) JOptionPane.showInputDialog(this, "选择小组:", "小组选择", JOptionPane.QUESTION_MESSAGE, null, groupNames, groupNames[0]);
        
        if (selectedGroup == null) return; // 用户取消选择

        // 查找选中的小组
        Group randomGroup = groups.stream().filter(g -> g.getGroupName().equals(selectedGroup)).findFirst().orElse(null);
        
        if (randomGroup == null || randomGroup.getStudents().isEmpty()) {
            appendToDisplay("警告：选中的小组没有学生！\n");
            return;
        }
        
        Random random = new Random();
        Student randomStudent = randomGroup.getStudents().get(random.nextInt(randomGroup.getStudents().size()));
        appendToDisplay("从" + randomGroup + "随机抽取的学生：\n" + randomStudent + "\n");
    }

    private void randomSelectStudentFromClass() {
        if (schoolClass.getStudents().isEmpty()) {
            appendToDisplay("警告：班级中没有学生！\n");
            return;
        }
        Random random = new Random();
        Student randomStudent = schoolClass.getStudents().get(random.nextInt(schoolClass.getStudents().size()));
        appendToDisplay("从班级随机抽取的学生：\n" + randomStudent + "\n");
    }

    private void appendToDisplay(String text) {
        displayArea.append(text + "\n");
        displayArea.setCaretPosition(displayArea.getDocument().getLength());
    }

    // 新增添加小组的方法
    private void addGroup() {
        String groupName = JOptionPane.showInputDialog(this, "请输入小组名称:");
        if (groupName != null && !groupName.trim().isEmpty()) {
            Group newGroup = new Group(groupName);
            schoolClass.addGroup(newGroup);
            appendToDisplay("小组 " + groupName + " 已添加。\n");
        }
    }

    // 新增添加学生的方法
    private void addStudent() {
        String studentName = JOptionPane.showInputDialog(this, "请输入学生姓名:");
        String studentIdStr = JOptionPane.showInputDialog(this, "请输入学生ID:");
        if (studentName != null && !studentName.trim().isEmpty() && studentIdStr != null) {
            int studentId = Integer.parseInt(studentIdStr);
            
            // 选择添加方式
            String[] options = {"添加到已有小组", "创建新小组并添加"};
            int choice = JOptionPane.showOptionDialog(this, "选择添加方式:", "添加学生",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            
            if (choice == 0) { // 添加到已有小组
                // 选择小组
                List<Group> groups = schoolClass.getGroups();
                String[] groupNames = groups.stream().map(Group::getGroupName).toArray(String[]::new);
                String selectedGroup = (String) JOptionPane.showInputDialog(this, "选择小组:", "小组选择", JOptionPane.QUESTION_MESSAGE, null, groupNames, groupNames[0]);
                
                if (selectedGroup == null) return; // 用户取消选择

                // 查找选中的小组
                Group groupToAdd = groups.stream().filter(g -> g.getGroupName().equals(selectedGroup)).findFirst().orElse(null);
                
                if (groupToAdd != null) {
                    Student newStudent = new Student(studentName, studentId);
                    groupToAdd.addStudent(newStudent); // 将学生添加到选中的小组
                    schoolClass.addStudent(newStudent); // 也将学生添加到班级
                    appendToDisplay("学生 " + studentName + " 已添加到小组 " + selectedGroup + "。\n");
                }
            } else if (choice == 1) { // 创建新小组并添加
                String newGroupName = JOptionPane.showInputDialog(this, "请输入新小组名称:");
                if (newGroupName != null && !newGroupName.trim().isEmpty()) {
                    Group newGroup = new Group(newGroupName);
                    newGroup.addStudent(new Student(studentName, studentId)); // 将学生添加到新小组
                    schoolClass.addGroup(newGroup); // 将新小组添加到班级
                    schoolClass.addStudent(newGroup.getStudents().get(0)); // 也将学生添加到班级
                    appendToDisplay("学生 " + studentName + " 已添加到新小组 " + newGroupName + "。\n");
                }
            }
        }
    }

    // 新增显示班级列表的方法
    private void showClassList() {
        StringBuilder classList = new StringBuilder("班级列表:\n");
        for (Group group : schoolClass.getGroups()) {
            classList.append(group.getGroupName()).append("\n");
        }
        appendToDisplay(classList.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RandomSelector().setVisible(true);
        });
    }
}
