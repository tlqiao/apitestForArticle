package tl86.thirdCourse

import org.junit.Test
class ClosureStudy {
    def firstClosure = {println "hello world"} //把闭包赋值给变量
    def secondClosure= {a,b -> a+b}
    @Test
    void testClosure() {
        firstClosure() //运行闭包
        println secondClosure(1,2)  //返回1+2的值
    }
    //闭包作为方法的参数
    def myFunction(name,closure) {
        closure(name)
    }
    @Test
    void testMyFunction() {
        myFunction("Dave",{name-> println "my name is ${name}"})
        myFunction("Dave",{it-> println "my name is ${it}"})
        myFunction("Dave",{println "my name is ${it}"})
       // 上面三种写法效果一样，闭包的参数只有一个时，可以用it，且可以省略 it->
    }
    //闭包是方法的唯一参数
    def function(closure) {
        def a ="hello"
        closure(a)
    }
    @Test
    void testFunction() {
        function({it -> println it})
        function{it -> println it} //方法的括号可以取消，前面已练习过
        //上面两种写法效果一样
        // 第二种写法在数据集处理部分使用很多,我们讲闭包也是为数据集处理做准备，接口测试中有大量数据集处理的场景
    }
}
