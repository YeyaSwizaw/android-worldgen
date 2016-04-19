cmake_minimum_required(VERSION 3.5)
project(native)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
    src/main.cpp
    src/native.cpp)

add_library(native SHARED ${SOURCE_FILES})
target_link_libraries(native log android)
set_target_properties(native PROPERTIES COMPILE_DEFINITIONS "ANDROID")

set(TARGET_INSTALL_DIR "${PROJECT_SOURCE_DIR}/../app/src/main/jniLibs/${ANDROID_ABI}/")

install(TARGETS native DESTINATION "${TARGET_INSTALL_DIR}")
add_custom_target(uninstall rm "${TARGET_INSTALL_DIR}/$<TARGET_FILE_NAME:native>" DEPENDS)
