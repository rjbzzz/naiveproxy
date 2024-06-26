// Copyright 2018 The Chromium Authors
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.
package org.jni_zero;

import android.content.Context;
import android.view.View;

import org.stubs.MyClass;
import org.stubs.MyClass.FirstNestedInterface;
import org.stubs.MyClass.SecondNestedInterface;
import org.stubs.MyInterface;

import java.util.ArrayList;

/**
 * Sample class that uses the JNI annotation processor for static methods.
 * See generated files at bottom.
 */
class SampleForAnnotationProcessor {
    class TestStruct {
        int mA;
        int mB;
    }
    /**
     * Static methods declared here, each of these refer to a native method
     * which will have its declaration generated by our annotation processor.
     * There will also be a class generated to wrap these native methods
     * with the name SampleForAnnotationProcessorJni which will implement
     * Natives.
     */
    @NativeMethods
    interface Natives {
        void foo();
        SampleForAnnotationProcessor bar(SampleForAnnotationProcessor sample);
        String revString(String stringToReverse);
        String[] sendToNative(String[] strs);
        SampleForAnnotationProcessor[] sendSamplesToNative(SampleForAnnotationProcessor[] strs);
        boolean hasPhalange();

        @JniType("std::vector<int32_t>")
        int[] testAllPrimitives(
                @JniType("int") int zint,
                @JniType("std::vector<int32_t>") int[] ints,
                long zlong,
                long[] longs,
                short zshort,
                short[] shorts,
                @JniType("int") char zchar,
                char[] chars,
                byte zbyte,
                byte[] bytes,
                double zdouble,
                double[] doubles,
                float zfloat,
                float[] floats,
                boolean zbool,
                boolean[] bools);

        void testSpecialTypes(
                Class clazz,
                Class[] classes,
                Throwable throwable,
                Throwable[] throwables,
                String string,
                String[] strings,
                @JniType("std::string") String convertedString,
                @JniType("std::vector<std::string>") String[] convertedStrings,
                @JniType("std::optional<std::string>") String optionalString,
                TestStruct tStruct,
                TestStruct[] structs,
                Object obj,
                @JniType("jni_zero::tests::CPPClass") Object convertedObj,
                Object[] objects,
                MyClass.SecondNestedInterface nestedInterface,
                View view,
                Context context,
                @JniType("std::vector<jni_zero::tests::CPPClass>") Object[] convertedObjects);

        Throwable returnThrowable();

        Throwable[] returnThrowables();

        Class returnClass();

        Class[] returnClasses();

        String returnString();

        @JniType("std::string")
        String returnConvertedString();

        String[] returnStrings();

        @JniType("std::vector<std::string>")
        String[] returnConvertedStrings();

        @JniType("std::vector")
        SampleForAnnotationProcessor[] returnConvertedAppObjects();

        @JniType("std::vector")
        int[] returnConvertedInts();

        TestStruct returnStruct();
        TestStruct[] returnStructs();
        Object returnObject();
        Object[] returnObjects();
    }

    public static void test() {
        // Using the imports so they are not removed.
        View view = null;
        Context context = null;
        MyClass myclass = null;
        FirstNestedInterface nested1 = null;
        SecondNestedInterface nested2 = null;
        MyInterface myinterface = null;
        ArrayList myList = new ArrayList<String>();

        int[] x = new int[] {1, 2, 3, 4, 5};
        String[] strs = new String[] {"the", "quick", "brown", "fox"};
        strs = SampleForAnnotationProcessorJni.get().sendToNative(strs);

        SampleForAnnotationProcessor sample =
                SampleForAnnotationProcessorJni.get().bar(new SampleForAnnotationProcessor());
        SampleForAnnotationProcessor[] samples =
                new SampleForAnnotationProcessor[] {sample, sample};
        samples = SampleForAnnotationProcessorJni.get().sendSamplesToNative(samples);

        // Instance of Natives accessed through (classname + "Jni").get().
        SampleForAnnotationProcessorJni.get().foo();
        boolean hasPhalange = SampleForAnnotationProcessorJni.get().hasPhalange();
        String s = SampleForAnnotationProcessorJni.get().revString("abcd");
    }
}
