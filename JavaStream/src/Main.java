import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {

        List<Integer> collect = Stream.of(asList(1, 2), asList(1, 3))
                .flatMap(Collection::stream)
                .collect(toList());


        System.out.println(collect.hashCode());
        System.out.println(asList(1, 1, 2, 3).hashCode());


        Optional<Long> id = null;

        System.out.println("hello");

    }
}
