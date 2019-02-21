package collector;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: qianhangkang
 * Date: 2018/11/19
 * Email: qianhangkang@dxy.cn
 */
public class Collector {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "4");
        list.stream().filter("2"::equals).forEach(System.out::println);
    }
}
