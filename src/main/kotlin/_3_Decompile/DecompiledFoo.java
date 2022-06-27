package _3_Decompile;

// Simplified for readability
class DecompiledFoo {
    /*
    private final Object foo(Continuation var1) {
        Logger var10000;
        Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object $continuation = null; // Init continuation

        switch ($continuation.label) {
            case 0:
                var10000 = LoggerFactory.getLogger(Decompile.Companion.class);
                Intrinsics.checkNotNullExpressionValue(var10000, "LoggerFactory.getLogger(T::class.java)");
                var10000.info("1");

                if (DelayKt.delay(1000L, (Continuation)$continuation) == var6) {
                    return var6;
                }
                break;
            case 1:
                var10000 = LoggerFactory.getLogger(Decompile.Companion.class);
                Intrinsics.checkNotNullExpressionValue(var10000, "LoggerFactory.getLogger(T::class.java)");
                var10000.info("2");
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
     */
}
