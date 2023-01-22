package com.devroulette.restapi.jsEngine;

import org.graalvm.polyglot.*;


// https://www.graalvm.org/javascript/
// https://www.graalvm.org/22.2/reference-manual/js/RunOnJDK/

public class JavaScriptExecutor {

    private static final String JS = "js";

    public boolean isScriptSyntaxCorrect(String script) {
        try (Context context = Context.create()) {
            Source source = Source.create(JS, script);
            try {
                context.parse(source);
                return true;
            } catch (PolyglotException e) {
                if (e.isSyntaxError()) {
                    // Might be used to point where the error is
                    SourceSection location = e.getSourceLocation();
                    return false;
                    // syntax error detected at location
                } else {
                    // other guest error detected
                }
                throw e;
            }
        }
    }

    public Value execute(String script) {
        Engine engine = Engine.newBuilder()
                .option("js.ecmascript-version", "2020")
                .option("engine.WarnInterpreterOnly", "false")
                .build();

        Context context = Context.newBuilder(JS).engine(engine).build();
        Source source = Source.create(JS, script);

        return context.eval(source);
    }

}
