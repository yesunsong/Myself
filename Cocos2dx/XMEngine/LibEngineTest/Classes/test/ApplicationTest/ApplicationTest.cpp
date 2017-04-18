#include "ApplicationTest.h"

USING_NS_CC;

ApplicationTests::ApplicationTests() {
    ADD_TEST_CASE(BaseApplicationTest);
}

//------------------------------------------------------------------
//
// BaseApplicationTest
//
//------------------------------------------------------------------

BaseApplicationTest::BaseApplicationTest(void) {
}

BaseApplicationTest::~BaseApplicationTest(void) {
}


void BaseApplicationTest::onEnter() {
    TestCase::onEnter();

    // Android��iOS�Դ�֧�ִ򿪱��������
    Application::getInstance()->openURL("https://www.baidu.com");
}

std::string BaseApplicationTest::title() const {
    return "Application Test";
}
std::string BaseApplicationTest::subtitle() const {
    return "Base Application Test";
}