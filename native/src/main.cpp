#include "main.hpp"
#include "native.hpp"

#ifdef __cplusplus
extern "C" {
#endif

static JNINativeMethod native_methods[] = {
    { "hello", "()V", (void*)native::hello }
};

jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env;
    if(vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
        LogE("Failed to get environment");
        return -1;
    }

    jclass native_class = env->FindClass("uk/co/yeyaswizaw/worldgen/Native");
    if(!native_class) {
        LogE("Unable to load class 'uk/co/yeyaswizaw/worldgen/Native'");
    }

    env->RegisterNatives(native_class, native_methods, sizeof(native_methods) / sizeof(native_methods[0]));

    LogI("Native Library Loaded");
    return JNI_VERSION_1_6;
}

#ifdef __cplusplus
}
#endif
