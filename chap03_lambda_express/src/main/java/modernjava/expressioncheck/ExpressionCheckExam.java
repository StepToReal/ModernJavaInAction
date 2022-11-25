package modernjava.expressioncheck;

public class ExpressionCheckExam {
    public static void main(String[] args) {
//        Object o = () -> { System.out.println("hello"); }; //error - Object 는 함수형 인터페이스 아님, 형식 추론이 안됨.
        Runnable r = () -> System.out.println("hello");
        Object o = (Runnable) () -> System.out.println("hello"); // 이렇게는 가능. 인터페이스의 객체가 만들어지기 때문
    }
}
