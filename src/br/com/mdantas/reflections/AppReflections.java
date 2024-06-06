package br.com.mdantas.reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author marcelo.dantas
 */
public class AppReflections {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class tabela = Tabela.class;

        Annotation[] annotations = tabela.getAnnotations();

        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType().getSimpleName());
        }

        Class clazz = Produto.class;

        System.out.println(clazz);

        Produto prod = new Produto();
        Class clazz1 = prod.getClass();
        System.out.println(clazz1);

        Constructor cons = clazz.getConstructor();
        Produto prod1 = (Produto) cons.newInstance();
        System.out.println(cons);
        System.out.println(prod1);

        Field[] fields = prod1.getClass().getDeclaredFields();

        for (Field field : fields) {
            Class<?> type = field.getType();
            String nome = field.getName();
            System.out.println(type);
            System.out.println(nome);
        }

        Method[] methods = prod1.getClass().getDeclaredMethods();

        for (Method method : methods) {
            Class<?> type = method.getReturnType();
            String nome = method.getName();
            System.out.println(type);
            System.out.println(nome);

            System.out.println("Executando métodos");
            if (method.getName().startsWith("set")) {
                for (Class classesTypes : method.getParameterTypes()) {
                    if (classesTypes.equals(String.class)) {
                        System.out.println(method.invoke(prod1, "Mahaw"));
                    } else if (classesTypes.equals(Long.class)) {
                        System.out.println(method.invoke(prod1, 1l));
                    } else {
                        System.out.println(method.invoke(prod1, 2d));
                    }
                }
            } else {
                System.out.println(method.invoke(prod1));
            }
        }
    }
}
