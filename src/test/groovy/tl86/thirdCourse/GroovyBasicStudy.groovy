package tl86.thirdCourse

import org.junit.Test

class GroovyBasicStudy {
    def add(a, b, c = 1) {
        a + b + c
    }

    @Test
    void testAdd() {
        println add(2, 2)
        def c = add 1, 1, 3
        println c
    }

    @Test
    void testGstring() {
        def c = 100
        println "this c value is ${c}"
        int[] array = [1, 2, 3]
        array.each() { it ->
            println """the array value is ${it}\n """
        }
    }

    @Test
    void testIf() {
        def c = 0
        int[] myArray = []
        def myString = ""
        if (!c) {
            println "c is 0"
        }
        if (!myArray) {
            println "myArray is 空值数组"
        }
        if (!myString) {
            println "myString is 空值字符串"
        }
    }

    @Test
    void testEqual() {
        def a = [1,2,3]
        def b = [1,2,3]
        def c = a
        if (a == b) {
            println "a's value equal to b"
        }
        if (a.is(b)) {
            println "a is b"
        } else {
            println "a is not b"
        }
        if (a.is(c)) {
            println "a is c"
        }
    }

    @Test
    void testAssert() {

    }
}
