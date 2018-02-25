package com.pax.cms;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Myconfig.class,Bank.class,Person.class); 
        System.out.println(context.getBean(Person.class).getContext());
        context.close();
    }
}
