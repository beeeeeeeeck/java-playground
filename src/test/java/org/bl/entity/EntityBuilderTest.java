package org.bl.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author beckl
 */
public class EntityBuilderTest {
    @Test
    public void testBuilder() throws Exception {
        EntityBuilder<Person> personEntityBuilder = new EntityBuilder<>(Person.class);
        String name = "test";
        int age = 10;
        Person person = personEntityBuilder.setValue("name", name).setValue("age", age).build();
        assertNotNull(person.getName());
        assertNotNull(person.getAge());
        assertEquals(name, person.getName());
        assertEquals(age, person.getAge().intValue());
    }
}
