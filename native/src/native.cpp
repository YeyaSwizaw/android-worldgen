#include "native.hpp"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL Java_uk_co_yeyaswizaw_worldgen_Native_hello(JNIEnv *, jobject) {
    __android_log_print(ANDROID_LOG_INFO, APP_NAME, "Hello Native World!");
}

#ifdef __cplusplus
}
#endif

