package parallel;

import java.util.Arrays;
import java.util.List;

/**
 * Author: qianhangkang
 * Date: 2018/11/19
 * Email: qianhangkang@dxy.cn
 */
public class Parallelism {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);//arrayList
        //串行
        list.forEach(System.out::print);


        System.out.println(" ");

        //并行
        list.parallelStream().forEach(System.out::print);





    }
}
