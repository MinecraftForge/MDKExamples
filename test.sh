#!/bin/bash

# [TODO] Windows batch script version, and make it better

export MDK_EXAMPLE_FG7=7.0.0-rc.2
( cd "accesstransformers-only/fg7" && ./gradlew build )
AT_TEST=$?
( cd "jarjar-only/fg7" && ./gradlew build )
JARJAR_TEST=$?
( cd "legacy-mdk/fg7" && ./gradlew build )
LEGACY_TEST=$?
( cd "mixins-only/fg7" && ./gradlew build )
MIXINS_TEST=$?

echo ""
echo "Results (0 = passed, any other = failure)"
echo "accesstransformers-only: $AT_TEST"
echo "jarjar-only: $JARJAR_TEST"
echo "legacy-mdk: $LEGACY_TEST"
echo "mixins-only: $MIXINS_TEST"

exit $((AT_TEST + JARJAR_TEST + LEGACY_TEST + MIXINS_TEST))
