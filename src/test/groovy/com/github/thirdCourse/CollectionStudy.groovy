package com.github.thirdCourse

import org.junit.Test

class CollectionStudy {
    @Test
    void testEach() {
        //数组的each方法参数就是一个闭包
        def firstList = ["zhangshan", 1, 2, "lisi"]
        firstList.each { println it }

        // 采用each巧妙计算数组的和
        def secondList = [1, 3, 5, 7]
        def a = 0
        secondList.each { a = a + it }
        println a

        // map对象也有each方法
        def myMap = ["name": "Tom", "age": 100]
        myMap.each { key, value -> println key + "---" + value }
    }

    @Test
    void testFind() {
        def firstList = [1, 3, 5, 8, 9]
        def result = firstList.find{ it -> it > 5 }
        println result //打印出8
        result = firstList.findAll{it -> it>5}
        println result  // 打印出[8,9]

    }
}
