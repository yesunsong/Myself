// C++LearningVS.cpp : �������̨Ӧ�ó������ڵ㡣
//

//#include "stdafx.h"
#include "StringTest.h"
#include <stdio.h>
#include <iostream>
#include <string>

using std::cout;
using std::endl;
using std::cin;
using std::string;

void StringTest::test() {
    //����string����
    string word1 = "Game";
    string word2("Over");
    string word3(3, '!');
    //string���������
    string phrase = word1 + " " + word2 + word3;
    cout << "The phrase is: " << phrase << "\n\n";
    //ʹ��size()��Ա����
    cout << "The phrase has " << phrase.size() << " characters in it.\n\n";
    //����string����
    cout << "The character at position 0 is:" << phrase[0] << "\n\n";
    cout << "Changing the character at position 0.\n";
    phrase[0] = 'L';
    cout << "The phrase is now " << phrase << "\n\n";
    //ѭ������string����
    for (unsigned int i = 0; i < phrase.size(); i++) {
        cout << "Character at position " << i << " is " << phrase[i] << endl;
    }
    cout << "\nThe sequence 'Over' begin at location ";
    cout << phrase.find("Over") << endl;
    //ʹ��find()��Ա����
    if (phrase.find("eggplant") == string::npos) {
        cout << "'eggplant' is not in the phrase.\n\n";
    }
    //ʹ��erase()��Ա����
    phrase.erase(4, 5);
    cout << "The phrase is now:" << phrase << endl;
    phrase.erase(4);
    cout << "The phrase is now:" << phrase << endl;
    phrase.erase();
    cout << "The phrase is now:" << phrase << endl;
    //ʹ��empty()��Ա����
    if (phrase.empty())	{
        cout << "\nThe phrase is no more.\n";
    }
    //system("pause");yon g
}

/**ʹ��string����*/
//int _tmain(int argc, _TCHAR* argv[]) {
//
//    return EXIT_SUCCESS;
//}

