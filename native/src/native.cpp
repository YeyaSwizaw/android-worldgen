#include "native.hpp"
#include "main.hpp"

namespace native {

void hello(JNIEnv*, jobject) {
    LogI("Hello Native World!");
}

}
