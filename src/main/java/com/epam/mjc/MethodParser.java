package com.epam.mjc;

import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringTokenizer tokenizer = new StringTokenizer(signatureString, "()");
        String[] methodSignatures = tokenizer.nextToken().split(" ");
        MethodSignature methodSignature = new MethodSignature(methodSignatures[methodSignatures.length - 1]);
        if (methodSignatures.length == 3) {
            methodSignature.setAccessModifier(methodSignatures[0]);
            methodSignature.setReturnType(methodSignatures[1]);
        } else {
            methodSignature.setReturnType(methodSignatures[0]);
        }

        if (tokenizer.hasMoreTokens()) {
            fillMethodArguments(methodSignature, tokenizer.nextToken());
        }
        return methodSignature;
    }

    private void fillMethodArguments(MethodSignature methodSignature, String arguments) {
        StringTokenizer tokenizer = new StringTokenizer(arguments, ",");
        List<MethodSignature.Argument> argumentList = methodSignature.getArguments();
        String[] params;
        while (tokenizer.hasMoreTokens()) {
            params = tokenizer.nextToken().trim().split(" ");
            argumentList.add(new MethodSignature.Argument(params[0], params[1]));
        }
    }
}
