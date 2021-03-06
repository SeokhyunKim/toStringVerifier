package be.joengenduvel.java.verifiers;

import org.junit.Ignore;
import org.junit.Test;

public class ToStringVerifierTest {

    @Test
    public void containsAllPrivateFields_shouldPassWhenToStringIsImplementedCorrectly() {
        ToStringVerifier.forClass(ClassWithToString.class).containsAllPrivateFields(new ClassWithToString());
    }

    @Test(expected = WrongToStringImplementationException.class)
    public void containsAllPrivateFields_shouldFailWhenToStringIsNotImplemented() {
        ToStringVerifier.forClass(ClassWithoutToString.class).containsAllPrivateFields(new ClassWithoutToString());
    }

    @Test(expected = WrongToStringImplementationException.class)
    public void containsAllPrivateFields_shouldFailWhenProvidedWithNull() {
        ToStringVerifier.forClass(ClassWithToString.class).containsAllPrivateFields(null);
    }

    @Test
    public void containsAllPrivateFields_shouldIgnoreJacocoFields() {
        ToStringVerifier.forClass(ClassContainingJacocoAndJpaData.class).containsAllPrivateFields(new ClassContainingJacocoAndJpaData());
    }

    @Test(expected = WrongToStringImplementationException.class)
    public void containsAllPrivateFields_shouldFailWhenFieldIsNotPresentInToString() {
        ToStringVerifier.forClass(ClassWithMissingFieldInToString.class).containsAllPrivateFields(new ClassWithMissingFieldInToString());
    }

    @Test(expected = WrongToStringImplementationException.class)
    public void containsAllPrivateFields_shouldFailWhenValueIsNotPresentInToString() {
        ToStringVerifier.forClass(ClassWithMissingValueInToString.class).containsAllPrivateFields(new ClassWithMissingValueInToString());
    }

    @Test
    public void containsAllPrivateFields_shouldBeAbleToIgnoreFields() {
        ToStringVerifier.forClass(ClassWithMissingFieldInToString.class).ignore("field2").containsAllPrivateFields(new ClassWithMissingFieldInToString());
    }

    @Ignore
    @Test(expected = WrongToStringImplementationException.class)
    public void containsAllPrivateFields_shouldBeAbleToHandleReflectionErrors() {
        //TODO: use mockito or something to trigger this behaviour
        ToStringVerifier.forClass(ClassWithToString.class).containsAllPrivateFields(new ClassWithToString());
    }

    @Test(expected = WrongToStringImplementationException.class)
    public void containsClassName_shouldFailForNullValues() {
        ToStringVerifier.forClass(ClassWithToString.class).containsClassName(null);
    }

    @Test(expected = WrongToStringImplementationException.class)
    public void containsClassName_shouldFailIfClassNameIsMissingFromToString() {
        ToStringVerifier.forClass(ClassWithMissingFieldInToString.class).containsClassName(new ClassWithMissingFieldInToString());
    }

    @Test
    public void containsClassName_shouldPassIfClassNameIsPresentInToString() {
        ToStringVerifier.forClass(ClassWithToString.class).containsClassName(new ClassWithToString());
    }


    private class ClassWithoutToString {
        private final int field2 = 2;
        private String field1 = "field1";
    }

    private class ClassWithToString {
        private final int field2 = 2;
        private String field1 = "field1";
        private Object field3 = null;

        @Override
        public String toString() {
            return "ClassWithToString{" +
                    "field2=" + field2 +
                    ", field1='" + field1 + '\'' +
                    ", field3=" + field3 +
                    '}';
        }

    }

    private class ClassWithMissingValueInToString {
        private final int field2 = 2;
        private String field1 = "test";

        @Override
        public String toString() {
            return "ClassWithMissingValueInToString{" +
                    "field2=" + field2 +
                    ", field1='" + "something" + '\'' +
                    '}';
        }
    }

    private class ClassWithMissingFieldInToString {
        private final int field2 = 2;
        private String field1 = "field1";

        @Override
        public String toString() {
            return "ClassWithToString{" +
                    "field1='" + field1 + '}';
        }
    }

    private class ClassContainingJacocoAndJpaData {
        private final int field2 = 2;
        protected transient boolean pcVersionInit;
        protected transient Object pcStateManager;
        private String field1 = "field1";
        //jacoco
        private Object $jacocoData = "Some data";
        //jpa
        private Class pcPCSuperclass;
        private transient Object pcDetachedState;

        @Override
        public String toString() {
            return "ClassWithToString{" +
                    "field1='" + field1 + '\'' +
                    ", field2=" + field2 +
                    '}';
        }
    }
}