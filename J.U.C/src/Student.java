/**
 * Date: 2018/9/26 下午5:46
 * Author: qianhangkang
 * Description:
 */
public class Student {
    private static int studentNum = 0;
    private static double totalScore = 0;

    public Student(double score) {
        studentNum++;
        totalScore += score;
    }

    public static void printStudentInfo() {
        System.out.println("total number = " + studentNum + ",average score = " + totalScore / studentNum);
    }


    public static void main(String[] args) {
        //test
        Student s1 = new Student(66.66);
        Student s2 = new Student(77.77);
        Student s3 = new Student(88.88);

        Student.printStudentInfo();

    }
}
