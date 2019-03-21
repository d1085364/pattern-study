package com.edu.pattern.singleton;

public class Singleton {

    /**
     * 单例模式：一个类模板，在整个系统运行过程中，只允许产生一个实例
     * 用的方面：工厂本身 配置文件 日历
     * 解决一个并发访问的时候线程安全问题
     * 保证单例的技术方案很多：
     * 饿汉式、懒汉式、注册登记式、枚举式
     * 序列化与反序列化的时候出现多例
     */


    /*单例中懒汉和饿汉的本质区别？
    懒汉
    public class Student{
        private Student();//私有构造
        private static Student student = null；
        public static  Student newStudent(){
            if(student == null) {
                student = new Student();  //懒汉式做法
            }
            return student；
        }

        饿汉
        public class Student    {
            private Student();//私有构造
            private static Student student = new Student（）； //建立对象
            public static   Student newStudent(){
                return student；  //直接返回单例对象
            }

            忽略线程安全不计，从表面上看，懒汉是调用方法时候实例化对象，饿汉是加载类时候就实例化了。
            但是最终结果只要用到这个类，都是要实例化的，除了顺序不一样，感觉没啥区别了？

            比如饿汉模式，如果我要Student.newStudent(); 这时候加载这个类，然后静态变量实例化，然后通过方法返回实例化对象。最终我得到了Student类的实例化对象。

                    那懒汉模式我要Student.newStudent(),执行过程也是先加载这个类，但是静态变量赋值为null,然后通过方法里去实例化对象，最终我得到了Student类的实例化对象。

            所以从流程上看，除了实例化对象时候的顺序和位置不同，好像没什么区别啊？因为最终我都是得到了对象，就好比我上午吃馒头和下午吃馒头，除了时间不同，但是最后结果都是吃馒头啊，那又有什么关系的，反正都是吃。这里咱们还是先忽略线程问题不考虑哦

            但是我还知道如果在工厂模式中，如果有多个单例的对象，那懒汉和饿汉就有区别了，
            比如一个工厂类里如果有多个静态变量赋值实例化对象，一旦这个工厂类加载，就会把
            所有的静态变量全创建出实例化对象，这样是占资源的。

            所以我想问问，如果不是工厂模式的话，哗胆糕感蕹啡革拾宫浆就只有一个单例的时候，懒汉和饿汉是不是没太大区别？（除了顺序和线程不计）

            zzhszx1
            采纳率：53%8级2013.10.29
                    　　单例中懒汉和饿汉的本质区别在于以下几点：

                    　　1、饿汉式是线程安全的,在类创建的同时就已经创建好一个静态的对象供系统使用,以后不在改变。懒汉式如果在创建实例对象时不加上synchronized则会导致对对象的访问不是线程安全的。

                    　　2、从实现方式来讲他们最大的区别就是懒汉式是延时加载,他是在需要的时候才创建对象,而饿汉式在虚拟机启动的时候就会创建，饿汉式无需关注多线程问题、写法简单明了、能用则用。但是它是加载类时创建实例(上面有个朋友写错了)、所以如果是一个工厂模式、缓存了很多实例、那么就得考虑效率问题，因为这个类一加载则把所有实例不管用不用一块创建。
            追问： 明白了，顺便在问下，一个类被加载，是吧属性和方法都加载了吗？那方法里的代码会被执行吗？还是只把方法名加载？里面的代码只有调用方法时候才执行？
            比如我new一个对象了，但是我只调用了属性，没调用任何方法

            此时这个类加载了，那里面的方法应该也被加载了，但是我不知道方法里的代码会加载吗，还是只加载一个方法名而已？
            追答： 按问题分别回答下：
                    1、类加载，即将字节码class文件加到载到jvm中或者说到内存中。并不执行任何的方法和属性，类的静态代码块是类加载即执行。
                    2、方法和属性的执行，是在调用的时候，去内存中定位取值或是计算，类加载是不对他们进行计算的。
                    3、new一个对象：即是调用该类的构造方法，也就是执行一次，执行了什么就会计算什么。
                    4、方法的加载：方法的加载是和class一起的，即代码块被加载进内存，并不会被执行。
                    5、方法的执行：即将代码块调入jvm解释器解释执行，当然方法执行一次则代码块就会被jvm调入解释执行一次。
            像这些内容需要相当长一段时间的程序设计后，自然就会很清楚，希望能解决楼主的问题。
            追问： 关于第二条我还有点疑问，我可以这么理解嘛，加载就好比把面包先放进加工厂，但是没有加工成面包片呢，当你执行属性和方法时候，才执行加工成面包片？
            我明白了，所有的方法和属性（其实就是咱们写的代码内容）已经全在内存里了，但是没有调用的话，cup是不会去计算和执行的，只有你调用哪个才去计算和执行哪个属性和方法的代码对吧？
            追答： 1、恩，是的，楼主这段话是完全正确的。
                    2、不过，类的静态代码块除外，如static {
                                                           ........
            }
            这种情况之下，类一加载，该代码块就会被执行。相当直接卖面粉而已。

            多从写代码去体会是王道。

            */


            //1、类加载，即将字节码class文件加到载到jvm中或者说到内存中。并不执行任何的方法和属性，类的静态代码块是类加载即执行。
            //2、方法和属性的执行，是在调用的时候，去内存中定位取值或是计算，类加载是不对他们进行计算的。
            //3、new一个对象：即是调用该类的构造方法，也就是执行一次，执行了什么就会计算什么。
            //4、方法的加载：方法的加载是和class一起的，即代码块被加载进内存，并不会被执行。
            //5、方法的执行：即将代码块调入jvm解释器解释执行，当然方法执行一次则代码块就会被jvm调入解释执行一次。

        }
