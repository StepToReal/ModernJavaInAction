package modernjava.localvar;

import java.util.function.Predicate;

public class LocalVariableUseExam {
    private Integer no = 120;

    public static void main(String[] args) {
        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber); // lambda 에서 외부변수(자유변수) 사용 가능 --> 람다 캡처링
        r.run();

//        portNumber = 12; //error -> lambda 에서 사용된 변수는 final or final 처럼 사용되어야 한다.

        new LocalVariableUseExam().printNo();
    }

    private void printNo() {
        Predicate<Integer> p = (n) -> n > 100;
        no = 103; // 음.. 실행이 되는데 인스턴스 변수는 final 제한이 없는건가??

        if (p.test(no)) {
            System.out.println(no);
        }
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}
