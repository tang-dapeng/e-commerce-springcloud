package com.imooc.ecommerce.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


/**
 * java8 Predicate 使用方法与思想
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/15
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PredicateTest {
    public static List<String> MICRO_SERVICE = Arrays.asList("nacos","gateway","authority","feign","ribbon");

    /**
     * test 方法主要用于参数符不符合规则，返回值是 boolean
     */
    @Test
    public void testPredicateTest() {
        Predicate<String> letterLengthLimit = s -> s.length() > 5;
        MICRO_SERVICE.stream().filter(letterLengthLimit).forEach(System.out::println);

    }

    /**
     * and 等同于逻辑与
     */
    @Test
    public void testPredicateAnd() {
        Predicate<String> letterLengthGt = s -> s.length() > 5;
        Predicate<String> letterLengthLt = s -> s.length() < 7;
        MICRO_SERVICE.stream().filter(letterLengthGt.and(letterLengthLt)).forEach(System.out::println);

    }

    /**
     * or 等同于逻辑与
     */
    @Test
    public void testPredicateOr() {
        Predicate<String> letterLengthGt = s -> s.length() > 5;
        Predicate<String> letterLengthLt = s -> s.length() < 3;
        MICRO_SERVICE.stream().filter(letterLengthGt.or(letterLengthLt)).forEach(System.out::println);
    }

}
