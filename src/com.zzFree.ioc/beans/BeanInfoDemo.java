package com.zzFree.ioc.beans;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.IntrospectionException;
import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 *{@link java.beans.BeanInfo} 示例
 * @since
 *
 * 为什么会有PropertyDescriptor的介绍:
 *      因为内省(Introspector) 是Java 语言对JavaBean类属性、事件的一种缺省处理方法。
 *      JavaBean是一种特殊的类，主要用于传递数据信息，这种类中的方法主要用于访问私有的字段。
 *      Java JDK中提供了一套API用来访问某个属性的getter/setter方法，这就是内省。
 *      PropertyDescriptor类：(属性描述器)
 *
 */


public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {

        /**获取Person类的beanInfo  即 bean的元信息 */
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);

        /**   Part 1
         * 演示了propertyDescriptor的具体内容
         */
        Stream.of(beanInfo.getPropertyDescriptors())
        .forEach(propertyDescriptor ->{
            System.out.println(propertyDescriptor);
                });

        /**    Part 1
         *     PropertyDescriptor 允许添加属性编辑器 - PropertyEditor
         *      例如：Gui -> text(String)->PropertyType
         *     Part 2
         *      setPropertyEditorClass 用法:
         *          通常，可以使用PropertyEditorManager找到PropertyEditor。
         *           但是，如果由于某种原因您想要关联特定的具有给定属性的PropertyEditor
         *           那么您可以使用此方法。
         *
         *    Part 3
         *     该模块的演示是为了说明白:
         *     比如我们做一个GUI，会根据输入来输出一些内容，往往我们输入的是String，但是在bean中age对应
         *     的是Integer类型
         *     我们不会直接调用某个类的read/write方法，而是交给了某个PropertyEditorSupport子类去完成类型转换
         */
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor ->{
                    Class<?> propertyType = propertyDescriptor.getPropertyType();
                    String propertyName = propertyDescriptor.getName();
                    if("age".equals(propertyName)){ //为 "age"字段/属性 增加 propertyEditor
                        //Part 2
                        propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                    }
                });


        /**    lambda foreach demo:
         *     lambda大概语法:
         *         1. map在jdk8 新增了forEach方法,参数接口是BiConsumer刚好契合lambda写法
         *         2.map.forEach( 传入一个代码块 ，入参是 (k,v))
         *         2.1  代码块的方法在箭头后面 ->  输出
         */
        Map<String,Integer> map = new HashMap<>();
        map.put("A",10);
        map.put("B",20);
        map.put("C",30);
        map.forEach((k,v)-> System.out.println("Item:"+k+"Count:"+v));
        /**========================================================================*/

    }

    /**    PropertyEditorSupport 是一个实现了PropertyEditor接口的大部分基础方法的类，方便我们调用
     *      使用了PropertyEditorSupport要注意，覆写setAsText()方法
     *      setAsText  : Sets the property value by parsing a given String 输入限定String
     *     setValue  :  临时存储以便于后续调用
     */
    static class StringToIntegerPropertyEditor extends PropertyEditorSupport{
        @Override
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
         }
    }
}
