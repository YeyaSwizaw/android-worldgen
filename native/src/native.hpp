#ifndef NATIVE_NATIVE_HPP
#define NATIVE_NATIVE_HPP

#include <jni.h>
#include <android/log.h>

#define APP_NAME "Worldgen Native"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL Java_uk_co_yeyaswizaw_worldgen_Native_hello(JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif

#endif
