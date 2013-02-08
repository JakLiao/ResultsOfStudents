
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainMeum extends JFrame implements ActionListener, Serializable {

    final int CLASS_NUMBER = 50;
    @SuppressWarnings("unchecked")
    private JButton jbtAdd = new JButton("��Ӱ༶��Ϣ");
    private JButton jbtCheck = new JButton("ѧ���ɼ���ѯ");
    private JButton jbtCourse = new JButton("��ӿγ���Ϣ");
    private JButton jbtCheckCourse = new JButton("�γ���Ϣ��ѯ");
    private JButton jbtMark = new JButton("�༶�ɼ���ѯ");
    private JButton jbtQuit = new JButton("�˳�");
    private JButton jbtCheckClass = new JButton("�༶��Ϣ��ѯ");
	private JButton jbtMarkAdd = new JButton("�༶�ɼ�¼��");
    private Font font = new Font("SansSerif", Font.BOLD, 18);
    public static ArrayList<String> classname;
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    MainMeum() {
        jbtAdd.setFont(font);
        jbtCheck.setFont(font);
        jbtCourse.setFont(font);
        jbtCheckCourse.setFont(font);
        jbtMark.setFont(font);
        jbtQuit.setFont(font);
        jbtCheckClass.setFont(font);
		jbtMarkAdd.setFont(font);
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 2, 20, 15));
        p1.add(jbtAdd);
        p1.add(jbtCheckClass);

        p1.add(jbtCourse);
        p1.add(jbtCheckCourse);
        p1.add(jbtCheck);
        p1.add(jbtMark);
		p1.add(jbtMarkAdd);
        p1.add(jbtQuit);

        getContentPane().add(p1);

        jbtAdd.addActionListener(this);
        jbtCheck.addActionListener(this);
        jbtCheckCourse.addActionListener(this);
        jbtCourse.addActionListener(this);
        jbtMark.addActionListener(this);
        jbtQuit.addActionListener(this);
		jbtMarkAdd.addActionListener(this);

        jbtCheckClass.addActionListener(this);


        try {
            File name = new File("Course.dat");
            if (!name.exists()) {
                ArrayList<RecordCourse> all = new ArrayList<RecordCourse>();

                ObjectOutputStream output =
                        new ObjectOutputStream(new FileOutputStream("Course.dat"));
                output.writeObject(all);
                output.close();
            }
            File name2 = new File("classname.dat");
            if (!name2.exists()) {
                classname = new ArrayList<String>();
                ObjectOutputStream output2 =
                        new ObjectOutputStream(new FileOutputStream("classname.dat"));
                output2.writeObject(classname);
                output2.close();
            }
            if (name2.exists()) {
                ObjectInputStream input = new ObjectInputStream(
                        new FileInputStream("classname.dat"));
                classname = (ArrayList<String>) (input.readObject());
                input.close();
                for (int i = 0; i < classname.size(); i++) {
                    System.out.println(classname.get(i));
                }

            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        }
    }

    public void actionPerformed(ActionEvent e) {
        int x = 0;
        int y = 0;
		try{
		 ArrayList<RecordCourse> courseNum;
         ObjectInputStream input = new ObjectInputStream(
                        new FileInputStream("Course.dat"));
                courseNum = (ArrayList<RecordCourse>) input.readObject();
                input.close();
		ArrayList<String> className;
                ObjectInputStream input2 = new ObjectInputStream(
                        new FileInputStream("classname.dat"));
                className = (ArrayList<String>) (input2.readObject());
                input2.close();
		//��������༶��Ϣ�Ľ���
        if (e.getSource() == jbtAdd) {
		    if(courseNum.size() != 0){
            ClassInfor test = new ClassInfor();
            test.setSize(300,300);
            x = (screenSize.width - test.getWidth()) / 2;
            y = (screenSize.height - test.getHeight()) / 2;
            test.setLocation(x, y);
            test.setTitle("Test");
            test.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            test.setVisible(true);
			}
			else JOptionPane.showMessageDialog(null,"��û�г�ʼ���γ���Ϣ�����ʼ����Ϣ");

        }
		//�����ѯѧ����Ϣ�Ľ���
        if (e.getSource() == jbtCheck) {
            try {
                boolean find = false;
                
                System.out.print(className.size());
                if (className.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ϵͳ�����ڻ�û�а༶����Ϣ�����ʼ����Ϣ");
                } else {
                    String studentID = JOptionPane.showInputDialog(null, "������ѧ����ѧ��");
                    StringBuffer filename;
                    String file;
                    int i = 0;
                    for (i = 0; i < className.size(); i++) {
                        filename = new StringBuffer(className.get(i));
                        filename.append(".dat");
                        file = filename.toString();
                        ObjectInputStream input3 = new ObjectInputStream(
                                new FileInputStream(file));
                        RecordClassInfor classInfor = (RecordClassInfor)(input3.readObject());
						input3.close();
                        RecordStudent s = classInfor.getStudent();
                        String[] ID = s.getID();
                        int j = 0;
                        for (j = 0; j < classInfor.getStudentNum(); j++) {
                            int k = 0;
                            String[] course = s.getCourse();
			
                            if (studentID.compareTo(ID[j]) == 0) {
                                find = true;
                                String coursename = JOptionPane.showInputDialog(null, "������Ҫ��ѯ�Ŀγ�");
                                for (k = 0; k < course.length; k++) {
                                    if (coursename.compareTo(course[k]) == 0) {
                                        //�ҵ��γ�
                                        double[][] allScore = s.getScore();
                                        StudentMark mark = new StudentMark(s, j, k);
                                        mark.setSize(300,300);
                                        x = (screenSize.width - mark.getWidth()) / 2;
                                        y = (screenSize.height - mark.getHeight()) / 2;
                                        mark.setLocation(x, y);
                                        mark.setTitle("ѧ�����Ƴɼ���");
                                        mark.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                                        mark.setVisible(true);
                                        break;
                                    }
                                }

                            }
                            if (k >= course.length) {
                                JOptionPane.showMessageDialog(null, "��ѧ����ѧ�����޸��ſγ�");
                                break;
                            }
                        }

                    }
                    if (!find) {
                        JOptionPane.showMessageDialog(null, "���޴���");
                    }
                }
                
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            }


        }
       //���뵽��ѯ�༶�ɼ��Ľ���
        if (e.getSource() == jbtMark) {
		    if(!className.isEmpty()){
            int courseID = 0;
            String major = JOptionPane.showInputDialog(null, "������רҵ");
            String grade = JOptionPane.showInputDialog(null,"�������꼶");
			String classname = JOptionPane.showInputDialog(null,"������༶");
			StringBuilder temp = new StringBuilder(major.trim());
			temp.append(grade.trim());
			temp.append(classname.trim());
			String name = temp.toString();
            temp.append(".dat");
            String name1 = temp.toString();
            File file = new File(name1);
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "���޴˰࣬��������");
            } else {
                try {
                    ObjectInputStream input4 = new ObjectInputStream(
                            new FileInputStream(file));
                    RecordClassInfor classInfor = (RecordClassInfor) (input4.readObject());
                    input4.close();
                    String[] courses = classInfor.getCourse();
                    int i = 0;
                    String courseName = JOptionPane.showInputDialog(null, "�������ѯ�Ŀγ�");
                    while (i < courses.length) {
                        if ((courseName.compareTo(courses[i])) == 0) {
                            courseID = i;
                            break;
                        }
                        i++;
                    }
                    if (i >= courses.length) {
                        JOptionPane.showMessageDialog(null, "�ð༶�޴˿γ�");
                    } else {
                        ClassMark frame = new ClassMark(classInfor, courseID, name);
                        frame.setSize(750, 650);
                        x = (screenSize.width - frame.getWidth()) / 2;
                        y = (screenSize.height - frame.getHeight()) / 2;
                        frame.setLocation(x, y);
                        frame.setResizable(false);
                        frame.setTitle("�ɼ�����");
                        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        frame.setVisible(true);
                    }
                } catch (IOException ex) {
                    System.out.println("Error: " + ex);
                    System.exit(0);
                } catch (ClassNotFoundException ex) {
                    System.out.println("Error: " + ex);
                    System.exit(0);
                }
            }
		}
		else JOptionPane.showMessageDialog(null,"��δ����༶��Ϣ�����ȳ�ʼ����Ϣ");
        }
        //���뵽��ӿγ̵Ľ���
        if (e.getSource() == jbtCourse) {
            Course course = new Course();
            course.setSize(300,300);
            x = (screenSize.width - course.getWidth()) / 2;
            y = (screenSize.height - course.getHeight()) / 2;
            course.setLocation(x, y);
            course.setTitle("��ӿγ���Ϣ");
            course.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            course.setVisible(true);

        }
		//���뵽��ѯ���пγ̵Ľ���
        if (e.getSource() == jbtCheckCourse) {
		    if(!courseNum.isEmpty()){
			String check = JOptionPane.showInputDialog(null, "������Ҫ��ѯ�Ŀγ̵�����");
            int i = 0;
            while (i < courseNum.size()) {
                RecordCourse c = courseNum.get(i);
                if (check.compareTo(c.getName()) == 0) {
                CheckCourse course = new CheckCourse(c);
                course.setSize(300,300);
                x = (screenSize.width - course.getWidth()) / 2;
                y = (screenSize.height - course.getHeight()) / 2;
                course.setLocation(x, y);
                course.setTitle("Test");
                course.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                course.setVisible(true);
				break;
				}
				 i++;
            }
            if (i >= courseNum.size()) {
                JOptionPane.showMessageDialog(null, "���޴˿γ�");
                
            }
		}	
			else JOptionPane.showMessageDialog(null,"��δ��ʼ���γ���Ϣ�����ȳ�ʼ��");
        }

        if (e.getSource() == jbtQuit) {
            try {
                ObjectOutputStream output =
                        new ObjectOutputStream(new FileOutputStream("classname.dat"));
                output.writeObject(MainMeum.classname);
                output.close();
                this.dispose();
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            }
        }
        if (e.getSource() == jbtCheckClass) {
		    if(!className.isEmpty()){
            CheckClass frame = new CheckClass();
            frame.setSize(345, 345);
            x = (screenSize.width - frame.getWidth()) / 2;
            y = (screenSize.height - frame.getHeight()) / 2;
            frame.setLocation(x, y);
            frame.setTitle("�༶��ѯ");
            frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
			}
			else JOptionPane.showMessageDialog(null,"��δ��ʼ���༶��Ϣ��������Ӱ༶��Ϣ");
        }
		
		if(e.getSource() == jbtMarkAdd){
		    MarkInput frame = new MarkInput();
			frame.setSize(345, 345);
            x = (screenSize.width - frame.getWidth()) / 2;
            y = (screenSize.height - frame.getHeight()) / 2;
            frame.setLocation(x, y);
            frame.setTitle("�ɼ�¼��");
            frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
		}
	} catch (IOException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            }
    }

    public static void main(String[] args) {

        MainMeum frame = new MainMeum();
        frame.setSize(350, 300);
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        frame.setTitle("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

//����༶��Ϣ
class ClassInfor extends JDialog implements ActionListener, Comparable, ListSelectionListener, Serializable {

    private String major;
    private String grade;
    private String classNum;
    private int studentNum;
    private int courseNum;
    private String[] course;
    private String[] courseSelect;
    private Student student;
    static int countS = 0;
    private JButton jbtAdd = new JButton("��һ��");
    private JButton jbtCancel = new JButton("����");
    private JButton jbtBack = new JButton("����");
    private JButton jbtAllOK = new JButton("���");
    static boolean finish = false;
	boolean isNull = true;
	
    //Text field
    //List
    private JList jlist;
    private JTextField jtfMajor = new JTextField(10);
    private JTextField jtfGrade = new JTextField(10);
    private JTextField jtfClass = new JTextField(10);
    private JTextField jtfStuNum = new JTextField(10);
    

    public ClassInfor() {
        try {
		    
            ArrayList<RecordCourse> all;
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream("Course.dat"));

            all = (ArrayList<RecordCourse>) (input.readObject());
            input.close();
            int i = all.size();
            course = new String[i];
            int j = 0;
            while (j < all.size()) {
                course[j] = all.get(i - 1).getName();
                j++;
                i--;
            }
    
            jlist = new JList(course);
            jlist.setSelectionForeground(Color.RED);
            jlist.setSelectionMode(2);
            jlist.setVisibleRowCount(5);

           
        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        }
        //p1 to hold the label of field
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(5, 1));
        p1.add(new JLabel("רҵ"));
        p1.add(new JLabel("�꼶"));
        p1.add(new JLabel("���"));
        p1.add(new JLabel("�༶����"));
        p1.add(new JLabel("�γ�ѡ��"));

        //p2 holding textfield 
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(5, 1));
        p2.add(jtfMajor);
        p2.add(jtfGrade);
        p2.add(jtfClass);
        p2.add(jtfStuNum);

        //p3 holding p1 and p2
        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        p3.add(p1, BorderLayout.WEST);
        p3.add(p2, BorderLayout.CENTER);

        //p4 holding the Button;
        JPanel p4 = new JPanel();
        p4.add(jbtAdd);
        p4.add(jbtCancel);
        p4.add(jbtBack);
        p4.add(jbtAllOK);

        JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("(��ѡ�밴ctrl)"),BorderLayout.CENTER);
		
        JPanel p6 = new JPanel();
        p6.setLayout(new BorderLayout());
        p6.add(p, BorderLayout.WEST);
        p6.add(new JScrollPane(jlist), BorderLayout.CENTER);

        JPanel p7 = new JPanel();
        p7.setLayout(new BorderLayout());
        p7.add(p6, BorderLayout.SOUTH);
        p7.add(p3, BorderLayout.CENTER);
        //p5 holding the panel4 and p3
        JPanel p5 = new JPanel();
        p5.setLayout(new BorderLayout());
        p5.add(p7, BorderLayout.CENTER);
        p5.add(p4, BorderLayout.SOUTH);

        getContentPane().add(p5, BorderLayout.CENTER);
		
        jbtCancel.addActionListener(this);
        jbtBack.addActionListener(this);
        jbtAdd.addActionListener(this);
        jbtAllOK.addActionListener(this);
        jlist.addListSelectionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        
         //�����һ������������ѧ������Ϣ�Ľ���         
		if (e.getSource() == jbtAdd) {
		String test = new String();
		//�ж��Ƿ�Ϊ��Ч���룬�˴����ж��Ƿ�������Ϣ
		if(jtfMajor.getText().compareTo(test) == 0  ||jtfGrade.getText().compareTo(test) == 0  
			            || jtfClass.getText().compareTo(test) == 0  
						|| jtfStuNum.getText().compareTo(test) == 0  /*|| jtfCum.getText().compareTo(test) == 0 */){
		isNull = true;
        JOptionPane.showMessageDialog(null,"����Ϊ�գ�����������");		
		}
		else{
           
				isNull = true;
                String help = new String();
                major = (jtfMajor.getText()).trim();
                grade = (jtfGrade.getText()).trim();
                classNum = (jtfClass.getText()).trim();
                //�˴��Ĳ����쳣��������ǵ��༶�����Ϳγ�������ת������
		try {
                     help = (jtfStuNum.getText()).trim();
                     studentNum = Integer.parseInt(help);
					 //help = jtfCum.getText();
                     //courseNum = Integer.parseInt(help.trim());
					 countS = studentNum;
				    
                int remember = courseNum;
                //int count = courseNum;
                StringBuilder help2 = new StringBuilder(major);
                help2.append(grade);
                help2.append(classNum);
                help2.append(".dat");
                String fileName = help2.toString();
                File file = new File(fileName);
				//�жϰ༶�Ƿ��Ѵ���
                if (!file.exists()) {
                        
                    int[] indices = jlist.getSelectedIndices();
					courseNum = indices.length;
					int count = courseNum;
                    courseSelect = new String[indices.length];
                    if(indices.length != 0){
					for (int i = 0; i < indices.length; i++) {
                        courseSelect[i] = course[indices[i]];
                    }
				    
					jbtAdd.setEnabled(false);
				    jbtCancel.setEnabled(false); 
                    jtfMajor.setEditable(false);
                    jtfGrade.setEditable(false);
                    jtfClass.setEditable(false);
                    jtfStuNum.setEditable(false);
                    jlist.setEnabled(false);
                    student = new Student(courseSelect, countS);
					isNull = false;
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    student.pack();
                    int x = (screenSize.width - student.getWidth()) / 2;
                    int y = (screenSize.height - student.getHeight()) / 2;
                    student.setLocation(x, y);

                    student.setTitle("Test");
                    student.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    student.setVisible(true);
                    finish = true;
					} 
				else JOptionPane.showMessageDialog(null,"��δѡȡ�γ̣���ѡ�γ�");
                } 
				else {
                    JOptionPane.showMessageDialog(null, "�ð༶�Ѵ��ڣ�������Ϣ��Ч,����������");
                }
				
                } catch (Exception ex1) {
                            if(isNull)
                            JOptionPane.showMessageDialog(null, "�����ʽ����,��������");
							
                    jtfStuNum.setEditable(true);
                    
                }
                
			  
            } 
		}

        
        if (e.getSource() == jbtCancel) {
            jtfMajor.setText(" ");
            jtfGrade.setText(" ");
            jtfClass.setText(" ");
            jtfStuNum.setText(" ");
           
        }

        if (e.getSource() == jbtBack) {
		    int comfirmed = JOptionPane.showConfirmDialog(null,"�Ƿ������д");
			if(comfirmed == 0)
            this.dispose();
        }

        if (e.getSource() == jbtAllOK) {
            try {
                if (finish) {
                    StringBuilder s = new StringBuilder(major);
                    s.append(grade);
                    s.append(classNum);
                    String help2 = s.toString();
                    s.append(".dat");
                    String help = s.toString();
                    File file = new File(help);
                    if (!file.exists()) {
					    
						RecordStudent recordStudent = new RecordStudent(student.getname(),student.getID(),student.getCourse(),student.getScore());
					    RecordClassInfor classInfor = new RecordClassInfor(major,grade,classNum,studentNum,courseSelect,recordStudent,courseNum);
                        ObjectOutputStream output =
                                new ObjectOutputStream(new FileOutputStream(help));
                        output.writeObject(classInfor);
                        output.close();

                        MainMeum.classname.add(help2);
                        for (int i = 0; i < MainMeum.classname.size(); i++) {
                            System.out.println(MainMeum.classname.get(i));
                        }
                        ObjectOutputStream output2 =
                                new ObjectOutputStream(new FileOutputStream("classname.dat"));
                        output2.writeObject(MainMeum.classname);
                        System.out.println("ok");
                        output2.close();
                    }
                    this.dispose();
                } else {
				    if(!isNull)
					JOptionPane.showMessageDialog(null,"��Ϣ����Ϊ��,���������");
					else
                    JOptionPane.showMessageDialog(null, "ѧ����Ϣ��û���룬����add����ѧ����Ϣ����");
                }
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            }

        }
    }
    //��Ͽ�ļ���
    public void valueChanged(ListSelectionEvent e) {
        int[] indices = jlist.getSelectedIndices();

        int i;
        for (i = 0; i < indices.length; i++) {
            System.out.println("ѡ���б�����" + indices[i]);
        }
        courseSelect = new String[indices.length];
        for (i = 0; i < indices.length; i++) {
            courseSelect[i] = course[indices[i]];

        }
        for (i = 0; i < courseSelect.length; i++) {
            System.out.println("��ѡ�� " + courseSelect[i]);
        }

    }

    public int compareTo(Object o) {
        if (major.compareTo(((ClassInfor) o).major) > 0) {
            return 1;
        } else if (major.compareTo(((ClassInfor) o).major) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public String getMjor() {
        return major;
    }

    public String getGrade() {
        return grade;
    }

    public String getClassNum() {
        return classNum;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public String[] getCourse() {
        return courseSelect;
    }

    public Student getStudent() {
        return student;
    }
}
//��¼ѧ��
class Student extends JDialog implements ActionListener, Serializable {

    private String[] name;
    private String[] ID;
    private String[] course;
    private double[][] score;
    static boolean terator = false;
    private int courseNum;
    static int courseRem;
    static int studentRem;
    private int studentNum;
    private JButton jbtFinish = new JButton("Finish");
    private JButton jbtNextM = new JButton("��һ��ѧ��");
    private JTextField jtfName = new JTextField(10);
    private JTextField jtfID = new JTextField(10);
    private JLabel jlbNum;

    public Student() {
    }

    public Student(String[] course, int studentNum) {

        Student.terator = false;
        this.course = course;
        this.studentNum = studentNum;
        studentRem = studentNum;
        courseNum = this.course.length;
        courseRem = courseNum;
        score = new double[studentNum][courseNum];
        name = new String[studentNum];
        ID = new String[studentNum];
        
        JPanel p6 = new JPanel();
        p6.setLayout(new GridLayout(4, 1));  
        p6.add(new JLabel("����"));
        p6.add(new JLabel("ѧ��"));
     
        JPanel p7 = new JPanel();
        p7.setLayout(new GridLayout(4, 1));
        p7.add(jtfName);
        p7.add(jtfID);
     

        JPanel p9 = new JPanel();
        p9.add(jbtNextM);
        p9.add(jbtFinish);

        JPanel p10 = new JPanel();
        p10.setLayout(new BorderLayout());
        p10.add(p6, BorderLayout.WEST);
        p10.add(p7, BorderLayout.CENTER);


        JPanel p11 = new JPanel();
        p11.setLayout(new BorderLayout());

        p11.add(jlbNum = new JLabel("����ѧ����Ϣ"
                + "(����" + (studentNum - 1) + "λѧ������Ϣ��û��д)"), BorderLayout.NORTH);
        p11.add(p10, BorderLayout.CENTER);
        p11.add(p9, BorderLayout.SOUTH);

        getContentPane().add(p11, BorderLayout.CENTER);
		
        jbtNextM.addActionListener(this);
        jbtFinish.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        boolean right = false;
        boolean finish = false;
        if (e.getSource() == jbtNextM) {

            String help;
            this.name[studentRem - 1] = (jtfName.getText()).trim();		
			try{
		        ArrayList<String> classname;
                ObjectInputStream input2 = new ObjectInputStream(
                        new FileInputStream("classname.dat"));
                classname = (ArrayList<String>) (input2.readObject());
                input2.close();
			    StringBuffer filename;
                String file;
                int i = 0;
               // boolean same = false;	
                    //�鿴�Ƿ���ѧ����ͬ��ѧ����������������		   
				    while(i < classname.size()){
                      for (i = 0; i < classname.size(); i++) {
                            filename = new StringBuffer(classname.get(i));
                            filename.append(".dat");
                            file = filename.toString();
                            ObjectInputStream input3 = new ObjectInputStream(
                                new FileInputStream(file));
                            RecordClassInfor classInfor = (RecordClassInfor)(input3.readObject());
						    input3.close();
                            RecordStudent s = classInfor.getStudent();
						    String[] studentID = new String[classInfor.getStudentNum()];
                            studentID = s.getID();
                            int j = 0;
                            for (j = 0; j < classInfor.getStudentNum(); j++) {
                               if ((jtfID.getText()).trim().compareTo(studentID[j]) == 0) {
			                    jtfID.setText(JOptionPane.showInputDialog(null,"��ѧ���Ѵ��ڣ�����������"));
							    i = -1;
							    j = classInfor.getStudentNum();
							  }
			                }
					    }
				    }
				this.ID[studentRem - 1] = (jtfID.getText()).trim();    
                studentRem--;
                if (studentRem == 0) {
                    finish = true;
                    JOptionPane.showMessageDialog(null, "ȫ����Ϣ������� ");
                    this.dispose();
                } else{   
                    jtfName.setText(" ");
                    jtfID.setText(" ");
                }
        } catch (IOException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            }
	}
        if (e.getSource() == jbtFinish) {
            if (finish) {
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "��Ϣ��δ�������");
            }
        }
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public void setID(String[] ID) {
        this.ID = ID;
    }

    public void setCourse(String[] course) {
        this.course = course;
    }

    public void setScore(double[][] score) {
        this.score = score;
    }

    public String[] getname() {
        return name;
    }

    public String[] getID() {
        return ID;
    }

    public String[] getCourse() {
        return course;
    }

    public double[][] getScore() {
        return score;
    }
}
//���ڴ洢ѧ����Ϣ���Ա����л�
class RecordStudent implements Serializable{
    private String[] name;
	private String[] ID;
	private String[] course;
	private double[][] score;
	
	public RecordStudent(String[] name,String[] ID,String[] course,double[][] score){
	   this.name = name;
	   this.ID = ID;
	   this.course = course;
	   this.score = score;
	}
	public String[] getname() {
        return name;
    }
	public void setname(String[] name) {
        this.name = name;
    }

    public String[] getID() {
        return ID;
    }
    public void setID(String[] ID) {
        this.ID = ID;
    }
    public String[] getCourse() {
        return course;
    }
	
	public void setCourse(String[] course) {
        this.course = course;
    }

    public double[][] getScore() {
        return score;
    }
	public void setScore(double[][] score) {
        this.score = score;
    }
}
//���ڴ洢�༶��Ϣ���Ա����л�
class RecordClassInfor implements Serializable{
    private String major;
	private String grade;
	private String classNum;
	private int studentNum;
	private String[] courseSelect;
	private RecordStudent student;
	private int courseNum;
	
	public RecordClassInfor(String major,String grade,String classNum,
	     int studentNum,String[] courseSelect,RecordStudent student,int courseNum){
	  this.major = major;
	  this.grade = grade;
	  this.classNum = classNum;
	  this.studentNum = studentNum;
	  this.courseSelect = courseSelect;
	  this.student = student;
	  this.courseNum = courseNum;
	  
	}
	public String getMjor() {
        return major;
    }

    public String getGrade() {
        return grade;
    }

    public String getClassNum() {
        return classNum;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public String[] getCourse() {
        return courseSelect;
    }
	 public  void setCourse(String[] courseSelect) {
        this.courseSelect = courseSelect;
    }


    public RecordStudent getStudent() {
        return student;
    }
	
	
	public void setStudent(RecordStudent student) {
        this.student = student;
    }
	
	
}

//��ѯ����ѧ�����Ƴɼ�
class StudentMark extends JDialog implements ActionListener, Serializable {

    private double score;
    private String course;
    private String studentName;
    private String studentID;
    private JTextField jtfID = new JTextField();
    private JTextField jtfName = new JTextField();
    private JTextField jtfCourse = new JTextField();
    private JTextField jtfGrade = new JTextField();
    private JButton jbtBack = new JButton("������ҳ��");

    StudentMark(RecordStudent student, int studentNum, int courseNum) {
        RecordStudent s = student;
        double[][] allScore = s.getScore();
        score = allScore[studentNum][courseNum];
        String[] allName = s.getname();
        String[] allID = s.getID();
        String[] allCourse = s.getCourse();
        this.course = allCourse[courseNum];
        this.studentName = allName[studentNum];
        this.studentID = allID[studentNum];

        jtfID.setText(this.studentID);
        jtfID.setEditable(false);
        jtfName.setText(this.studentName);
        jtfName.setEditable(false);
        jtfCourse.setText(this.course);
        jtfCourse.setEditable(false);
        jtfGrade.setText(String.valueOf(this.score));
        jtfGrade.setEditable(false);
		
		
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 1, 3, 5));
		JLabel b1 = new JLabel("ѧ��");
		JLabel b2 = new JLabel("����");
		JLabel b3 = new JLabel("�γ�");
		JLabel b4 = new JLabel("�ɼ�");
        Font font1 = new Font("SansSerif",Font.BOLD,14);
		b1.setFont(font1);
		b2.setFont(font1);
		b3.setFont(font1);
		b4.setFont(font1);
		
		jtfID.setFont(font1);
        jtfID.setFont(font1);
        jtfName.setFont(font1);
        jtfName.setFont(font1);
        jtfCourse.setFont(font1);
        jtfCourse.setFont(font1);
        jtfGrade.setFont(font1);
        jtfGrade.setFont(font1);
		
		p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(4, 1));
        p2.add(jtfName);
        p2.add(jtfID);
        p2.add(jtfCourse);
        p2.add(jtfGrade);


        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        p3.add(p1, BorderLayout.WEST);
        p3.add(p2, BorderLayout.CENTER);

        getContentPane().add(p3, BorderLayout.CENTER);
        getContentPane().add(jbtBack, BorderLayout.SOUTH);
        jbtBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtBack) {
            this.dispose();
        }
    }
}
//�����༶�ɼ�
class ClassMark extends JDialog implements Serializable {

    private String[] className;
    private String name;
    private double hightest;
    private double lowest;
    private double average;
    private int eGrade;
    private int dGrade;
    private int cGrade;
    private int bGrade;
    private int aGrade;
    private int studentNum;
    private String courseName;
    private int courseID;
    private ClassInfor classInfor;
    private double sum;

    public ClassMark() {
    
	}

    public ClassMark(RecordClassInfor className, int keyID, String name) {
        
        RecordStudent s = className.getStudent();
        this.name = name;
        studentNum = className.getStudentNum();
        double[][] score = s.getScore();
        hightest = lowest = score[0][keyID];
        for (int i = 0; i < studentNum; i++) {
            if (score[i][keyID] > hightest) {
                hightest = score[i][keyID];
            } else if (score[i][keyID] < lowest) {
                lowest = score[i][keyID];
            }
            if (score[i][keyID] < 60) {
                eGrade++;
            }
            if (score[i][keyID] < 70 && score[i][keyID] > 59) {
                dGrade++;
            }
            if (score[i][keyID] < 80 && score[i][keyID] > 69) {
                cGrade++;
            }
            if (score[i][keyID] < 90 && score[i][keyID] > 79) {
                bGrade++;
            }
            if (score[i][keyID] < 101 && score[i][keyID] > 89) {
                aGrade++;
            }
            sum += score[i][keyID];
        }
        average = sum / studentNum;
    }

    public int getEGrade() {
        return eGrade;
    }

    public int getDGrade() {
        return dGrade;
    }

    public int getCGrade() {
        return cGrade;
    }

    public int getBGrade() {
        return bGrade;
    }

    public int getAGrade() {
        return aGrade;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //����
        g.setColor(Color.black);
        int Height = getHeight();
        int Width = getWidth();
        int unitH = (Height / 2 - 60) / 25;
        int unitW = (getWidth() - 50) / 5 - 20;
        g.drawRect(15, 23, getWidth() - 26, Height / 2 - 30);
        g.drawString("����", 25, 40);


        g.setColor(Color.black);
        g.drawString(" 25", 25, 55);
        g.drawString(" 20", 25, 55 + 5 * unitH);
        g.drawString(" 15", 25, 55 + 10 * unitH);
        g.drawString(" 10", 25, 55 + 15 * unitH);
        g.drawString("  5", 25, 55 + 20 * unitH);
        g.drawRect(50, 50, getWidth() - 70, Height / 2 - 80);
        //����ɼ�����
		int ePortion = eGrade * 100 / studentNum;
        int dPortion = dGrade * 100 / studentNum;
        int cPortion = cGrade * 100 / studentNum;
        int bPortion = bGrade * 100 / studentNum;
        int aPortion = aGrade * 100 / studentNum;
        int eAngel;
        int dAngel;
        int cAngel;
        int bAngel;
        int aAngel;
		
        g.setColor(Color.blue);
        g.fillRect(unitW, 296 - unitH * eGrade, 40, unitH * eGrade);
        g.drawString("<60��", unitW - 2, Height / 2 - 10);
        g.fillRect(unitW * 2 + 4, 296 - unitH * dGrade, 40, unitH * dGrade);
        g.drawString("60-69��", unitW * 2, Height / 2 - 10);
        g.fillRect(unitW * 3 + 4, 296 - unitH * cGrade, 40, unitH * cGrade);
        g.drawString("70-79��", unitW * 3, Height / 2 - 10);
        g.fillRect(unitW * 4 + 4, 296 - unitH * bGrade, 40, unitH * bGrade);
        g.drawString("80-89��", unitW * 4, Height / 2 - 10);
        g.fillRect(unitW * 5 + 6, 296 - unitH * aGrade, 40, unitH * aGrade);
        g.drawString("90-100��", unitW * 5, Height / 2 - 10);
        g.setColor(Color.red);
        g.drawLine(0, Height / 2, getWidth(), Height / 2);
        g.setColor(Color.black);
        //draw a cricle;
        g.drawOval(Width / 2 - 80, Height / 2 + 50, 250, 250);
        
        if (ePortion != 0) {
            eAngel = 360 * ePortion / 100;
        } else {
            eAngel = 0;
        }

        if (dPortion != 0) {
            dAngel = 360 * dPortion / 100;
        } else {
            dAngel = 0;
        }
        if (cPortion != 0) {
            cAngel = 360 * cPortion / 100;
        } else {
            cAngel = 0;
        }
        if (bPortion != 0) {
            bAngel = 360 * bPortion / 100;
        } else {
            bAngel = 0;
        }
        if (aPortion != 0) {
            aAngel = 360 * aPortion / 100;
        } else {
            aAngel = 0;
        }
        //draw Arcs;
        int rest = 360 - (eAngel + dAngel + cAngel + bAngel + aAngel) ;
        int add = 0;
		if(rest <= 0){
		rest = 0;
		}
        g.setColor(Color.red);
        if (eAngel != 0) {
            g.fillArc(Width / 2 - 80, Height / 2 + 50, 250, 250, 0, eAngel + rest);
            add += eAngel +rest;
			rest = 0;
		}
		
        g.setFont(new Font("Courier", Font.PLAIN, 14));
        g.fillRect(Width / 2 + 250, Height / 2 + 50, 15, 15);
        g.setColor(Color.black);
        g.drawString("<60��", Width / 2 + 270, Height / 2 + 60);
        g.drawString(String.valueOf(ePortion)+"%",Width/2 +340,Height/2 + 60);
        g.setColor(Color.blue);
        if (dAngel != 0) {
            g.fillArc(Width / 2 - 80, Height / 2 + 50, 250, 250, add, dAngel + rest);
            add += dAngel + rest;
			rest = 0;
		}
		//else add += dAngel;
        g.fillRect(Width / 2 + 250, Height / 2 + 80, 15, 15);
        g.setColor(Color.black);
        g.drawString("60-69��", Width / 2 + 270, Height / 2 + 90);
		g.drawString(String.valueOf(dPortion)+"%", Width / 2 + 340, Height / 2 + 90);

        g.setColor(Color.green);
        if (cAngel != 0) {
            g.fillArc(Width / 2 - 80, Height / 2 + 50, 250, 250, add, cAngel + rest);
            add += cAngel + rest;
			rest = 0;
		}
        g.fillRect(Width / 2 + 250, Height / 2 + 110, 15, 15);
        g.setColor(Color.black);
        g.drawString("70-79��", Width / 2 + 270, Height / 2 + 120);
		g.drawString(String.valueOf(cPortion)+"%", Width / 2 + 340, Height / 2 + 120);

        g.setColor(Color.yellow);
        if (bAngel != 0) {
            g.fillArc(Width / 2 - 80, Height / 2 + 50, 250, 250, add, bAngel + rest);
            add += bAngel + rest;
			rest = 0;
		}
        g.fillRect(Width / 2 + 250, Height / 2 + 140, 15, 15);
        g.setColor(Color.black);
        g.drawString("80-89��", Width / 2 + 270, Height / 2 + 150);
		g.drawString(String.valueOf(bPortion)+"%", Width / 2 + 340, Height / 2 + 150);

        g.setColor(Color.pink);
        if (aAngel != 0) {
            g.fillArc(Width / 2 - 80, Height / 2 + 50, 250, 250, add,360 - add);
			
        }
        g.fillRect(Width / 2 + 250, Height / 2 + 170, 15, 15);
        g.setColor(Color.black);
        g.drawString("90-100��", Width / 2 + 270, Height / 2 + 180);
		g.drawString(String.valueOf(aPortion)+"%", Width / 2 + 340, Height / 2 + 180);

        //title
        g.setColor(Color.red);
        g.setFont(new Font("TimesRoman", Font.BOLD, 18));
        g.drawString("���Գɼ�ͳ��ͼ", 270, 40);
        //g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.BOLD, 16));

        StringBuffer help = new StringBuffer(this.name);
        help.append("�༶�ķ���������ͼ�����");
        String temp = help.toString();
        g.drawString(temp, 30, Height / 2 + 30);

        help = new StringBuffer("��߷�:");
        help.append(String.valueOf(this.hightest));
        temp = help.toString();
        g.drawString(temp, 30, Height / 2 + 50);

        help = new StringBuffer("��ͷ�:");
        help.append(String.valueOf(this.lowest));
        temp = help.toString();
        g.drawString(temp, 30, Height / 2 + 70);

        help = new StringBuffer("ƽ����:");
        help.append(String.valueOf(this.average));
        temp = help.toString();
        g.drawString(temp, 30, Height / 2 + 90);





    }
}

//��ѯ�༶��Ϣ
class CheckClass extends JDialog implements ActionListener, Serializable, ListSelectionListener {

    private String selectClass;
    private String[] classname;
    private JTextField jtfClassName = new JTextField();
    private JTextField jtfStudentNum = new JTextField();
    private JTextField jtfCourseNum = new JTextField();
    private JTextField jtfCourse = new JTextField();
    private JList jlist1;
    private JList jlist2 = new JList();
	private JPanel addList = new JPanel(new BorderLayout()); 
    private JButton jbtComfirm = new JButton("ȷ��");

    public CheckClass() {
        jtfClassName.setEditable(false);
        jtfStudentNum.setEditable(false);
        jtfCourseNum.setEditable(false);
        jtfCourse.setEditable(false);
		Font font1 = new Font("SansSerif",Font.BOLD,14);
		jtfClassName.setFont(font1);
        jtfStudentNum.setFont(font1);
        jtfCourseNum.setFont(font1);
        jtfCourse.setFont(font1);
		
        try {

            ArrayList<String> className;
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream("classname.dat"));
            className = (ArrayList<String>) (input.readObject());
            input.close();
            int i = className.size();
            classname = new String[i];
            int j = 0;
            while (j < className.size()) {
                classname[j] = className.get(i - 1);
                j++;
                i--;
            }

            jlist1 = new JList(classname);
            jlist1.setSelectionForeground(Color.RED);
            jlist1.setSelectionMode(0);
            jlist1.setVisibleRowCount(8);

        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        }

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
		JLabel b =new JLabel("��ѡ��༶");
		b.setFont(font1);
        p1.add(b,BorderLayout.NORTH);
        p1.add(jbtComfirm, BorderLayout.SOUTH);
        p1.add(jlist1, BorderLayout.CENTER);
		jlist1.setFont(font1);
		jlist2.setFont(font1);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(4, 1));
		JLabel b1 = new JLabel("�༶");
		JLabel b2 = new JLabel("ѧ������");
		JLabel b3 = new JLabel("ѧ����Ŀ");
		JLabel b4 = new JLabel("��ѧѧ��");
		b1.setFont(font1);
		b2.setFont(font1);
		b3.setFont(font1);
		b4.setFont(font1);
		
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(4, 1));
        p3.add(jtfClassName);
        p3.add(jtfStudentNum);
        p3.add(jtfCourseNum);
        p3.add(jtfCourse);
		p3.setSize(200,200);

        JPanel p4 = new JPanel();
        p4.setLayout(new BorderLayout());
        p4.add(p2, BorderLayout.WEST);
        p4.add(p3, BorderLayout.CENTER);
		
    
        getContentPane().add(p1, BorderLayout.WEST);
        getContentPane().add(p4, BorderLayout.CENTER);
		getContentPane().add(addList,BorderLayout.EAST);

        jbtComfirm.addActionListener(this);
        jlist1.addListSelectionListener(this);

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int indices = jlist1.getSelectedIndex();
        selectClass = classname[indices];
		
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtComfirm) {
		   // addList.remove(jlist2);
            String file = selectClass;
            String fileName = file + ".dat";
            try {
                RecordClassInfor infor;
                ObjectInputStream input = new ObjectInputStream(
                        new FileInputStream(fileName));
                infor = (RecordClassInfor) (input.readObject());
                input.close();
                jtfClassName.setText(file);
                jtfStudentNum.setText(String.valueOf(infor.getStudentNum()));
                jtfCourseNum.setText(String.valueOf(infor.getCourseNum()));
                String[] help = infor.getCourse();
                StringBuilder temp = new StringBuilder(help[0]);
                for (int i = 1; i < help.length; i++) {
                    temp.append(help[i]);
					temp.append(" ");
                }
                jtfCourse.setText(temp.toString());
                RecordStudent s = infor.getStudent();
                String[] student = new String[infor.getStudentNum()];
                String[] studentName = s.getname();
                String[] studentID = s.getID();
                for (int i = 0; i < infor.getStudentNum(); i++) {
                    StringBuilder temp2 = new StringBuilder(studentID[i]);
                    temp2.append(" - ");
                    temp2.append(studentName[i]);
                    student[i] = temp2.toString();
                }
                jlist2 = new JList(student);
                jlist2.setVisibleRowCount(8);
                jlist2.setEnabled(false);
				JLabel b = new JLabel("   ѧ������");
				JLabel b2 = new JLabel("��ѧ�� - ������");
				Font font1 = new Font("SansSerif",Font.BOLD,14);
				JPanel p = new  JPanel();
				p.setLayout(new BorderLayout());
				p.add(b,BorderLayout.NORTH);
				p.add(b2,BorderLayout.CENTER);
				b2.setFont(font1);
				b.setFont(font1);
				this.addList.add(p,BorderLayout.NORTH);
                this.addList.add(jlist2,BorderLayout.CENTER);
				jlist2.setFont(font1);
			    jlist2.repaint();
				this.repaint();
				this.setSize(370, 370);

            } catch (FileNotFoundException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            }


        }
    }
}

//��¼�γ���Ϣ����
class Course extends JDialog implements ActionListener, Comparable, Serializable {

    private String name;
    private String courseID;
    private double xuefeng;
    private int xueshi;
    private String testDate;
    private int testNum; //��������	 
    private JTextField jftName = new JTextField(10);
    private JTextField jftCourseID = new JTextField(10);
    private JTextField jftXueFeng = new JTextField(10);
    private JTextField jftXueShi = new JTextField(10);
    private JTextField jftTestDate = new JTextField(10);
    private JTextField jftTestNum = new JTextField(10);
    private JButton jbtFinish = new JButton("���");
    private JButton jbtCancel = new JButton("����");
    private JButton jbtBack = new JButton("����");

    public Course() {
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(6, 1));
        p1.add(new JLabel("�γ�����"));
        p1.add(new JLabel("�γ̱��"));
        p1.add(new JLabel("�γ�ѧ��"));
        p1.add(new JLabel("�γ�ѧʱ"));
        p1.add(new JLabel("��������"));
        p1.add(new JLabel("��������"));

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(6, 1));
        p2.add(jftName);
        p2.add(jftCourseID);
        p2.add(jftXueFeng);
        p2.add(jftXueShi);
        p2.add(jftTestDate);
        p2.add(jftTestNum);

        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        p3.add(p1, BorderLayout.WEST);
        p3.add(p2, BorderLayout.CENTER);

        JPanel p4 = new JPanel();
        p4.add(jbtFinish);
        p4.add(jbtCancel);
        p4.add(jbtBack);


        getContentPane().add(p3, BorderLayout.CENTER);
        getContentPane().add(p4, BorderLayout.SOUTH);

        jbtFinish.addActionListener(this);
        jbtCancel.addActionListener(this);
        jbtBack.addActionListener(this);

    }

    @Override
    public String getName() {
        return name;
    }

    public String getCourseID() {
        return courseID;
    }

    public double getXF() {
        return xuefeng;
    }

    public int getXS() {
        return xueshi;
    }

    public String getDate() {
        return testDate;
    }

    public int getNum() {
        return testNum;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String help;
		boolean isNull = false;
		String test = new String();
		if(jftName.getText().compareTo(test) == 0 ||jftCourseID.getText().compareTo(test) == 0
		          ||jftXueFeng.getText().compareTo(test) == 0 ||jftXueShi.getText().compareTo(test) == 0
				  ||jftTestNum.getText().compareTo(test) ==0 ||jftTestDate.getText().compareTo(test) == 0)
         {isNull = true; }
        if (e.getSource() == jbtFinish) {
            if(!isNull){
			boolean right = false;
			
            name = (jftName.getText()).trim();
            courseID = jftCourseID.getText();

            try {
                help = jftXueFeng.getText();
                xuefeng = Double.parseDouble(help.trim());

            } catch (Exception ex) {
                while (!right) {
                    try {
                        help = JOptionPane.showInputDialog(null, "����ѧ�ָ�ʽ�д����ڴ�����������ȷ��ѧ��");
                        xuefeng = Double.parseDouble(help.trim());
                        jftXueFeng.setText(help);
                        right = true;
                    } catch (Exception ex1) {
                    }
                }
            }
            right = false;
            try {
                help = jftXueShi.getText();
                xueshi = Integer.parseInt(help.trim());
            } catch (Exception ex3) {
                while (!right) {
                    try {
                        help = JOptionPane.showInputDialog(null, "����ѧʱ��ʽ�д����ڴ�����������ȷ��ѧʱ");
                        xueshi = Integer.parseInt(help.trim());
                        right = true;
                    } catch (Exception ex4) {
                    }
                }
            }
            testDate = (jftTestDate.getText()).trim();
            right = false;
            try {
                help = jftTestNum.getText();
                testNum = Integer.parseInt(help.trim());
            } catch (Exception ex5) {
                while (!right) {
                    try {
                        help = JOptionPane.showInputDialog(null, "����ѧ������ʽ�д����ڴ�����������ȷ��ѧ����");
                        testNum = Integer.parseInt(help.trim());
                        right = true;
                    } catch (Exception ex6) {
                    }
                }
            }
            try {
                ArrayList<RecordCourse> all;
                ObjectInputStream input = new ObjectInputStream(
                        new FileInputStream("Course.dat"));
                all = (ArrayList<RecordCourse>) input.readObject();
                input.close();
                ObjectOutputStream output =
                        new ObjectOutputStream(new FileOutputStream("Course.dat"));
				RecordCourse course = new RecordCourse(name,courseID,xuefeng,xueshi,testDate,testNum);
                all.add(course);
                output.writeObject(all);
                output.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: " + ex);
                System.exit(0);
            }
            this.dispose();
		}
		else JOptionPane.showMessageDialog(null,"��Ϣδ���꣬��������Ϣ");

        }
        if (e.getSource() == jbtCancel) {
            jftName.setText(" ");
            jftCourseID.setText(" ");
            jftXueFeng.setText(" ");
            jftXueShi.setText(" ");
            jftTestDate.setText(" ");
            jftTestNum.setText(" ");
        }
        if (e.getSource() == jbtBack) {
            int comfirmed = JOptionPane.showConfirmDialog(null,"�Ƿ������д");
			if(comfirmed == 0)
			this.dispose();
        }
    }

    @Override
    public int compareTo(Object o) {
        if (name.compareTo(((Course) o).name) > 0) {
            return 1;
        } else if (name.compareTo(((Course) o).name) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}

//����һ����¼�γ���Ϣ�����Ա����л�
class RecordCourse implements Serializable{
   private String courseName;
   private String courseID;
   private double xueFeng;
   private int xueShi;
   private String date;
   private int testNum;
   
   public RecordCourse(String courseName,String courseID,double xueFeng,
            int xueShi,String date,int testNum){
      this.courseName = courseName;
	  this.courseID = courseID;
	  this.xueFeng = xueFeng;
	  this.xueShi = xueShi;
	  this.date = date;
	  this.testNum = testNum;
   }
    public String getName() {
        return courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public double getXF() {
        return xueFeng;
    }

    public int getXS() {
        return xueShi;
    }

    public String getDate() {
        return date;
    }

    public int getNum() {
        return testNum;
    }
}

//��ѯ�γ���Ϣ
class CheckCourse extends JDialog implements ActionListener, Serializable {

    private JTextField jftName = new JTextField(10);
    private JTextField jftCourseID = new JTextField(10);
    private JTextField jftXueFeng = new JTextField(10);
    private JTextField jftXueShi = new JTextField(10);
    private JTextField jftTestDate = new JTextField(10);
    private JTextField jftTestNum = new JTextField(10);
	
    private JButton jbtBack = new JButton("����");

    public CheckCourse(RecordCourse c) {
        int help;
        double help2;
        jftName.setEditable(false);
        jftCourseID.setEditable(false);
        jftXueFeng.setEditable(false);
        jftXueShi.setEditable(false);
        jftTestDate.setEditable(false);
        jftTestNum.setEditable(false);
		
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(6, 1));
        p1.add(new JLabel("�γ�����"));
        p1.add(new JLabel("�γ̱��"));
        p1.add(new JLabel("�γ�ѧ��"));
        p1.add(new JLabel("�γ�ѧʱ"));
        p1.add(new JLabel("��������"));
        p1.add(new JLabel("��������"));

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(6, 1));
        p2.add(jftName);
        p2.add(jftCourseID);
        p2.add(jftXueFeng);
        p2.add(jftXueShi);
        p2.add(jftTestDate);
        p2.add(jftTestNum);

        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        p3.add(p1, BorderLayout.WEST);
        p3.add(p2, BorderLayout.CENTER);

        JPanel p4 = new JPanel();
        p4.add(jbtBack);


        getContentPane().add(p3, BorderLayout.CENTER);
        getContentPane().add(p4, BorderLayout.SOUTH);

        jbtBack.addActionListener(this);
       

        
                    jftName.setText(c.getName());
                    jftName.setEditable(false);
                    jftCourseID.setText(c.getCourseID());
                    jftCourseID.setEditable(false);
                    help2 = c.getXF();
                    jftXueFeng.setText(java.lang.String.valueOf(help2));
                    jftXueFeng.setEditable(false);
                    help = c.getXS();
                    jftXueShi.setText(java.lang.String.valueOf(help));
                    jftXueShi.setEditable(false);
                    jftTestDate.setText(c.getDate());
                    jftTestDate.setEditable(false);
                    help = c.getNum();
                    jftTestNum.setText(java.lang.String.valueOf(help));
                    jftTestNum.setEditable(false);
                    

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtBack) {
            this.dispose();
        }
    }
}

//�ɼ�����
class MarkInput extends JDialog implements ActionListener{
   private JComboBox jcobClass;
   private JComboBox jcobCourse = new JComboBox();
   private JLabel studentName = new JLabel("����");
   private JLabel studentID = new JLabel("ѧ��");
   private JLabel selectCourse = new JLabel("�γ�");
   private JLabel JLscore = new JLabel("����");
   private Font font = new Font("SansSerif",Font.BOLD,14);
   private JButton jbtComfirm = new JButton("ȷ��");
   private JTextField jtfScore = new JTextField(); 
   private String[] classname;
   private int index;
   private int courseIndex;
   private int studentNum;
   static int count=0;
   private double[][] score;
   private RecordClassInfor infor;
   private StringBuffer fileName;
   private RecordStudent student;
   private String[] ID;
   private String[] name;
   private String[] course;
   
   public MarkInput(){
     studentName.setFont(font);
	 studentID.setFont(font);
	 selectCourse.setFont(font);
	 JLscore.setFont(font);
	 jtfScore.setFont(font);
	 jbtComfirm.setFont(font);
 
	 
	 try {
            ArrayList<String> className;
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream("classname.dat"));
            className = (ArrayList<String>) (input.readObject());
            input.close();
            int i = className.size();
            classname = new String[i];
            int j = 0;
            while (j < className.size()) {
                classname[j] = className.get(i - 1);
                j++;
                i--;
            }

            jcobClass = new JComboBox(classname);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        }
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1,2));
		p1.add(jcobClass);
		p1.add(jcobCourse);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(JLscore,BorderLayout.WEST);
		p3.add(jtfScore,BorderLayout.CENTER);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(4,1));
		p2.add(studentName);
		p2.add(studentID);
		p2.add(selectCourse);
		p2.add(p3);
		
		
		
		getContentPane().add(p1,BorderLayout.NORTH);
		getContentPane().add(p2,BorderLayout.CENTER);
		getContentPane().add(jbtComfirm,BorderLayout.SOUTH);
		
		jbtComfirm.addActionListener(this);
		jcobClass.addActionListener(this);
		jcobCourse.addActionListener(this);
		
   }
     
   public void actionPerformed(ActionEvent e){
      try{
	  if(e.getSource() == jbtComfirm){
		 String temp = jtfScore.getText().trim();
                  System.out.print(temp);
			 double help1 = Double.parseDouble(temp);
		     score[count][courseIndex] = help1;
              if(count < studentNum -1){
                   
                     System.out.print("ok");
		     studentID.setText(ID[count]);
			 studentName.setText(name[count]);
		     
                    
             JOptionPane.showMessageDialog(null,"������һλѧ���ĳɼ�");
             count++;
			 System.out.println(count);
			 System.out.println(studentNum);
             jtfScore.setText(" ");			 	  
		  }	 
		 else
		 { 
		   JOptionPane.showMessageDialog(null,"�������");
		   student.setScore(score);
		   infor.setStudent(student);
		   String help = fileName.toString();
		   ObjectOutputStream output =
                                new ObjectOutputStream(new FileOutputStream(help));
                        output.writeObject(infor);
                        output.close();	
		   
		 }
		 
	  }
		        if(e.getSource() == jcobClass){
				 jcobCourse.removeAllItems();
                 index =jcobClass.getSelectedIndex();
				 System.out.println(index);
		         fileName = new StringBuffer(classname[index]);	 
		         fileName.append(".dat");
                 
                 ObjectInputStream input2 = new ObjectInputStream(
                    new FileInputStream(fileName.toString()));	
                infor = (RecordClassInfor) (input2.readObject());
				input2.close();
                 student = infor.getStudent();
				ID = student.getID();
				name = student.getname();
				studentNum = ID.length;
				count = 0;
                course = infor.getCourse();
				score = new double[studentNum][course.length];
				for(int i=0;i <course.length;i++){
				  jcobCourse.addItem(course[i]);
				}
				this.jcobCourse.repaint();
				this.repaint();
			}
               if(e.getSource() == jcobCourse){		
		        courseIndex = jcobCourse.getSelectedIndex();
				selectCourse.setText("�γ����֣�"+course[courseIndex]);
				studentID.setText("ѧ�ţ�"+ID[count]);
				studentName.setText("������"+name[count]);
				this.repaint();
			}
				
		
   }catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
            System.exit(0);
        }
  
     
   
}

}