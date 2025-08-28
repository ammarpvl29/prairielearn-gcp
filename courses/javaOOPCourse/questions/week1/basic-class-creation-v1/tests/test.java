public class Test {
    public static void main(String[] args) {
        try {
            // Test student creation
            Student student = new Student("John Smith");
            
            // Test getter methods
            String name = student.getName();
            int id = student.getStudentId();
            double gpa = student.getGpa();
            String info = student.getInfo();
            
            // Simple assertions
            if (name.equals("John Smith") && 
                id == 12345 && 
                Math.abs(gpa - 3.85) < 0.01 && 
                info.equals("Student: John Smith, ID: 12345, GPA: 3.85")) {
                System.out.println("PASS: All tests passed!");
            } else {
                System.out.println("FAIL: Some tests failed");
                System.out.println("Name: " + name);
                System.out.println("ID: " + id);
                System.out.println("GPA: " + gpa);
                System.out.println("Info: " + info);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}