import com.security.demo.domain.entity.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainTest {


    @Test
    @DisplayName("Cloneable - clone() 테스트")
    void copyTest() {
        Student student = new Student();
        System.out.println("init oldStudent's Name : " + student.getName());

//      Student 클래스에 구현한 clone()
        Student newStudent = student;
        newStudent.setName("juli");
        System.out.println("newStudent's address : " + newStudent);
        System.out.println("oldStudent's address : " + student);
    }


    @Test
    @DisplayName("Cloneable - clone() 테스트")
    void mainTest() throws CloneNotSupportedException {
        Student student = new Student();
        System.out.println("init oldStudent's Name : " + student.getName());

        // Student 클래스에 구현한 clone()
//        Student newStudent = student.clone();
//        newStudent.setName("juli");
//        System.out.println("newStudent's address : " + newStudent);
        System.out.println("oldStudent's address : " + student);
    }

    @Test
    @DisplayName("복사 생성자 & 복사 팩토리 테스트")
    void copyFactory() {
        Student student = new Student();
        System.out.println("oldStudent's address : " + student);

        // 복사 팩토리 copy() 사용
        Student newStudent = Student.copy(student);
        newStudent.setName("Juli");
        System.out.println("newStudent's address : " + newStudent);
        System.out.println("oldStudent's address : " + student);
        System.out.println("newStudent's name : " + newStudent.getName());
        System.out.println("oldStudent's name : " + student.getName());
    }

}
