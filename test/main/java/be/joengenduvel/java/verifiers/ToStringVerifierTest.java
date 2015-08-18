package be.joengenduvel.java.verifiers;

import org.junit.Test;

public class ToStringVerifierTest {

    @Test
    public void shouldPassWhenToStringIsImplementedCorrectly() {
        ToStringVerifier.forClass(ClassWithToString.class).containsAllPrivateFields(new ClassWithToString());
    }

    @Test(expected = WrongToStringImplementation.class)
    public void shouldFailWhenToStringIsNotImplemented() {
        ToStringVerifier.forClass(ClassWithoutToString.class).containsAllPrivateFields(new ClassWithoutToString());
    }

    @Test(expected = WrongToStringImplementation.class)
    public void shouldFailWhenProvidedWithNull() {
        ToStringVerifier.forClass(TestClass.class).containsAllPrivateFields(null);
    }

    @Test
    public void shouldIgnoreJacocoFields() {
        Class<ClassWithToString> classWithToStringClass = ClassWithToString.class;
        ToStringVerifier.forClass(ClassContainingJacocoData.class).containsAllPrivateFields(new ClassContainingJacocoData());
    }

    private class TestClass {
        private final int field2 = 2;
        private String field1 = "field1";
    }

    private class ClassWithoutToString {
        private final int field2 = 2;
        private String field1 = "field1";
    }

    private class ClassWithToString {
        private final int field2 = 2;
        private String field1 = "field1";

        @Override
        public String toString() {
            return "ClassWithToString{" +
                    "field1='" + field1 + '\'' +
                    ", field2=" + field2 +
                    '}';
        }
    }

    private class ClassContainingJacocoData {
        private Object $jacocoData = null;
        private String field1 = "field1";

        @Override
        public String toString() {
            return "SomeClass{" +
                    "field1='" + field1 + '\'' +
                    '}';
        }
    }
}