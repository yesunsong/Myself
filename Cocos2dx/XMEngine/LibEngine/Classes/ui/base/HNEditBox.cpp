﻿//#include "stdafx.h"
#include "HNEditBox.h"

namespace HN {

HNEditBox::HNEditBox(void) {
}

HNEditBox::~HNEditBox(void) {
}

HNEditBox* HNEditBox::createEditBox(TextField* module, ui::EditBoxDelegate* pDelegate) {
    HNEditBox* editBox = new HNEditBox();
    if (editBox->init(module, pDelegate)) {
        editBox->autorelease();
        return editBox;
    } else {
        CC_SAFE_DELETE(editBox);
        return nullptr;
    }
}

bool HNEditBox::init(TextField* module, ui::EditBoxDelegate* pDelegate) {
    Size editSize = module->getCustomSize();
    this->initWithSizeAndBackgroundSprite(editSize, cocos2d::ui::Scale9Sprite::create());

    this->setFontName(module->getFontName().c_str());
    this->setFontSize(module->getFontSize());
    this->setAnchorPoint(module->getAnchorPoint());
    this->setPosition(module->getPosition());
    this->setFontColor(module->getColor());
    this->setPlaceholderFontName(module->getFontName().c_str());
    this->setPlaceholderFontSize(module->getFontSize());
    this->setPlaceHolder(module->getPlaceHolder().c_str());
    this->setPlaceholderFontColor(module->getPlaceHolderColor());
    if (module->getMaxLength() > 0) {
        this->setMaxLength(module->getMaxLength());
    }
    //设置键盘中return键显示的字符
    this->setReturnType(EditBox::KeyboardReturnType::DONE);
    //设置输入文本类型
    this->setInputMode(EditBox::InputMode::SINGLE_LINE);
    //设置委托代理对象
    this->setDelegate(pDelegate);
    module->getParent()->addChild(this, module->getLocalZOrder());

    return true;
}

void HNEditBox::setInput(EditBox::InputMode type) {
    this->setInputMode(type);
}

void HNEditBox::setString(std::string text) {
    this->setText(text.c_str());
}

std::string HNEditBox::getString() {
    return this->getText();
}

int HNEditBox::getStringLength() {
    std::string tmp = this->getText();
    return tmp.length();
}

bool HNEditBox::isPasswordEnabled() {
    if (_editBoxInputFlag == EditBox::InputFlag::PASSWORD) {
        return true;
    } else
        return false;
}

void HNEditBox::setPasswordEnabled(bool enable) {
    if (enable) {
        _editBoxInputFlag= EditBox::InputFlag::PASSWORD;
    } else {
        _editBoxInputFlag = EditBox::InputFlag::INITIAL_CAPS_ALL_CHARACTERS;
    }
    this->setInputFlag(_editBoxInputFlag);
}
}
