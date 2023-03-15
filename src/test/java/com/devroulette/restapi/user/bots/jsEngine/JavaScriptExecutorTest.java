package com.devroulette.restapi.user.bots.jsEngine;

import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JavaScriptExecutorTest {

    private final static String VALID_SCRIPT = """
            function test() {
                return 10 + 10;
            }
                        
            test();
            """;
    private final static String INVALID_SCRIPT = """
            function test() {
                returnn 10 + 10;
            }
            """;

    private JavaScriptExecutor javaScriptExecutor = new JavaScriptExecutor();

    @Test
    void isScriptSyntaxCorrectShouldReturnTrueForValidScript() {
        // when
        boolean result = this.javaScriptExecutor.isScriptSyntaxCorrect(VALID_SCRIPT);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void isScriptSyntaxCorrectShouldReturnFalseForInvalidScript() {
        // when
        boolean result = this.javaScriptExecutor.isScriptSyntaxCorrect(INVALID_SCRIPT);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void executeShouldReturnValueCalculatedInPassedScript() {
        // when
        Value result = this.javaScriptExecutor.execute(VALID_SCRIPT);

        // then
        assertThat(result.asInt()).isEqualTo(20);
    }
}