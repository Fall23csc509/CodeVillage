package org.test;

public class MultipleClasses {
    private int attributeOne;

    public void methodOne() {};
    public class PublicInnerClass {
        // Inner class code
        private String privateAttribute;
        private double anotherAttribute;
        private int thirdAttribute;

    }

    private class PrivateInnerClass {
        // Inner class code
        private String privateAttribute;
        private double anotherAttribute;

        public void method1() {
            // Method 1 implementation
        }
    }
}