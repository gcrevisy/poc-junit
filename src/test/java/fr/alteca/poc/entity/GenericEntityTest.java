package fr.alteca.poc.entity;

import org.junit.Assert;
import org.junit.Test;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class GenericEntityTest {

    @Test
    public void entitiesInstanciationTest() {

        Reflections reflections = new Reflections("fr.alteca.poc");

        String errors = "";

        Set<Class<? extends Entity>> entities = reflections.getSubTypesOf(Entity.class);

        for (Class<? extends Entity> entity : entities) {
            Entity instance = null;
            try {
                instance = entity.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            List<Field> fields = Arrays.asList(entity.getDeclaredFields());
            if (instance != null && fields.size() > 0) {
                for (Field field : fields) {
                    try {
                        Object value = field.get(instance);
                        if (value == null) {
                            Assert.fail(entity.getName() + "#" + field.getName());
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
