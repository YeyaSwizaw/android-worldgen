#ifndef NATIVE_MAIN_HPP
#define NATIVE_MAIN_HPP

#include <jni.h>
#include <android/log.h>

#define APP_NAME "Worldgen Native"

#define LOG_(type, ...) __android_log_print(ANDROID_LOG_ ## type, APP_NAME, __VA_ARGS__)
#define LogI(...) LOG_( INFO , __VA_ARGS__)
#define LogE(...) LOG_( ERROR , __VA_ARGS__)

#ifdef __cplusplus
extern "C" {
#endif

jint JNI_OnLoad(JavaVM* vm, void* reserved);

#ifdef __cplusplus
}
#endif

#endif
