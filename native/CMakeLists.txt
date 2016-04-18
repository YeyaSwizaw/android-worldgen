cmake_minimum_required(VERSION 3.5)
project(native)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -ffor-scope -fno-rtti -fno-exceptions -pipe -ffunction-sections -fdata-sections -ffast-math -Wnon-virtual-dtor -Wreorder -Wsign-promo -fvisibility=hidden -fvisibility-inlines-hidden -Wstrict-null-sentinel -Os -funroll-all-loops -fpeel-loops -ftree-vectorize")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS}")
set(LINKER_FLAGS "${LINKER_FLAGS} -Wl,--as-needed -Wl,--gc-sections -Wl,--no-undefined -Wl,--strip-all -Wl,-rpath-link=${ANDROID_NDK_SYSROOT}/usr/lib/ -L${ANDROID_NDK_SYSROOT}/usr/lib/")

set(SOURCE_FILES src/native.cpp)

add_library(native SHARED ${SOURCE_FILES})
target_link_libraries(native log android)
set_target_properties(native PROPERTIES COMPILE_DEFINITIONS "ANDROID")
